package tw.com.services.kagumachi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.repository.MemberRepository;

import java.text.SimpleDateFormat;
import java.util.Map;
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
            jsonObject.put("chinese_name", member.getRealname() != null ? member.getRealname() : "");
            jsonObject.put("gender", member.getGender() != null ? member.getGender().toString() : "");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            jsonObject.put("birthday", member.getBirthday() != null ? ft.format(member.getBirthday()) : "");
            jsonObject.put("phone", member.getPhone() != null ? member.getPhone() : "");
            jsonObject.put("email", member.getEmail() != null ? member.getEmail() : "");
            jsonObject.put("zip_code", member.getPostalcode() != null ? member.getPostalcode().toString() : "");
            jsonObject.put("address", member.getAddress() != null ? member.getAddress().replaceAll(".*[市區鄉鎮]", "") : "");
            return jsonObject.toString();
        } else {
            return "Member not found";
        }
    }

    @PutMapping
    public String updateProfile(@RequestBody Map<String, Object> payload) {
        System.out.println(payload);
        Integer memberid = (Integer) payload.get("memberid");
        Optional<Member> optionalMember = memberRepository.findByMemberid(memberid);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setRealname((String) payload.get("chinese_name"));
            member.setPassword(BCrypt.hashpw((String) payload.get("password"), BCrypt.gensalt()));
            member.setGender(Integer.valueOf((String) payload.get("gender")));
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            try {
                member.setBirthday(ft.parse((String) payload.get("birthday")));
            } catch (Exception e) {
                return "Invalid date format";
            }
            member.setPhone((String) payload.get("phone"));
            member.setEmail((String) payload.get("email"));
            member.setPostalcode(Integer.valueOf((String) payload.get("zip_code")));
            member.setCity((String) payload.get("county"));
            member.setAddress((String) payload.get("address"));
            memberRepository.save(member);
            return "Update successful";
        } else {
            return "Member not found";
        }
    }

}
