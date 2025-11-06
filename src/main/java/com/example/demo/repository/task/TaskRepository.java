package com.example.demo.repository.task;

import com.example.demo.service.task.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    @Select("SELECT id, summary, description, status FROM tasks;")
    List<TaskEntity> select();

    @Select("SELECT id, summary, description, status FROM tasks Where id = #{taskId};")
    Optional<TaskEntity> selectById(@Param("taskId") long taskId);
}
