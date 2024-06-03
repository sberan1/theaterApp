package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for sending authentication responses.
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    /**
     * The access token for the authenticated user.
     */
    private String accessToken;
}