package com.client.ws.rasmooplus.repositoy.redis;

import com.client.ws.rasmooplus.model.redis.RecoveryCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryCodeRepository extends CrudRepository<RecoveryCode, String> {
}
