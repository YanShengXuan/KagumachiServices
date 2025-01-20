package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.model.Website;
import tw.com.services.kagumachi.repository.WebsiteRepository;

import java.util.Optional;

@RestController
@RequestMapping("/navbar")
public class NavbarFooterController {
    @Autowired
    private WebsiteRepository websiteRepository;

    @GetMapping
    public ResponseEntity<Website> getWebsiteInfo() {
        Optional<Website> website = websiteRepository.findById(1);
        return website.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
