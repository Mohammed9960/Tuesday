package com.crud.crudwithchangelog;

import com.crud.crudwithchangelog.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLogin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userDetails;


    @PostMapping("/register")
    public ResponseEntity<MyUser> register(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/authenticated")
    public String authenticated(@RequestBody LoginForm form) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                form.email(), form.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetails.loadUserByUsername(form.email()));
        } else throw new UsernameNotFoundException("Invalid email or password");
    }
}
