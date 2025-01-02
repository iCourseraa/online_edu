package xyz.refrain.onlineedu.model.vo.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.refrain.onlineedu.config.LocalDateTimeSerializerConfig;
import xyz.refrain.onlineedu.model.base.BeanConvert;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 每日数据统计信息
 *
 */
@Accessors(chain = true)
@Data
public class TchStatDailyVO implements Serializable, BeanConvert {

	private static final long serialVersionUID = 2634477720113002360L;

	@JsonFormat(pattern = LocalDateTimeSerializerConfig.DEFAULT_DATE_PATTERN)
	@ApiModelProperty(value = "统计日期")
	private LocalDateTime date;

	@NotNull(message = "课程Id不能为空")
	@ApiModelProperty(value = "课程id")
	private Integer courseId;

	@ApiModelProperty(value = "每日播放视频数")
	private Integer videoViewCount;

	@ApiModelProperty(value = "每日新增课程数")
	private Integer courseBuyCount;

}
