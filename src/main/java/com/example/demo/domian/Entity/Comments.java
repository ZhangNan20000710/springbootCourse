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
@ApiModel(value = "课程评论")
public class Comments {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "评论创建时间")
    private Date createTime;

    @ApiModelProperty(value = "多少时间之前")
    private String beforeTime;

    @JoinColumn(name = "course_id")
    @ManyToOne
    @ApiModelProperty(value = "课程id")
    private Courses course;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @ApiModelProperty(value = "用户id")
    private Users user;

    @JsonIgnore
    @OneToMany(mappedBy = "parentComment",cascade={CascadeType.ALL})
    private Set<Comments> childComments=new HashSet<>();

    
    @JoinColumn(name = "parentComment_id")
    @ManyToOne
    @ApiModelProperty(value = "父级评论id(如果给值就表示此条评论是回复)")
    private Comments parentComment;

    @Override
    public String toString() {
        return "Comments{" +
                "commentId='" + commentId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", createTime=" + createTime +
                ", beforeTime=" + beforeTime +
                ", course=" + course +
                ", user=" + user +
                '}';
    }
}