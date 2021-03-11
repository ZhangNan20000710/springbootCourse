package com.example.demo.domian.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "课程章")
public class Chapters {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "章id")
    private String chapterId;

    @ApiModelProperty(value = "章id(2)")
    private String id;

    @ApiModelProperty(value = "章名称")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "章创建时间")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "章更新时间")
    private Date updateTime;

    @JoinColumn(name = "course_id")
    @ManyToOne
    @ApiModelProperty(value = "课程id")
    private Courses course;

//    @JsonIgnore
    @OneToMany(mappedBy = "chapter",cascade = CascadeType.ALL)
    private Set<Sections> sections= new HashSet<>();

    @Override
    public String toString() {
        return "Chapters{" +
                "chapterId='" + chapterId + '\'' +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", course=" + course +
                '}';
    }
}
