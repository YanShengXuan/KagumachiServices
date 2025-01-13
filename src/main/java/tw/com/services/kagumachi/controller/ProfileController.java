package tw.com.services.kagumachi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.repository.MemberRepository;

import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public String getProfile(@RequestParam Integer memberid) {
        Optional<Member> optionalMember = memberRepository.findByMemberid(memberid);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("chinese_name", member.getRealname());
            jsonObject.put("gender", member.getGender().toString());
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            jsonObject.put("birthday", ft.format(member.getBirthday()));
            jsonObject.put("phone", member.getPhone());
            jsonObject.put("email", member.getEmail());
            jsonObject.put("zip_code", member.getPostalcode().toString());
            jsonObject.put("address", member.getAddress().replaceAll(".*[區鄉鎮]", ""));
            return jsonObject.toString();
        } else {
            return "Member not found";
        }
    }
}
