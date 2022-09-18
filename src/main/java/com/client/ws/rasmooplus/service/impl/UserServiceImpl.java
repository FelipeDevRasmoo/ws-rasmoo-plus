package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.jpa.User;
import com.client.ws.rasmooplus.model.jpa.UserType;
import com.client.ws.rasmooplus.model.redis.RecoveryCode;
import com.client.ws.rasmooplus.repositoy.jpa.UserRepository;
import com.client.ws.rasmooplus.repositoy.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.repositoy.redis.RecoveryCodeRepository;
import com.client.ws.rasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    private final RecoveryCodeRepository recoveryCodeRepository;

    UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository, RecoveryCodeRepository recoveryCodeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.recoveryCodeRepository = recoveryCodeRepository;
    }

    @Override
    public User create(UserDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("id deve ser nulo");
        }

        var userTypeOpt = userTypeRepository.findById(dto.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoudException("userTypeId não encontrado");
        }

        UserType userType = userTypeOpt.get();
        User user = UserMapper.fromDtoToEntity(dto, userType, null);
        return userRepository.save(user);
    }

    @Override
    public Object sendRecoveryCode(String email) {

        String code = String.format("%04d", new Random().nextInt(10000));

        recoveryCodeRepository.save(RecoveryCode.builder().code(code).build());
        return null;
    }
}
