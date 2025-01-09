package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.MyKeep;

import java.util.List;
@Repository
public interface MyKeepRepository extends JpaRepository<MyKeep, Integer> {
    List<MyKeep> findByMember_Memberid(Integer memberid);
}