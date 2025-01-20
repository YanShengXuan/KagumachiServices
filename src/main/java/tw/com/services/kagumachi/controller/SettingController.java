package tw.com.services.kagumachi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.Website;
import tw.com.services.kagumachi.repository.WebsiteRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private WebsiteRepository websiteRepository;

    @GetMapping
    public String getData() {
        JSONObject jsonObject = new JSONObject();
        Optional<Website> website = websiteRepository.findById(1);
        jsonObject.put("websitename", website.get().getWebsitename());
        jsonObject.put("aboutus", website.get().getAboutus());
        jsonObject.put("qa", website.get().getQa());
        jsonObject.put("logo", website.get().getLogo());
        jsonObject.put("footerinstagramlink", website.get().getFooterinstagramlink());
        jsonObject.put("footerfacebooklink", website.get().getFooterfacebooklink());
        return jsonObject.toString();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateWebsite(@RequestBody Map<String, String> payload) {
        String content = payload.get("content");
        String option = payload.get("option");

        Optional<Website> websiteOptional = websiteRepository.findById(1);
        if (websiteOptional.isPresent()) {
            Website website = websiteOptional.get();

            switch (option) {
                case "網站名稱":
                    website.setWebsitename(content);
                    break;
                case "關於我們":
                    website.setAboutus(content);
                    break;
                case "Footer Instagram Link":
                    website.setFooterinstagramlink(content);
                    break;
                case "Footer Facebook Link":
                    website.setFooterfacebooklink(content);
                    break;
                case "Q&A":
                    website.setQa(content);
                    break;
                case "Logo":
                    website.setLogo(content);
                    break;
                default:
                    return ResponseEntity.status(400).body("Invalid option");
            }

            websiteRepository.save(website);
            return ResponseEntity.ok("Website updated successfully");
        } else {
            return ResponseEntity.status(404).body("Website not found");
        }
    }
}
