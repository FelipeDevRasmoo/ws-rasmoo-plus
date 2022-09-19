package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.model.jpa.UserCredentials;

public interface UserDetailsService {

    UserCredentials loadUserByUsernameAndPass(String username, String pass);

    Object sendRecoveryCode(String email);

}
