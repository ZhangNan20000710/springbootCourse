package com.example.demo.dao;

import com.example.demo.domian.Entity.Chapters;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Set;

public interface IChaptersDao extends JpaRepository<Chapters,String>, JpaSpecificationExecutor<Chapters> {
    Set<Chapters> findByCourseCourseId(String courseId, Sort sort);

}
