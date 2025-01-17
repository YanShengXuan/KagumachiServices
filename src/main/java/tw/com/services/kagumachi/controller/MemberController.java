package tw.emma.kaguv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.emma.kaguv2.model.Member;
import tw.emma.kaguv2.service.MemberService;

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
}
