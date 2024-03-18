package com.subproject.board.service;

import com.subproject.board.dto.auth.LoginDto;
import com.subproject.board.entity.UserEntity;
import com.subproject.board.repository.UserRepository;
import jakarta.validation.constraints.Null;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public LoginDto.Response login(String id, String password){

        UserEntity user = userRepository.findByUserIdAndUserPassword(id, password);

        return LoginDto.Response.builder()
                .userCode(user.getUserCode())
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .build();
    }

}
