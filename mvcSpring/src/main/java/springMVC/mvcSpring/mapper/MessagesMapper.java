package springMVC.mvcSpring.mapper;

import org.apache.ibatis.annotations.*;
import springMVC.mvcSpring.model.ChatMessage;

import java.util.List;

@Mapper
public interface MessagesMapper {

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getAllMessages();

    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int addMessage(ChatMessage chatMessage);

    @Delete("DELETE FROM ChatMessage WHERE messageId=#{messageId}")
    void deleteMessage(Integer messageId);
}
