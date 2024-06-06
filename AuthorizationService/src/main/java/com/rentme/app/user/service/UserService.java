package com.rentme.app.user.service;

import com.rentme.app.user.model.PrincipleUser;
import com.rentme.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(PrincipleUser::new)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("user not found with username %s", username)));
    }

}
