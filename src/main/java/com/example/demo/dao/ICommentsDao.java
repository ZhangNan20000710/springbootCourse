package com.example.demo.dao;

import com.example.demo.domian.Entity.Comments;
import com.example.demo.domian.Entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface ICommentsDao extends JpaRepository<Comments,String>, JpaSpecificationExecutor<Comments> {
//    Set<Comments> findAllByParentCommentIsNull();
    Page<Comments> findByCourseCourseIdAndParentCommentIsNull(String courseId, Pageable page);
    Page<Comments> findByCommentContentLikeOrCourseCourseNameLikeOrUserNicknameLike(String commentContent,
                                                                                    String courseName,
                                                                                    String nickName,
                                                                                    Pageable page);
    Set<Comments> findByCourseCourseId(String courseId);
    Page<Comments> findByUserUserId(String userId,Pageable page);
}
