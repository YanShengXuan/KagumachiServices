package tw.com.services.kagumachi.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.chat.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderidAndReceiverid(Long senderid, Long receiverid);

    List<Message> findBySenderidOrReceiverid(Long userId, Long userId1);

    List<Message> findByReceiveridAndIsbackread(Long receiverid, boolean isbackread);
}