package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    AppUser user;
    @ManyToOne()
    @JoinColumn(name = "projection_id")
    @JsonManagedReference
    Projection projection;
    @ManyToMany
    @JoinTable(
            name = "reservation_seats",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seats_id")
    )
    @JsonManagedReference
    List<Seat> seats = new ArrayList<>();
    boolean paid;
    @Min(0)
    @Max(100)
    Integer discount;
    @Version
    private Long version;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
