//package xyz.refrain.onlineedu.model.entity;
//
//import cn.hutool.core.date.DateTime;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
///**
// * <p>
// * 学生观看视频的数据表
// * </p>
// *
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@TableName("edu_video_number")
//@Accessors(chain = true)
//@ApiModel(value="EduVideoNumberEntity对象", description="课程视频表（用于存放二次修改的数据）")
//public class EduVideoNumberEntity implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @TableId(value = "id", type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键")
//    private Long id;
//
//    @TableField("user_id")
//    private String userId;
//
//    @TableField("video_id")
//    private String videoId;
//
//    @TableField("play_number")
//    private String playNumber;
//
//    @TableField("pause_number")
//    private String pauseNumber;
//
//    @TableField("ended_number")
//    private String endedNumber;
//
//    @TableField("seeked_number")
//    private String seekedNumber;
//
//    @TableField("completion_rate_number")
//    private String completionRateNumber;
//
//    @TableField("curr_time")
//    private String currTime;
//
//    @TableField("duration_number")
//    private String durationNumber;
//
//    @TableField("kj_number")
//    private String kjNumber;
//
//    @TableField("kt_number")
//    private String ktNumber;
//
//    @TableField("create_time")
//    private Date createTime;
//
//    @TableField(select = false)
//    private String  nickname;
//    @TableField(select = false)
//    private String  title;
////    @TableField(select = false)
////    private List<String> videoIds;
//
//
//
//}
package xyz.refrain.onlineedu.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 学生观看视频的数据表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_video_number")
@Accessors(chain = true)
@ApiModel(value="EduVideoNumberEntity对象", description="课程视频表（用于存放二次修改的数据）")
public class EduVideoNumberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField("user_id")
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @TableField("video_id")
    @ApiModelProperty(value = "视频ID")
    private Integer videoId;

    @TableField("course_id")
    @ApiModelProperty(value = "老师ID")
    private Integer courseId;

    @TableField("teacher_id")
    @ApiModelProperty(value = "老师ID")
    private Integer teacherId;

    @TableField("play_number")
    @ApiModelProperty(value = "播放次数")
    private Integer playNumber;

    @TableField("pause_number")
    @ApiModelProperty(value = "暂停次数")
    private Integer pauseNumber;

    @TableField("ended_number")
    @ApiModelProperty(value = "结束播放次数")
    private Integer endedNumber;

    @TableField("seeked_number")
    @ApiModelProperty(value = "拖拽次数")
    private Integer seekedNumber;

    @TableField("completion_rate_number")
    @ApiModelProperty(value = "完成率")
    private Double completionRateNumber;

    @TableField("curr_time")
    @ApiModelProperty(value = "当前播放时间")
    private Integer currTime;

    @TableField("duration_number")
    @ApiModelProperty(value = "视频时长")
    private Integer durationNumber;

    @TableField("kj_number")
    @ApiModelProperty(value = "快进次数")
    private Integer kjNumber;

    @TableField("kt_number")
    @ApiModelProperty(value = "快退次数")
    private Integer ktNumber;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 不映射数据库表的字段，用于封装额外信息
    @TableField(exist = false)
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @TableField(exist = false)
    @ApiModelProperty(value = "视频标题")
    private String title;
}
