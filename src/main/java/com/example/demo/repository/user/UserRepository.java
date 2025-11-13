package com.example.demo.repository.user;

import com.example.demo.service.user.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM Users Where loginId = #{loginId};")
    Optional<UserEntity> findByLoginId(@Param("loginId") String loginId);

    @Insert("""
            INSERT INTO Users (loginId, password, nickname, role)
            VALUES (#{entity.loginId}, #{entity.password}, #{entity.nickname}, #{entity.role});
            """)
    void addUser(@Param("entity") UserEntity entity);

//    @Update("""
//            UPDATE Users
//            SET
//                summary     = #{task.summary},
//                description = #{task.description},
//                status      = #{task.status}
//            WHERE
//                id          = #{task.id}
//            """)
//    void update(@Param("Users") TaskEntity entity);
//
//    @Delete("DELETE FROM Users WHERE id = #{taskId}")
//    void delete(@Param("taskId") long id);

}
