package ru.gb.springdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.repository.UserRepository;
import ru.gb.springdemo.model.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.gb.springdemo.model.User user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getLogin(), user.getPassword(), List.of(
                new SimpleGrantedAuthority(user.getRole())
        ));
    }
}
