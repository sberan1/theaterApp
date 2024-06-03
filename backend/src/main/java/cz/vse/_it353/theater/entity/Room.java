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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    Integer capacity;
    @ManyToOne()
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    Branch branch;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Projection> projections = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Seat> seats;
    @Version
    Long version;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    public Room(String id) {
        this.id = id;
    }
}
