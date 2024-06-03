package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.AuthenticationResponse;
import cz.vse._it353.theater.dto.LoginUserDto;
import cz.vse._it353.theater.dto.RegisterUserDto;
import cz.vse._it353.theater.entity.AppUser;
import cz.vse._it353.theater.entity.Role;
import cz.vse._it353.theater.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication and user registration.
 */
@AllArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user.
     * @param input the registration details
     * @return an AuthenticationResponse containing the JWT token
     */
    public AuthenticationResponse signup(RegisterUserDto input) {
        var user = AppUser.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(Role.USER)
                .build();

        user = userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    /**
     * Authenticates a user and generates a JWT token.
     * @param input the login details
     * @return an AuthenticationResponse containing the JWT token
     */
    public AuthenticationResponse login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        AppUser user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}