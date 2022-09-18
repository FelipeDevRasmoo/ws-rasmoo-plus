package com.client.ws.rasmooplus.repositoy.jpa;

import com.client.ws.rasmooplus.model.jpa.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType,Long> {

    Optional<SubscriptionType> findByProductKey(String productKey);

}
