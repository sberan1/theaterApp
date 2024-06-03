package cz.vse._it353.theater.dto;

import lombok.Data;

/**
 * Data Transfer Object for user login credentials.
 */
@Data
public class LoginUserDto {
    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;
}