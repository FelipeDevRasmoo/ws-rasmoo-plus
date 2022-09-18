package com.client.ws.rasmooplus.repositoy.jpa;

import com.client.ws.rasmooplus.model.jpa.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
