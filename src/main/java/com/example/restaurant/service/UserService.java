package com.example.restaurant.service;

import com.example.restaurant.config.CustomUserDetails;
import com.example.restaurant.dto.UserDto;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateUser(UserDto userDto, Long userId) {
        userRepository.updateUser(userDto, userId);
        updateSecurityContext(userDto.getUserPhoneNumber());
    }

    public void saveUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserRole("ROLE_USER");
        userRepository.insertUser(user);
    }

    private void updateSecurityContext(String userPhoneNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            userDetails.setUserPhoneNumber(userPhoneNumber);
        }
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                authentication.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    public Optional<UserDto> findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthority(String roles) {
        return List.of(new SimpleGrantedAuthority(roles));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByPhoneNumber(username) //Поиск по номеру телефона пользователя
                .map(user -> new CustomUserDetails(
                        user.getUserId(),
                        user.getUserName(),
                        user.getUserPassword(),
                        getGrantedAuthority(user.getUserRole()))
                )
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }
}
