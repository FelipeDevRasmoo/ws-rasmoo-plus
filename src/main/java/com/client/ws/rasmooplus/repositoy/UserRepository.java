package com.client.ws.rasmooplus.repositoy;

import com.client.ws.rasmooplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
