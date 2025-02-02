package tw.com.services.kagumachi.repository;

import tw.com.services.kagumachi.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeRepository extends JpaRepository<Home, Integer> {
    Optional<Home> findByMemberid(int memberid);
}
