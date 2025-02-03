package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Home;
import tw.com.services.kagumachi.repository.HomeRepository;

import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public Optional<Home> getHomeByMemberId(int memberId) {
        return homeRepository.findByMemberid(memberId);
    }

    public Home saveOrUpdateHome(Home home) {
        Optional<Home> existingHome = homeRepository.findByMemberid(home.getMemberid());
        if (existingHome.isPresent()) {
            Home updatedHome = existingHome.get();
            updatedHome.setDoorwidth(home.getDoorwidth());
            updatedHome.setDoorheight(home.getDoorheight());
            updatedHome.setElevatorwidth(home.getElevatorwidth());
            updatedHome.setElevatorheight(home.getElevatorheight());
            updatedHome.setElevatordepth(home.getElevatordepth());
            updatedHome.setStairheight(home.getStairheight());
            updatedHome.setStairwidth(home.getStairwidth());

            updatedHome.setHomeid(existingHome.get().getHomeid());

            return homeRepository.save(updatedHome);
        } else {
            home.setHomeid(null);
            return homeRepository.save(home);
        }
    }
}
