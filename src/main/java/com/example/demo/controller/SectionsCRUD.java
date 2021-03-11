package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.ISectionsDao;
import com.example.demo.domian.Entity.Chapters;
import com.example.demo.domian.Entity.Sections;
import com.example.demo.domian.Information.Message;
import com.example.demo.service.FileUploadLocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/Section")
@Api(tags = "节接口")
public class SectionsCRUD {

    @Autowired
    ISectionsDao sectionsDao;

    @Autowired
    FileUploadLocalService fileUpload;

    Sort sort=Sort.by(Sort.Direction.ASC,"createTime");

    @ApiOperation("查询所有节")
    @GetMapping(CRUD.FIND)
    @UserLoginToken
    public Message sectionShow(){
        HashSet<Sections> sections = new HashSet<>(sectionsDao.findAll());
        long count = sectionsDao.count();
        return Message.success().add("sections",sections).add("count",count);
    }

    @ApiOperation("按章id或课程id查询节")
    @GetMapping(CRUD.FIND_BY_ID)
    @UserLoginToken
    public Message sectionShowById(@PathVariable String id){
        Set<Sections> Sections = sectionsDao.findByChapterChapterId(id,sort);
        Set<Sections> SectionsCourse = sectionsDao.findByChapterCourseCourseId(id,sort);
        Sections section = sectionsDao.getOne(id);
        if(section!=null){
            return Message.success().add("characters",section);
        }
        else if(!Sections.isEmpty()){
            return Message.success().add("characters",SectionsCourse);
        }else {
            return Message.success().add("characters",Sections);
        }
    }

    @ApiOperation("添加节")
    @PostMapping(CRUD.SAVE)
    @UserLoginToken
    public Message SectionAdd(Sections section){
        section.setUpdateTime(new Date());
        section.setCreateTime(new Date());
        System.out.println(section);
        Sections save = sectionsDao.save(section);
        //复制主键的值
        save.setId(save.getSectionId());
        save=sectionsDao.save(save);
        return Message.success("添加成功").add("section",save);
    }

    @ApiOperation("添加PDF文件")
    @PostMapping("/addPdf")
    @UserLoginToken
    public Message SectionAddWord(String sectionId,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(sectionId);
        Sections section = sectionsDao.getOne(sectionId);

        if(!file.isEmpty()){
            String filePath = fileUpload.upload(file, "Pdf", section.getName());
            section.setPdfFile(filePath);
        }

        section.setUpdateTime(new Date());
        Sections save = sectionsDao.save(section);
        System.out.println(save);
        return Message.success("添加成功").add("Section",save);
    }

    @ApiOperation("添加视频文件")
    @PostMapping("/addVideo")
    @UserLoginToken
    public Message SectionAddVideo(String sectionId,@RequestParam("file") MultipartFile file) throws IOException {
        Sections section = sectionsDao.getOne(sectionId);
        if(!file.isEmpty()){
            String filePath = fileUpload.upload(file, "video", section.getName());
            section.setVideoFile(filePath);
        }

        section.setUpdateTime(new Date());
        Sections save = sectionsDao.save(section);
        return Message.success("添加成功").add("Section",save);
    }

    @ApiOperation("修改节")
    @PutMapping(CRUD.ALTER)
    @UserLoginToken
    public Message SectionAlter(Sections Section){

        /*通过id获取之前修改之前的数据*/
        Sections before = sectionsDao.getOne(Section.getSectionId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(Section,before, CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());

        Sections save = sectionsDao.save(before);
        return Message.success("修改成功").add("course",save);
    }

    @ApiOperation("按节id删除节")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    @UserLoginToken
    public Message SectionAlter(@PathVariable String id){
        sectionsDao.deleteById(id);
        return Message.success("修改成功").add("id",id);
    }

    @ApiOperation("删除所有节")
    @DeleteMapping(CRUD.DELETE_ALL)
    @UserLoginToken
    public Message SectionAlter(){
        sectionsDao.deleteAll();
        return Message.success("删除成功");
    }
}