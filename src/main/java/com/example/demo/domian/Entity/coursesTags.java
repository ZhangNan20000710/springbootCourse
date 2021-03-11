//package com.example.demo.domian.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//@ApiModel(value = "课程标签选择")
//public class coursesTags {
//    @Id
//    @JsonIgnore
//    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
//    @ApiModelProperty(value = "课程id")
//    private String id;
//
//    @JoinColumn(name = "course_id")
//    @ManyToOne
//    @ApiModelProperty(value = "创建课程的用户id")
//    private Courses course;
//
//    @JoinColumn(name = "tag_id")
//    @ManyToOne
//    @ApiModelProperty(value = "创建课程的用户id")
//    private Tags tag;
//
//}
