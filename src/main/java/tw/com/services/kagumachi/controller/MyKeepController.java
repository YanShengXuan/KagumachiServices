package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.model.MyKeep;
import tw.com.services.kagumachi.repository.MyKeepRepository;

import java.util.List;

@RestController
@RequestMapping("/mykeep")
public class MyKeepController {
    @Autowired
    private MyKeepRepository myKeepRepository;

    @GetMapping
    public List<MyKeep> getAllMyKeeps() {
        List<MyKeep> myKeepsList = myKeepRepository.findAll();
        System.out.println("Returned MyKeeps: " + myKeepsList.size());
        return myKeepsList;
    }
}
