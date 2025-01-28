package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.MyKeep;
import tw.com.services.kagumachi.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyKeepRepository extends JpaRepository<MyKeep, Integer> {
    List<MyKeep> findByMember_Memberid(Integer memberid);
    void deleteByMember_MemberidAndProduct_Productid(Integer memberid, Integer productid);

    Optional<MyKeep> findByMemberAndProduct(Member member, Product product);
}