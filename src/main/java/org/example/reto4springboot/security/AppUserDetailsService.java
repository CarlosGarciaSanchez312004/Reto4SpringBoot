package org.example.reto4springboot.security;


import org.example.reto4springboot.entities.UserDB;
import org.example.reto4springboot.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDB> currentUser = userRepository.findUserDBByEmail(username);

        if (currentUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // {noop} indica que la contraseña no está encriptada (para simplificar, como en el ejemplo)
        return User.withUsername(username)
                .password("{noop}" + currentUser.get().getPassword())
                .roles("ADMIN")
                .build();
    }
}