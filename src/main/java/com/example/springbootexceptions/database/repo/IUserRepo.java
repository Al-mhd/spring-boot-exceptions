package com.example.springbootexceptions.database.repo;

import com.example.springbootexceptions.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Integer> {
    @Query(value = "select u from user u where username = :x", nativeQuery = true)
    UserEntity myGetUserByName(@Param("x") String x);

    @Query(value = "select u from user u where username like :x", nativeQuery = true)
    List<UserEntity> myGetUserLikeName(@Param("x") String x);
}
