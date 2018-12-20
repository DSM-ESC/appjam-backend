package me.mocha.appjam.model.repository;

import me.mocha.appjam.model.entiity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
