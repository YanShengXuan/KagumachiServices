package tw.com.services.kagumachi.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
