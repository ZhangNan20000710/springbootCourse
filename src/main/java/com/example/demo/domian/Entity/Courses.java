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
@ApiModel(value = "课程")
public class Courses {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "是否是我发布的")
    private Boolean isMe;

    @ApiModelProperty(value = "课程图片")
    private String pictures;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @ApiModelProperty(value = "课程描述")
    private String describes;

    @ApiModelProperty(value = "课程完结状态")
    private Boolean sOver=false;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "课程创建时间")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "课程更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "参加人数")
    private int number =0;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @ApiModelProperty(value = "创建课程的用户id")
    private Users user;


    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = {CascadeType.ALL})
    private Set<Comments> comments=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = {CascadeType.ALL})
    private Set<CoursesUsers> Users=new HashSet<>();

    @JoinColumn(name = "type_id")
    @ManyToOne
    @ApiModelProperty(value = "课程种类")
    private Types type;

//    @JsonIgnore
//    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
//    private Set<coursesTags> tags=new HashSet<>();
//
//    @JsonIgnore
    @ManyToMany()
    private Set<Tags> tags=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = {CascadeType.ALL})
    private Set<Chapters> chapters=new HashSet<>();

    @Override
    public String toString() {
        return "Courses{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", isMe='" + isMe + '\'' +
                ", Number='" + number + '\'' +
                ", pictures='" + pictures + '\'' +
                ", describes='" + describes + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                ", tags=" + tags +
                ", type=" + type +
                '}';
    }
}
