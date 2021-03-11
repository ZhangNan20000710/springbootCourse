package com.example.demo.controller;

import com.example.demo.dao.ICarouselDao;
import com.example.demo.domian.Entity.Carousel;
import com.example.demo.domian.Information.Message;
import com.example.demo.service.FileUploadLocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Carousel")
@Api(tags = "轮播图接口")
public class CarouselCRUD {

    @Autowired
    ICarouselDao carouselDao;

    @Autowired
    FileUploadLocalService fileUpload;

    @ApiOperation("显示轮播图")
    @GetMapping(CRUD.FIND)
    public Message carouselShow(){
        /*findAll()查询所有轮播图*/
        List<Carousel> Carousels = carouselDao.findAll();
        return Message.success().add("Carousels",Carousels);
    }

    @ApiOperation("添加轮播图")
    @PostMapping(CRUD.SAVE)
    public Message carouselAdd(Carousel carousel,
                               @RequestParam("file") MultipartFile file) throws IOException {
        String filepath = fileUpload.upload
                (file, "Carousel","carousel");
        carousel.setPicture(filepath);
        Carousel save = carouselDao.save(carousel);
        return Message.success("添加成功")
                .add("carousel",save);
    }


    @ApiOperation("删除轮播图")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    public Message carouselDelete(@PathVariable String id){
        carouselDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("删除全部轮播图")
    @DeleteMapping(CRUD.DELETE_ALL)
    public Message carouselDeleteAll(){
        carouselDao.deleteAll();
        return Message.success("删除成功");
    }


}
