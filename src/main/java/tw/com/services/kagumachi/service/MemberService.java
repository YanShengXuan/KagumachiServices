package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.repository.OrderRepository;


import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    public void deleteMember(Integer id){
        memberRepository.deleteById(id);
    }

    public List<Member> searchMembers( String query){
        return memberRepository.findByEmailContainingOrPhoneContaining(query,query);
    }


    public Order getLatestOrderByMemberId(Integer memberid) {
        return orderRepository.findLatestOrderByMemberId(memberid)
                .orElse(null);
    }


}
