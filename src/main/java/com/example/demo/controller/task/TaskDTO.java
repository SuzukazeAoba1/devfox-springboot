package com.example.demo.controller.task;

import com.example.demo.service.task.TaskEntity;

//DB -> front
public record TaskDTO (
        long id,
        String summary,
        String description,
        String status
) {

    // Entity -> DTO
    public static TaskDTO toDTO(TaskEntity entity){
        return new TaskDTO(
                entity.id(),
                entity.summary(),
                entity.description(),
                entity.status().name()
        );
    }

}
