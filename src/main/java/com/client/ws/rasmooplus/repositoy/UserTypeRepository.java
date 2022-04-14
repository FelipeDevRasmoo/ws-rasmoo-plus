package com.client.ws.rasmooplus.repositoy;

import com.client.ws.rasmooplus.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
