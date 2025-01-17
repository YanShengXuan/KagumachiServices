package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.Util.JwtUtil;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.service.LoginService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public Member addMember(@RequestBody Member member) {
        return loginService.addMember(member);
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
