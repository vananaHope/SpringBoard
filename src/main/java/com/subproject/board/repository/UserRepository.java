package com.subproject.board.repository;

import com.subproject.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUserIdAndUserPassword(String userId, String userPassword);

    Optional<UserEntity> findOneByUserId(String userId);
}
