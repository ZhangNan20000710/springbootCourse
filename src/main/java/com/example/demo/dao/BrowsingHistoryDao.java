//package com.example.demo.dao;
//
//import com.example.demo.domian.Entity.BrowsingHistory;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//
//import java.util.Set;
//
//public interface BrowsingHistoryDao extends JpaRepository<BrowsingHistory,String> , JpaSpecificationExecutor<BrowsingHistory> {
//    BrowsingHistory findByUserUserIdAndCourseCourseId(String userId,String courseId);
//    void deleteByUserUserIdAndCourseCourseId(String userId,String courseId);
//    Set<BrowsingHistory> findByUserUserId(String userId);
//}
