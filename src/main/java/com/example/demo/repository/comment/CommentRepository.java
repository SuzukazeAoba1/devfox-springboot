package com.example.demo.repository.comment;

import com.example.demo.service.comment.CommentEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Select("SELECT COUNT(*) FROM comment Where tasks_id = #{id};")
    int countByTaskId(@Param("id") long taskId);

    @Select("SELECT * FROM comment Where tasks_id = #{id};")
    List<CommentEntity> selectByTaskId(@Param("id") long taskId);

    @Insert("""
            INSERT INTO comment (tasks_id, tasks_order, content, writer)
            VALUES (#{comment.tasks_id}, #{comment.tasks_order}, #{comment.content}, #{comment.writer})
            """)
    void insert(@Param("comment") CommentEntity newEntity);

    @Delete("DELETE FROM comment WHERE tasks_id = #{taskId} AND tasks_order = #{commentOrder}")
    void delete(@Param("taskId") long taskId, @Param("commentOrder") long commentOrder);
}
