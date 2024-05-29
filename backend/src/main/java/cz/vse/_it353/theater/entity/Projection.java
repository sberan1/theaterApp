package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Projection {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    LocalDateTime startTime;
    @ManyToOne
    Price priceType;
    @ManyToOne
    Movie movie;
    @ManyToOne
    Room room;
}
