package springMVC.mvcSpring.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import springMVC.mvcSpring.mapper.MessagesMapper;
import springMVC.mvcSpring.model.ChatForm;
import springMVC.mvcSpring.model.ChatMessage;

import java.util.List;

@Service
public class MessageService {

    private MessagesMapper messagesMapper;

    public MessageService(MessagesMapper messagesMapper) {
        this.messagesMapper = messagesMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating Message Service Bean");
    }
    public void addMessage(ChatForm chatForm){

        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()){
            case "Say":
                newMessage.setMessagetext(chatForm.getMessageText());
            case "Shout":
                newMessage.setMessagetext(chatForm.getMessageText().toUpperCase());
            case "Whisper":
            newMessage.setMessagetext(chatForm.getMessageText().toLowerCase());

        }
        //using MyBatis mapper to store message in the database
        messagesMapper.addMessage(newMessage);

    }

    public List<ChatMessage> getChatMessages(){
        //using MyBatis mapper to retrieve message from the database
        return messagesMapper.getAllMessages();
    }


//    //private List<String> messages;
//
//    //@PostConstruct initializes our constructor when our app starts running
//    @PostConstruct
//    public void postConstruct(){
//        this.messages = new ArrayList<>();
//    }
//
//    public void addMessage(String message){
//
//    }
//
//    public List<String> getMessages(){
//        return new ArrayList<>(this.messages);
//    }



}
