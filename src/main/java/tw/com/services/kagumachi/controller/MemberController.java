package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/showAllMember")
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/updateMember/{id}")
    public Member updateMember(@PathVariable Integer id,@RequestBody Member updatedMember) {
        return memberService.updateMember(id, updatedMember);
    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().body("{\"message\":\"Member deleted successfully\"}");
    }

    @GetMapping("/searchMember")
    public List<Member> searchMembers(@RequestParam String query) {
        return memberService.searchMembers(query);
    }

    @GetMapping("/latest/{memberid}")
    public ResponseEntity<?> getLatestOrderByMemberId(@PathVariable Integer memberid) {
        Order latestOrder = memberService.getLatestOrderByMemberId(memberid);
        if (latestOrder != null) {
            return ResponseEntity.ok(latestOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
