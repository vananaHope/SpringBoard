package com.subproject.board.service;

import com.subproject.board.common.config.Authority;
import com.subproject.board.common.exception.CustomException;
import com.subproject.board.common.exception.ErrorCode;
import com.subproject.board.dto.auth.SignUpDto;
import com.subproject.board.entity.UserEntity;
import com.subproject.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpDto.Response signup(SignUpDto.Request request){

        Optional<UserEntity> optionalUser = userRepository.findOneByUserId(request.getUserId());

        if(optionalUser.isPresent() && optionalUser.get().getUserPassword() != null){
            throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }

        UserEntity user = optionalUser.orElseGet(()-> UserEntity.builder()
                .userId(request.getUserId())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userEmail(request.getUserEmail())
                .userNickname(request.getUserNickname())
                .authority(Authority.ROLE_USER)
                .build());

        userRepository.save(user);

        return SignUpDto.Response.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .build();

    }

}
