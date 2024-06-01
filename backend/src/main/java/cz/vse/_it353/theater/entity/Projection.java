package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @OneToMany
    List<Reservation> reservations;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
