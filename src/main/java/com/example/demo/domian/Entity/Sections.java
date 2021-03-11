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

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "课程节")
public class Sections {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "节id")
    private String sectionId;

    @ApiModelProperty(value = "节id(2)")
    private String id;

    @ApiModelProperty(value = "节姓名")
    private String name;

    @ApiModelProperty(value = "节内容")
    private String content;

    @ApiModelProperty(value = "word文件")
    private String pdfFile;

    @ApiModelProperty(value = "视频文件")
    private String videoFile;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "节创建时间")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "节更新时间")
    private Date updateTime;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "chapter_id")
    private Chapters chapter;

    @Override
    public String toString() {
        return "Sections{" +
                "sectionId='" + sectionId + '\'' +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", pdfFile='" + pdfFile + '\'' +
                ", videoFile='" + videoFile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", chapter=" + chapter +
                '}';
    }
}
