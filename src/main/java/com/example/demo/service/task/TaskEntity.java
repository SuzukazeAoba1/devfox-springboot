package com.example.demo.service.task;

import org.apache.ibatis.annotations.Mapper;

public record TaskEntity(
        long id,
        String summary,
        String description,
        TaskStatus status
) {



}
