package me.mocha.appjam.model.entiity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "beacon")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Beacon {

    @Id
    @Getter
    private String uuid;

    @Getter
    @Setter
    private Map<String, Double> position;

}
