package tw.com.services.kagumachi.repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.Member;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	List<Cart> findByMember_Memberid(@Param("memberId") Integer memberId);
    Optional<Cart> findByMember_MemberidAndProduct_ProductidAndProductColor_Colorsid(Integer memberid, Integer productid, Integer colorsid);

    List<Cart> findByMember_MemberidAndIspurchase(Integer memberid, Boolean ispurchase);


    int countByMember_Memberid(Integer memberid);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.member.memberid = :memberId")
    void deleteByMemberId(@Param("memberId") Integer memberId);
}
