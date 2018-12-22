package me.mocha.appjam.controller;

import lombok.extern.slf4j.Slf4j;
import me.mocha.appjam.annotation.CurrentUser;
import me.mocha.appjam.model.entiity.User;
import me.mocha.appjam.payload.request.action.ApplyActionRequest;
import me.mocha.appjam.scheduler.JobScheduler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/action")
public class ActionController {

    @PostMapping
    public void apply(@CurrentUser User user, @RequestBody ApplyActionRequest request) {
        JobScheduler.force = true;
        regulate(request.getType(), request.getPower());
        //TODO send action to iot server
    }

    @GetMapping
    public Map<String, Boolean> getStatus() {
        Map<String, Boolean> result = new HashMap<>();
        result.put("humidifier", JobScheduler.humidifier);
        result.put("cleaner", JobScheduler.cleaner);
        result.put("window", JobScheduler.window);
        return result;
    }

    private void regulate(int type, boolean power) {
        switch (type) {
            case 1:
                JobScheduler.humidifier = power;
                log.info("turn " + (power ? "on" : "off") + " the humidifier");
                break;
            case 2:
                JobScheduler.cleaner = power;
                log.info("turn " + (power ? "on" : "off") + " the cleaner");
                break;
            case 3:
                JobScheduler.window = power;
                log.info((power ? "open" : "close") + " the window");
                break;
            default:
                break;
        }
    }

}
