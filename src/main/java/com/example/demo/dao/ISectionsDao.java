package com.example.demo.dao;

import com.example.demo.domian.Entity.Sections;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface ISectionsDao extends JpaRepository<Sections,String>, JpaSpecificationExecutor<Sections> {
    Set<Sections> findByChapterChapterId(String chapterId, Sort sort);
    Set<Sections> findByChapterCourseCourseId(String courseId, Sort sort);
}
