package cz.vse._it353.theater.entity;

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
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    AppUser user;
    @ManyToOne
    Projection projection;
    boolean paid;
    @Min(1)
    @Max(100)
    Integer discount;
    @Version
    private Long version;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    @ManyToMany
    private List<Seat> seats;
    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
