package com.subproject.board.service;

import com.subproject.board.common.exception.CustomException;
import com.subproject.board.common.exception.ErrorCode;
import com.subproject.board.dto.auth.LoginDto;
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

    public LoginDto.Response login(String id, String password){

        UserEntity user = userRepository.findByUserIdAndUserPassword(id, password)
                .orElseThrow( () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return LoginDto.Response.builder()
                .userCode(user.getUserCode())
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .build();
    }

    public SignUpDto.Response signup(SignUpDto.Request request){

        UserEntity userEntity;

        Optional<UserEntity> optionalUserEntity = userRepository.findOneByUserId(request.getUserId());

        if(optionalUserEntity.isPresent() && optionalUserEntity.get().getUserPassword() != null){
            throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }

        if(optionalUserEntity.isEmpty()){
            userEntity = UserEntity.builder()
                    .userId(request.getUserId())
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .userNickname(request.getUserNickname())
                    .userEmail(request.getUserEmail())
                    .build();
            userRepository.save(userEntity);

        }

        return SignUpDto.Response.builder()
                .userId(request.getUserId())
                .userNickname(request.getUserNickname())
                .build();

    }

}
