package com.example.demo.repository.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

//    @Select("SELECT COUNT(*) FROM tasks;")
//    int selectAllCounter();
//
//    @Select("SELECT * FROM tasks LIMIT #{count} OFFSET #{offset}")
//    List<TaskEntity> selectList(@Param("offset") int offset, @Param("count") int count);
//
//    @Select("SELECT id, summary, description, status FROM tasks Where id = #{taskId};")
//    Optional<TaskEntity> selectById(@Param("taskId") long taskId);
//
//    @Insert("""
//            INSERT INTO tasks (summary, description, status)
//            VALUES (#{task.summary}, #{task.description}, #{task.status})
//            """)
//    void insert(@Param("task") TaskEntity newEntity);
//
//    @Update("""
//            UPDATE tasks
//            SET
//                summary     = #{task.summary},
//                description = #{task.description},
//                status      = #{task.status}
//            WHERE
//                id          = #{task.id}
//            """)
//    void update(@Param("task") TaskEntity entity);
//
//    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
//    void delete(@Param("taskId") long id);

}
