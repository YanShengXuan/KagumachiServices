package tw.com.services.kagumachi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

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


    @Modifying
    @Transactional
    @Query("DELETE FROM MyKeep m WHERE m.member.memberid = :memberId")
    void deleteByMemberId(@Param("memberId") Integer memberId);

}