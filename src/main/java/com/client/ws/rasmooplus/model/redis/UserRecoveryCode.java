package com.client.ws.rasmooplus.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@RedisHash("recoveryCode")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecoveryCode {

    @Id
    private String id;

    @Indexed
    @Email(message = "inválido")
    private String email;

    private String code;

    private LocalDateTime creationDate = LocalDateTime.now();

}
