package com.example.demo.domian.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "选课记录")
public class CoursesUsers {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "选课记录id")
    private String CoursesUserId;

    @ApiModelProperty(value = "是否时我发布的")
    private Boolean isMe;

    @ApiModelProperty(value = "成绩")
    private Double score;

    @ApiModelProperty(value = "课程进度")
    private Double learningRate;

    @JoinColumn(name = "course_id")
    @ManyToOne
    @ApiModelProperty(value = "课程id")
    private Courses course;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @ApiModelProperty(value = "用户id")
    private Users user;

    @Override
    public String toString() {
        return "CoursesUsers{" +
                "CoursesUserId='" + CoursesUserId + '\'' +
                "isMe='" + isMe + '\'' +
                ", score=" + score +
                ", learningRate=" + learningRate +
                ", course=" + course +
                ", user=" + user +
                '}';
    }
}
