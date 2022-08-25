package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.repositoy.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userDetailsOpt = userDetailsRepository.findByUsername(username);

       if(userDetailsOpt.isPresent()) {
           return userDetailsOpt.get();
       }

        throw new NotFoudException("Usuário não encontrado");
    }
}
