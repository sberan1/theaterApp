package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a branch.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    /**
     * The unique identifier for the branch.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The unique name of the branch.
     */
    @Column(unique = true)
    String name;

    /**
     * The address of the branch.
     */
    String address;

    /**
     * The list of rooms in the branch.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Room> rooms = new ArrayList<>();

    /**
     * The version of the branch entity, used for optimistic locking.
     */
    @Version
    Long version;

    /**
     * The timestamp of when the branch was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the branch was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;
}