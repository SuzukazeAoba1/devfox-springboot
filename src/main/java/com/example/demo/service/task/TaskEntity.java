package com.example.demo.service.task;

public record TaskEntity(
        Long id,
        String summary,
        String description,
        TaskStatus status
) {
}
