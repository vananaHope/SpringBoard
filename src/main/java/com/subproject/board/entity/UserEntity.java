package com.subproject.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "create_dt")
    private LocalDateTime createDt;

    @Column(name = "modify_dt")
    private LocalDateTime modifyDt;

}
