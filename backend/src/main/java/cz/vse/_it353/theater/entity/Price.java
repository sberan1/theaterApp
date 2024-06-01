package cz.vse._it353.theater.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    BigDecimal value;
    public Price(String id) {
        this.id = id;
    }
}
