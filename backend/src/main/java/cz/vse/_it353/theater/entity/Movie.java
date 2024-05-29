package cz.vse._it353.theater.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    Integer durationInMinutes;
    String rating;
    String genre;
}
