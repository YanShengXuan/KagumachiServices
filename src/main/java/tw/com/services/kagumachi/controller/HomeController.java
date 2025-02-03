package tw.com.services.kagumachi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.Home;
import tw.com.services.kagumachi.service.HomeService;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/{memberId}")
    public ResponseEntity<Home> getHomeDimensions(@PathVariable int memberId) {
        Optional<Home> home = homeService.getHomeByMemberId(memberId);
        if (home.isPresent()) {
            return ResponseEntity.ok(home.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<Home> saveOrUpdateHomeDimensions(@PathVariable int memberId, @RequestBody Home home) {
        home.setMemberid(memberId);
        Home savedHome = homeService.saveOrUpdateHome(home);
        return ResponseEntity.ok(savedHome);
    }
}
