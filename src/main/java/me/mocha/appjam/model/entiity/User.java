package me.mocha.appjam.model.entiity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    @Pattern(regexp = "^\\d{5}$")
    private String studentId;

    private String role;

}
