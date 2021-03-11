package com.example.demo;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.dao.ICoursesDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Entity.Users;
import com.example.demo.service.FileUploadLocalService;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Service
class DemoApplicationTests {

    @Autowired
    ICoursesDao coursesDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    FileUploadLocalService fileUpload;

    @Test
    void contextLoads() throws FileNotFoundException {

        File path =new File(ResourceUtils.getURL("").getPath(),"/src/main/resources");
        File upload = new File(path.getAbsolutePath(), "static/images/portrait/");
        System.out.println(upload.getAbsolutePath());
    }
    @Test
    void contextLoads1() throws IOException {

        JiebaSegmenter segmenter = new JiebaSegmenter();
        String sentences = "北京京天威科技发展有限公司大庆车务段的装车数量";
//        WordDictionary.getInstance().init(Paths.get("conf"));
        System.out.println(segmenter.process(sentences, JiebaSegmenter.SegMode.SEARCH));

    }

    public void getFiles(String path) throws IOException {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory()){
            files.add(root);
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                System.out.println(f.getName());
                String course = fileUpload.uploadLocal(f, "course", f.getName());
                Courses courses = new Courses();
                List<Users> all = userDao.findAll();
                courses.setUser(all.get(RandomUtil.randomInt(0, all.size()-1)));
                courses.setCourseName("课程"+RandomUtil.randomInt(0,1000000));
                courses.setPictures(course);
                courses.setUpdateTime(new Date());
                courses.setCreateTime(new Date());
                coursesDao.save(courses);
            }
        }
    }

}
