package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.IChaptersDao;
import com.example.demo.dao.ISectionsDao;
import com.example.demo.domian.Entity.Chapters;
import com.example.demo.domian.Information.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/Chapter")
@Api(tags = "章接口")
public class ChaptersCRUD {

    @Autowired
    IChaptersDao chaptersDao;

    @Autowired
    ISectionsDao sectionsDao;

    Sort sort=Sort.by(Sort.Direction.ASC,"createTime");

    @ApiOperation("查询所有章")
    @GetMapping(CRUD.FIND)
    public Message chapterShow(){
        Set<Chapters> chapters =new HashSet<>(chaptersDao.findAll(sort));

        long count = chaptersDao.count();
        return Message.success().add("characters",chapters).add("count",count);
    }

    @ApiOperation("按课程id查询章")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message chapterShowById(@PathVariable String id){
        Set<Chapters> chapters = chaptersDao.findByCourseCourseId(id,sort);
        for (Chapters chapter : chapters) {
            chapter.setSections(sectionsDao.findByChapterChapterId(chapter.getChapterId(),sort));
        }
        return Message.success().add("characters",chapters);
    }

    @ApiOperation("添加章")
    @PostMapping(CRUD.SAVE)
    @UserLoginToken
    public Message chapterAdd(Chapters chapter){
        chapter.setUpdateTime(new Date());
        chapter.setCreateTime(new Date());
        Chapters save = chaptersDao.save(chapter);
        //复制主键的值
        save.setId(save.getChapterId());
        save=chaptersDao.save(save);
        return Message.success("添加成功").add("chapter",save);
    }

    @ApiOperation("修改章")
    @PutMapping(CRUD.ALTER)
    @UserLoginToken
    public Message chapterAlter(Chapters chapter){
        /*通过id获取之前修改之前的数据*/
        Chapters before = chaptersDao.getOne(chapter.getChapterId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(chapter,before, CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());
        Chapters save = chaptersDao.save(before);

        return Message.success("修改成功").add("course",save);
    }

    @ApiOperation("按照id删除章")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    @UserLoginToken
    public Message chapterAlter(@PathVariable String id){
        chaptersDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("删除全部章")
    @DeleteMapping(CRUD.DELETE_ALL)
    @UserLoginToken
    public Message chapterDelete(){
        chaptersDao.deleteAll();
        return Message.success("删除成功");
    }

}
