package com.koen.quize.security;

import com.koen.quize.model.AuthUser;
import com.koen.quize.repository.AuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService{
    @Autowired
    AuthUserRepo authPersonRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser authPerson = authPersonRepository.findByEmail(email);
        if (authPerson == null) {
            throw new UsernameNotFoundException("User with username: " + email + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(authPerson);
        return jwtUser;
    }
}
