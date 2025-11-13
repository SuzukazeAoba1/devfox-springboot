package com.example.demo.repository.comment;

import com.example.demo.service.comment.CommentEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Select("SELECT COUNT(*) FROM comments Where tasks_id = #{id};")
    int countByTaskId(@Param("id") long taskId);

    @Select("SELECT * FROM comments Where tasks_id = #{id};")
    List<CommentEntity> selectByTaskId(@Param("id") long taskId);

    @Insert("""
            INSERT INTO comments (tasks_id, tasks_order, content, loginId, nickname)
            VALUES (#{comment.tasks_id}, #{comment.tasks_order}, #{comment.content}, #{comment.loginId}, #{comment.nickname})
            """)
    void insert(@Param("comment") CommentEntity newEntity);

    @Delete("DELETE FROM comments WHERE tasks_id = #{taskId} AND tasks_order = #{commentOrder}")
    void delete(@Param("taskId") long taskId, @Param("commentOrder") long commentOrder);
}
