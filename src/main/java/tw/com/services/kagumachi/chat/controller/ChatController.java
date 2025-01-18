package tw.com.services.kagumachi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import tw.com.services.kagumachi.chat.model.InputMessage;
import tw.com.services.kagumachi.chat.model.Message;
import tw.com.services.kagumachi.chat.model.OutputMessage;
import tw.com.services.kagumachi.chat.repository.MessageRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/init")
    @SendTo("/topic/messages")
    public List<Message> init() {
        return messageRepository.findAll();
    }

    @MessageMapping("/users")
    @SendTo("/topic/users")
    public List<String> getUsers() {
        List<Message> allMessages = messageRepository.findAll();
        Set<String> users = new HashSet<>();
        for (Message message : allMessages) {
            users.add(message.getSenderid().toString());
            users.add(message.getReceiverid().toString());
        }
        users.remove("0");
        return new ArrayList<>(users);
    }

    @MessageMapping("/historyFront")
    @SendTo("/topic/historyFront")
    public List<Message> getHistoryFront(InputMessage message) {
        System.out.println("Received history request for front site with senderid: " + message.getSenderid() + " and receiverid: " + message.getReceiverid());
        if (message.getSenderid() == null || message.getReceiverid() == null) {
            throw new IllegalArgumentException("Sender ID and Receiver ID cannot be null");
        }
        Long senderid = Long.parseLong(message.getSenderid());
        Long receiverid = Long.parseLong(message.getReceiverid());
        List<Message> historyMessages = new ArrayList<>();
        historyMessages.addAll(messageRepository.findBySenderidAndReceiverid(senderid, receiverid));
        historyMessages.addAll(messageRepository.findBySenderidAndReceiverid(receiverid, senderid));
        System.out.println("Returning history messages for front site: " + historyMessages);
        return historyMessages;
    }

    @MessageMapping("/historyBack")
    @SendTo("/topic/historyBack")
    public List<Message> getHistoryBack(InputMessage message) {
        System.out.println("Received history request for back site with senderid: " + message.getSenderid() + " and receiverid: " + message.getReceiverid());
        if (message.getSenderid() == null || message.getReceiverid() == null) {
            throw new IllegalArgumentException("Sender ID and Receiver ID cannot be null");
        }
        Long senderid = Long.parseLong(message.getSenderid());
        Long receiverid = Long.parseLong(message.getReceiverid());
        List<Message> historyMessages = new ArrayList<>();
        historyMessages.addAll(messageRepository.findBySenderidAndReceiverid(senderid, receiverid));
        historyMessages.addAll(messageRepository.findBySenderidAndReceiverid(receiverid, senderid));
        System.out.println("Returning history messages for back site: " + historyMessages);
        return historyMessages;
    }

    @MessageMapping("/message")
    public void handleMessage(InputMessage message) {
        System.out.println("Received message: " + message);

        if (message.getSenderid() == null || message.getReceiverid() == null) {
            throw new IllegalArgumentException("Sender ID and Receiver ID cannot be null");
        }

        String escapedContent = HtmlUtils.htmlEscape(message.getContent());

        Message savedMessage = new Message();
        savedMessage.setContent(escapedContent);
        savedMessage.setSenderid(Long.parseLong(message.getSenderid()));
        savedMessage.setReceiverid(Long.parseLong(message.getReceiverid()));
        savedMessage.setTimestamp(System.currentTimeMillis());

        if (!message.getSenderid().equals("0")) {
            savedMessage.setIsfrontread(true);
            savedMessage.setIsbackread(false);
        } else if (message.getSenderid().equals("0")) {
            savedMessage.setIsfrontread(false);
            savedMessage.setIsbackread(true);
        }

        messageRepository.save(savedMessage);

        System.out.println("Saved message: " + savedMessage);

        OutputMessage outputMessage = new OutputMessage(escapedContent, message.getSenderid(), message.getReceiverid());
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiverid(), outputMessage);

        System.out.println("Sent message: " + outputMessage);

        List<String> users = getUsers();
        messagingTemplate.convertAndSend("/topic/users", users);
    }

    @MessageMapping("/markAsReadFront")
    public void markAsReadFront(@Payload Long userId) {
        System.out.println("Received markAsReadFront request for userId: " + userId);
        List<Message> messages = messageRepository.findBySenderidOrReceiverid(userId, userId);
        for (Message message : messages) {
            message.setIsfrontread(true);
            messageRepository.save(message);
            System.out.println("Message marked as read for front site: " + message);
        }
    }

    @MessageMapping("/markAsReadBack")
    public void markAsReadBack(@Payload Long userId) {
        System.out.println("Received markAsReadBack request for userId: " + userId);
        List<Message> messages = messageRepository.findBySenderidOrReceiverid(userId, userId);
        for (Message message : messages) {
            message.setIsbackread(true);
            messageRepository.save(message);
            System.out.println("Message marked as read for back site: " + message);
        }
    }

    @MessageMapping("/unreadBackMessages")
    @SendTo("/topic/unreadBackMessages")
    public List<Message> getUnreadBackMessages(@Payload Long userId) {
        System.out.println("Received request for unread back messages for userId: " + userId);
        List<Message> unreadMessages = messageRepository.findByReceiveridAndIsbackread(userId, false);
        System.out.println("Unread back messages: " + unreadMessages);
        return unreadMessages;
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}