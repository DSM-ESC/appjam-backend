package me.mocha.appjam.model.repository;

import me.mocha.appjam.model.entiity.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeaconRepository extends JpaRepository<Beacon, String> {

}
