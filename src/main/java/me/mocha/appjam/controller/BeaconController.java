package me.mocha.appjam.controller;

import me.mocha.appjam.exception.NotFoundException;
import me.mocha.appjam.model.entiity.Beacon;
import me.mocha.appjam.model.repository.BeaconRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beacon")
public class BeaconController {

    private final BeaconRepository beaconRepository;

    public BeaconController(BeaconRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    @GetMapping("/{uuid}")
    public Beacon getPosition(@PathVariable("uuid") String uuid) throws NotFoundException {
        return beaconRepository.findById(uuid).orElseThrow(() -> new NotFoundException("cannot find beacon data"));
    }

}
