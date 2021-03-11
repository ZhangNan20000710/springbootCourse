package com.example.demo.dao;

import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ITagsDao extends JpaRepository<Tags,String> , JpaSpecificationExecutor<Tags> {
    Set<Tags> findByTagNameLike(String name);

    @Query("select t.courses from Tags as t where t.tagId = :id ")
    List<Courses> find(@Param("id") String id);
}
