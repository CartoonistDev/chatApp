package springMVC.mvcSpring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springMVC.mvcSpring.model.ChatForm;
import springMVC.mvcSpring.service.MessageService;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    //Injecting our MessageListService into our HomeController class
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChat(ChatForm chatForm, Model model){
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }


    @PostMapping
    public String postChat(Authentication authentication, ChatForm chatForm, Model model){
        //Interact with spring controller to get the currently logged-in user username
        chatForm.setUsername(authentication.getName());

        this.messageService.addMessage(chatForm);
        //Clears the form after a post has been made
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }



    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes(){
        return new String[]{"Say", "Shout", "Whisper"};
    }





    /*
    In order to actually bind the controller to a specific request URL - like /home
    in our example - we have to define a method in the controller and annotate it with @RequestMapping.
    We also have to return a String from this method - this is the name of the template
    we want to render. For this first step into web development,
    that's all we do - return the String "home"
    to indicate that we want the home.html template to be rendered when a user requests the /home URL.
     */

    /*
    The @GetMapping functions same way as the @RequestMapping with only one difference.
    It only responds to get requests
     */
//    @GetMapping("/home")
//    public String getHomePage(@ModelAttribute("newMessage") MessageForm newMessage, Model model){
//        model.addAttribute("greetings", this.messageListService.getMessages());
//        return "home";
//    }
//
//    @PostMapping("/home")
//    public String addMessage(@ModelAttribute("newMessage") MessageForm messageForm, Model model){
//        messageListService.addMessage(messageForm.getText());
//        model.addAttribute("greetings", messageListService.getMessages());
//        messageForm.setText("");
//        return "home";
//    }



}
