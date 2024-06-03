package cz.vse._it353.theater.config;

import cz.vse._it353.theater.filter.JwtFilter;
import cz.vse._it353.theater.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class for ensuring that only authenticated users can use the allowed content
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailService userDetailService;
    private final JwtFilter jwtFilter;

    /**
     * Constructor of SecurityConfig
     * @param userDetailService
     * @param jwtFilter
     */
    public SecurityConfig(UserDetailService userDetailService, JwtFilter jwtFilter) {
        this.userDetailService = userDetailService;
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configures the security filter chain for the application
     * @param http HttpSecurity configuration object
     * @return the configured SecurityFilterChain
     * @throws Exception if there is an error in the security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req ->req.requestMatchers("/login",
                                        "/register",
                                        "/rooms" ,
                                        "/movies",
                                        "/projection",
                                        "/projections",
                                        "/prices",
                                        "/branches",
                                        "/health-check")
                                .permitAll()
                                .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/reservation").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/reservations/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailService)
                .sessionManagement( sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Provides the password encoder bean
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the authetication manager bean
     * @param config the auth configuration
     * @return the auth manager
     * @throws Exception if there is an error retrieving the auth manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
