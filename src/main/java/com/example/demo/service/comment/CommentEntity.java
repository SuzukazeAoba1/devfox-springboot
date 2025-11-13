package com.example.demo.service.comment;

public record CommentEntity(
        Long id,
        Long tasks_id,
        Long tasks_order,
        String content,
        String loginId,
        String nickname
) {
}
