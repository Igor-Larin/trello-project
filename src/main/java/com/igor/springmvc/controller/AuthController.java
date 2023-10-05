package com.igor.springmvc.controller;

import com.igor.springmvc.DTO.UserResponse;
import com.igor.springmvc.DTO.AuthRequest;
import com.igor.springmvc.model.User;
import com.igor.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@CrossOrigin(value = {"http://localhost:8080", "https://localhost:8080"})
@RestController
public class AuthController {
    private UserService userService;
    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtEncoder(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody AuthRequest authRequest)
    {
        boolean isCreated = userService.create(authRequest);
        return isCreated ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

            Instant now = Instant.now();
            long expiry = 36000L;
            JwtClaimsSet claims =
                    JwtClaimsSet.builder()
                            .issuer("localhost:8081")
                            .issuedAt(now)
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(authentication.getName())
                            .build();

            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return new ResponseEntity<>(new UserResponse(authentication.getName(), token), HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.readAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/check/{username}")
    public ResponseEntity<?> checkUser(@PathVariable("username") String username) {
        try {
            userService.checkName(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
