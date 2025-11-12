package com.example.demo.controller.task;

import com.example.demo.service.comment.CommentEntity;

//DB -> front
public record CommentDTO (
        Long id,
        Long tasks_id,
        Long tasks_order,
        String content,
        String writer
) {

    // Entity -> DTO
    public static CommentDTO toDTO(CommentEntity e){
        return new CommentDTO(
                e.id(), e.tasks_id(), e.tasks_order(), e.content(), e.writer()
        );
    }

}
