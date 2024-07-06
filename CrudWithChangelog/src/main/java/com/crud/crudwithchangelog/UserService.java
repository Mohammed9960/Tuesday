package com.crud.crudwithchangelog;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> MyUser = userRepository.findByEmail(username);
        if (MyUser.isPresent()) {
            var user = MyUser.get();
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();

        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    private String [] getRoles(MyUser user) {
        if(user.getRole() == null) {
            return new String [] {"USER"};

        }
        return user.getRole().split(",");
    }
}

