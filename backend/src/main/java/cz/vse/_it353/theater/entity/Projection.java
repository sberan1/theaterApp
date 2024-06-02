package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JoinColumn(name = "price_type_id")
    Price priceType;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "projection")
    List<Reservation> reservations = new ArrayList<>();
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
