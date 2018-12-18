package me.mocha.appjam.model.repository;

import me.mocha.appjam.model.entiity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DataRepository extends JpaRepository<Data, Long> {

    Optional<Data> findByDateTime(LocalDateTime dateTime);

}
