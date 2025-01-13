package tw.com.services.kagumachi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.ShipRate;
import tw.com.services.kagumachi.repository.ShipRateRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/shiprate")
public class ShipRateController {
    @Autowired
    private ShipRateRepository shipRateRepository;

    @GetMapping
    public String getAllShipRates() {
        List<ShipRate> shipRates = shipRateRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for (ShipRate shipRate : shipRates) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("region", shipRate.getRegion());
            jsonObject.put("rate", shipRate.getRate());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @PutMapping
    public String updateShipRate(@RequestBody Map<String, Object> payload) {
        String region = (String) payload.get("region");
        Optional<ShipRate> optionalShipRate = shipRateRepository.findByRegion(region);

        if (optionalShipRate.isPresent()) {
            ShipRate shipRate = optionalShipRate.get();
            shipRate.setRate((Integer) payload.get("rate"));
            shipRateRepository.save(shipRate);
            return "Update successful";
        } else {
            return "ShipRate not found";
        }
    }
}
