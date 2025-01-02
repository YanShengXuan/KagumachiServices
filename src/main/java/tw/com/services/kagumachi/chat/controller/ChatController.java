package tw.com.services.kagumachi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
        messageRepository.save(savedMessage);

        System.out.println("Saved message: " + savedMessage);

        OutputMessage outputMessage = new OutputMessage(escapedContent, message.getSenderid(), message.getReceiverid());
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiverid(), outputMessage);

        System.out.println("Sent message: " + outputMessage);

        List<String> users = getUsers();
        messagingTemplate.convertAndSend("/topic/users", users);
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}