package com.subproject.board.repository;

import com.subproject.board.dto.auth.LoginDto;
import com.subproject.board.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserIdAndUserPassword(String userId, String userPassword);
}
