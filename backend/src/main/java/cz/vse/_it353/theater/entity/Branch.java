package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
public class Branch {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique = true)
    String name;
    String address;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Room> rooms = new ArrayList<>();
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
