package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne()
    @JoinColumn(name = "price_type_id")
    @JsonManagedReference
    Price priceType;
    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @JsonManagedReference
    Movie movie;
    @ManyToOne()
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    Room room;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "projection", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Reservation> reservations = new ArrayList<>();
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
