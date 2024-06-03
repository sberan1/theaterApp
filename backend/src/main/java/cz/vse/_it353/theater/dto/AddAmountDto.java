package cz.vse._it353.theater.dto;

import lombok.Data;

/**
 * Data Transfer Object for adding an amount to a user's balance.
 */
@Data
public class AddAmountDto {
    /**
     * The amount to be added.
     */
    double amount;
}