package com.example.demo.dao;

import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ICoursesDao extends JpaRepository<Courses,String>, JpaSpecificationExecutor<Courses> {
    Page<Courses> findByCourseNameLikeOrUserNicknameLike(String courseName,
                                                       String nickname,
                                                       Pageable page);
    List<Courses> findByCourseName(String name);
    Page<Courses> findByUserUserId(String userId, Pageable page);
    Page<Courses> findByTypeTypeId(String typeId, Pageable page);
    List<Courses> findByTypeTypeId(String typeId);
    /*根据选课人数降序排列*/
    @Query("select c,count(c) from CoursesUsers as cu left join Courses as c on cu.course.courseId=c.courseId group by c order by count(c) desc")
    List<Object> findBySortUserNumber();

    @Query("select cur from Courses as cur join Comments as com on cur.courseId = com.course.courseId group by cur order by count(cur) Desc,cur.updateTime desc")
    List<Object> findBySourCommentNumber();

    /*根据标签查询课程*/
    @Query("select t.courses from Tags t where t.tagId = :id")
    Page<Courses> findByTagId(@Param("id") String id, Pageable page);

}
