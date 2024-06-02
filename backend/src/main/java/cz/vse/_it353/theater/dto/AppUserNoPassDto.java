package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserNoPassDto {
    String username;
    String email;
    Integer phoneNumber;
}