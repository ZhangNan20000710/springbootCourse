//package com.example.demo.controller.toolController;
//
//import com.example.demo.dao.IChaptersDao;
//import com.example.demo.dao.ICoursesDao;
//import com.example.demo.dao.ISectionsDao;
//import com.example.demo.domian.Entity.Chapters;
//import com.example.demo.domian.Entity.Courses;
//import com.example.demo.domian.Entity.Sections;
//import com.example.demo.domian.Information.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//public class addChapter {
//
//    @Autowired
//    ICoursesDao coursesDao;
//
//    @Autowired
//    IChaptersDao chaptersDao;
//
//    @Autowired
//    ISectionsDao sectionsDao;
//
//    @RequestMapping("/addChapter")
//    public Message add(){
//        List<Courses> all = coursesDao.findAll();
//
//        for (Courses courses : all) {
//            for (int i=1;i<5;i++){
//                Chapters chapter = new Chapters();
//                chapter.setName("第"+i+"章");
//                chapter.setCreateTime(new Date());
//                chapter.setUpdateTime(new Date());
//                chapter.setCourse(courses);
//                Chapters save1 = chaptersDao.save(chapter);
//                save1.setId(save1.getChapterId());
//                save1 = chaptersDao.save(chapter);
//                for(int j=1;j<5;j++){
//                    Sections section = new Sections();
//                    section.setChapter(save1);
//                    section.setName("第"+j+"节");
//                    section.setContent("第"+j+"节内容："+ UUID.randomUUID());
//                    section.setUpdateTime(new Date());
//                    section.setCreateTime(new Date());
//                    Sections save = sectionsDao.save(section);
//                    save.setId(save.getSectionId());
//                    save = sectionsDao.save(section);
//                }
//            }
//        }
//
//        return Message.success();
//    }
//}
