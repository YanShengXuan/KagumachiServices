package tw.com.services.kagumachi.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.util.JwtUtil;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.service.LoginService;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberRepository memberRepository;


//    @PostMapping("/register")
//    public ResponseEntity<?> registerMember(@RequestHeader("Authorization") String authToken) {
//        try {
//            String idToken = authToken.replace("Bearer ", "");
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//            String email = decodedToken.getEmail();
//
//            // 在資料庫建立用戶
//            Member newMember = new Member();
//            newMember.setEmail(email);
//            Member savedMember = loginService.addMember(newMember);
//
//            // 產生 JWT Token
//            String token = JwtUtil.generateToken(Long.valueOf(savedMember.getMemberid()));
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            response.put("memberId", savedMember.getMemberid());
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法驗證 Firebase Token");
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> request, @RequestHeader("Authorization") String token) {
//        try {
//            // Firebase Token
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.replace("Bearer ", ""));
//            String uid = decodedToken.getUid();
//            String email = decodedToken.getEmail();
//
//            //email 檢查用戶
//            Member member = memberRepository.findByEmail(email)
//                    .orElseThrow(() -> new IllegalArgumentException("帳號不存在"));
//
//            //回傳 token 和 memberId
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            response.put("memberId", member.getMemberid());
//
//            return ResponseEntity.ok(response);
//        } catch (FirebaseAuthException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無效的 Firebase Token");
//        }
//    }
//

    @PostMapping("/register")
    public ResponseEntity<?> addMember(@RequestBody Member member) {
        try {
            // 1️⃣ 創建會員
            Member savedMember = loginService.addMember(member);

            // 2️⃣ 生成 JWT Token
            String token = JwtUtil.generateToken(Long.valueOf(savedMember.getMemberid()));

            // 3️⃣ 返回 Token 和 MemberId
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
}
