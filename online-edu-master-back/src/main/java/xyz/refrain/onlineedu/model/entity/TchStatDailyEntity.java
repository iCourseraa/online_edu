package xyz.refrain.onlineedu.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tch_stat_daily")
@ApiModel(value = "TchStatDailyEntity对象", description = "老师端统计日数据")
public class TchStatDailyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "统计日期")
    @TableField("date")
    private LocalDateTime date;

    @ApiModelProperty(value = "课程id")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty(value = "每日播放视频数")
    @TableField("video_view_count")
    private Integer videoViewCount;

    @ApiModelProperty(value = "每日新增课程数")
    @TableField("course_buy_count")
    private Integer courseBuyCount;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
