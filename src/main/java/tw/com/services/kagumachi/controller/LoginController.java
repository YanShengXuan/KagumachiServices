package tw.com.services.kagumachi.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.service.EmailService;
import tw.com.services.kagumachi.util.JwtUtil;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.service.LoginService;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EmailService emailService;



    @PostMapping("/register")
    public ResponseEntity<?> addMember(@RequestBody Member member) {
        try {
            // 1️⃣ 檢查該 Email 是否已經註冊
            if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"該信箱已經被註冊！\"}");
            }

            // 2️⃣ 創建會員
            Member savedMember = loginService.addMember(member);

            // 3️⃣ 生成 JWT Token
            String token = JwtUtil.generateToken(Long.valueOf(savedMember.getMemberid()));

            // 4️⃣ 返回 Token 和 MemberId
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("memberId", savedMember.getMemberid());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        try {
            // 直接呼叫 LoginService 的 login 方法
            Member member = loginService.login(email, password);

            // 生成 JWT
            String token = JwtUtil.generateToken(Long.valueOf(member.getMemberid()));

            // 回傳 token 和 memberId
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("memberId", member.getMemberid());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        try {
            String token = loginService.saveResetToken(email);

            String resetLink = "http://localhost:5173/reset-password/" + token;
            emailService.sendEmail(email, "密碼重設", "請點擊以下連結來重設密碼: " + resetLink);

            return ResponseEntity.ok(Map.of("message", "重設密碼連結已發送至您的信箱！"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");

        Optional<Member> memberOpt = loginService.getMemberByResetToken(token);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "重設連結無效或已過期"));
        }

        Member member = memberOpt.get();
        member.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        loginService.clearResetToken(member);

        return ResponseEntity.ok(Map.of("message", "密碼重設成功！"));
    }
}
