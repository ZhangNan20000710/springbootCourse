package com.example.demo.dao;

import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Entity.CoursesUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICoursesUsersDao extends JpaRepository<CoursesUsers,String> , JpaSpecificationExecutor<CoursesUsers> {
    Set<CoursesUsers> findByUserUserId(String UserId);
    Set<CoursesUsers> findByCourseCourseId(String courseId);
    Set<CoursesUsers> findByUserUserIdAndCourseCourseId(String userId,String courseId);
    void deleteByCourseCourseIdAndUserUserId(String courseId,String userId);
}
