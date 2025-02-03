package tw.com.services.kagumachi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.chat.model.Autochat;
import tw.com.services.kagumachi.chat.service.AutochatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autochat")
public class AutochatController {

    @Autowired
    private AutochatService autochatService;

    @GetMapping
    public List<Autochat> getAllAutochats() {
        return autochatService.getAllAutochats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autochat> getAutochatById(@PathVariable Long id) {
        Optional<Autochat> autochat = autochatService.getAutochatById(id);
        return autochat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Autochat createAutochat(@RequestBody Autochat autochat) {
        return autochatService.saveAutochat(autochat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autochat> updateAutochat(@PathVariable Long id, @RequestBody Autochat autochatDetails) {
        Optional<Autochat> autochat = autochatService.getAutochatById(id);
        if (autochat.isPresent()) {
            Autochat updatedAutochat = autochat.get();
            updatedAutochat.setKeywords(autochatDetails.getKeywords());
            updatedAutochat.setAnswer(autochatDetails.getAnswer());
            updatedAutochat.setIsautochat(autochatDetails.getIsautochat());
            autochatService.saveAutochat(updatedAutochat);
            return ResponseEntity.ok(updatedAutochat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutochat(@PathVariable Long id) {
        if (autochatService.getAutochatById(id).isPresent()) {
            autochatService.deleteAutochat(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}