package com.subproject.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_reply")
public class ReplyEntity {

    @Id
    @Column(name = "reply_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long replyNo;

    @ManyToOne
    @JoinColumn(name = "board_no")
    public BoardEntity board;

    @Column(name = "reply_content")
    public String replyContent;

    @Column(name = "group_id")
    public String groupId;

    @Column(name = "parent_id")
    public String parentId;
    @Column(name = "depth")
    public String depth;
    @Column(name = "create_dt")
    public LocalDateTime createDt;
    @Column(name = "modify_dt")
    public LocalDateTime modifyDt;
    @Column(name = "create_by")
    public String createBy;
    @Column(name = "modify_by")
    public String modifyBy;
}
