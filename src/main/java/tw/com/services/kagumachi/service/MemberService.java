package tw.com.services.kagumachi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.*;


import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MyKeepRepository myKeepRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Integer id ,Member updatedMember) {
        return memberRepository.findById(id).map(existingMember -> {
            existingMember.setRealname(updatedMember.getRealname());
            existingMember.setPassword(updatedMember.getPassword());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPhone(updatedMember.getPhone());
            existingMember.setPostalcode(updatedMember.getPostalcode());
            existingMember.setCity(updatedMember.getCity());
            existingMember.setAddress(updatedMember.getAddress());
            existingMember.setRegistrationdate(updatedMember.getRegistrationdate());

            return memberRepository.save(existingMember);
        }).orElseThrow(() -> new RuntimeException("Member not found with id " + id));
    }
    @Transactional
    public void deleteMember(Integer id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id " + id));

        List<Integer> orderIds = orderRepository.findOrderIdsByMemberId(id);

        if (!orderIds.isEmpty()) {
            orderDetailRepository.deleteByOrderIds(orderIds);
        }
        orderRepository.deleteByMemberId(id);

        cartRepository.deleteByMemberId(id);
        reviewRepository.deleteByMemberId(id);
        myKeepRepository.deleteByMemberId(id);

        memberRepository.delete(member);

    }

    public List<Member> searchMembers( String query){
        return memberRepository.findByEmailContainingOrPhoneContaining(query,query);
    }


    public Order getLatestOrderByMemberId(Integer memberid) {
        return orderRepository.findLatestOrderByMemberId(memberid)
                .orElse(null);
    }


}
