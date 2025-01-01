package tw.com.services.kagumachi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import tw.com.services.kagumachi.chat.model.InputMessage;
import tw.com.services.kagumachi.chat.model.Message;
import tw.com.services.kagumachi.chat.model.OutputMessage;
import tw.com.services.kagumachi.chat.repository.MessageRepository;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/init")
    @SendTo("/topic/messages")
    public List<Message> init() {
        return messageRepository.findAll();
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public OutputMessage handleMessage(InputMessage message) {
        // 處理接收到的訊息
//        String escapedContent = HtmlUtils.htmlEscape(message.getContent());
        // 保存訊息到資料庫或執行其他邏輯
        // messageRepository.save(new Message(escapedContent));
//        return new OutputMessage(escapedContent);
        return new OutputMessage("不知道你在說什麼");
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}