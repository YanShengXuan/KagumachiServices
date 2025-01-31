package tw.com.services.kagumachi.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.chat.model.Autochat;
import tw.com.services.kagumachi.chat.repository.AutochatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutochatService {

    @Autowired
    private AutochatRepository autochatRepository;

    public List<Autochat> getAllAutochats() {
        return autochatRepository.findAll();
    }

    public Optional<Autochat> getAutochatById(Long id) {
        return autochatRepository.findById(id);
    }

    public Autochat saveAutochat(Autochat autochat) {
        return autochatRepository.save(autochat);
    }

    public void deleteAutochat(Long id) {
        autochatRepository.deleteById(id);
    }
}