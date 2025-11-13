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

}
