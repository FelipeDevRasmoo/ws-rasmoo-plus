package com.client.ws.rasmooplus.repositoy;

import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo,Long> {
}
