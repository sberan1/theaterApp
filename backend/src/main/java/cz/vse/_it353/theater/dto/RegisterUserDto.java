package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for registering a new user.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDto {
    /**
     * The username of the new user.
     */
    private String username;

    /**
     * The password of the new user.
     */
    private String password;

    /**
     * The email of the new user.
     */
    private String email;

    /**
     * The phone number of the new user.
     */
    private Integer phoneNumber;
}