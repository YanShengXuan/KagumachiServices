package tw.com.services.kagumachi.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.chat.model.Autochat;

import java.util.List;

public interface AutochatRepository extends JpaRepository<Autochat, Long> {
    List<Autochat> findByKeywordsContaining(String keyword);
}
