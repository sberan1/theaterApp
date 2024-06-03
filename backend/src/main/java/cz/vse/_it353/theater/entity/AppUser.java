package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity representing an application user.
 */
@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The unique username of the user.
     */
    @Column(unique = true)
    String username;

    /**
     * The password of the user.
     */
    String password;

    /**
     * The unique email address of the user.
     */
    @Column(unique = true)
    String email;

    /**
     * The unique phone number of the user.
     */
    @Column(unique = true)
    Integer phoneNumber;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    Role role;

    /**
     * The balance of the user.
     */
    BigDecimal balance = BigDecimal.ZERO;

    /**
     * The list of reservations made by the user.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Reservation> reservations = new ArrayList<>();

    /**
     * Returns the authorities granted to the user.
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true if the account is non-expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * @return true if the account is non-locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}