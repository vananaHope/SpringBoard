package com.subproject.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_mst")
public class BoardEntity {
    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @ManyToOne
    @JoinColumn(name = "user_code")
    private UserEntity user;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "read_cnt")
    private int readCnt;

    @Column(name = "attach_no")
    private String attachNo;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "modify_by")
    private String modifyBy;

}
