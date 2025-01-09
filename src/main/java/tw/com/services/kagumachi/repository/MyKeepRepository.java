package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.MyKeep;

import java.util.List;

public interface MyKeepRepository extends JpaRepository<MyKeep, Integer> {
    List<MyKeep> findByMember_Memberid(Integer memberid);
}