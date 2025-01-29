package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.repository.MemberRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    MemberRepository memberRepository;

    //註冊
    public Member addMember(Member member) {
        boolean exists = memberRepository.existsByEmail(member.getEmail());
        if (exists) {
            throw new IllegalArgumentException("帳號已存在，請登入");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));

        return memberRepository.save(member);
    }



    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("帳號不存在"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("密碼錯誤");
        }
        return member;
    }

    public String saveResetToken(String email) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (memberOpt.isEmpty()) {
            throw new IllegalArgumentException("此信箱尚未註冊");
        }

        Member member = memberOpt.get();
        String token = UUID.randomUUID().toString();
        member.setReset(token);
        memberRepository.save(member);

        return token;
    }


    public Optional<Member> getMemberByResetToken(String token) {
        return memberRepository.findByReset(token);
    }


    public void clearResetToken(Member member) {
        member.setReset(null);
        memberRepository.save(member);
    }


}
