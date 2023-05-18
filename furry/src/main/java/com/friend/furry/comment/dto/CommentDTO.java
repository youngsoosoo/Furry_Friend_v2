package com.friend.furry.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long rid;
    private Long pid;
    private Long mid;
    private String nickname;
    private String email;
    private int grade;
    private String text;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
