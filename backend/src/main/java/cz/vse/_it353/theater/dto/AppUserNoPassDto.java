package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for transferring user details without the password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserNoPassDto {
    /**
     * The username of the user.
     */
    String username;

    /**
     * The email of the user.
     */
    String email;

    /**
     * The phone number of the user.
     */
    Integer phoneNumber;

    /**
     * The balance of the user's account.
     */
    BigDecimal balance;
}