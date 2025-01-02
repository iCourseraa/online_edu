package xyz.refrain.onlineedu.controller.teacher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.refrain.onlineedu.config.LocalDateTimeSerializerConfig;
import xyz.refrain.onlineedu.model.securtiy.EduTeacherDetail;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.service.StatService;
import xyz.refrain.onlineedu.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 讲师端数据统计控制器
 *
 */
@Validated
@RestController("TeacherStatController")
@RequestMapping("/api/teacher/stat")
@Api(value = "讲师端数据统计控制器", tags = {"讲师端数据统计接口"})
public class StatController {

	@Autowired
	private StatService statService;

	@GetMapping("/get/common")
	@ApiOperation("获取讲师数据统计")
	public R getCommon(HttpServletRequest request) {
		EduTeacherDetail teacher = SessionUtils.getTeacher(request);
		return statService.getTchStat(teacher.getId());
	}

	@PostMapping("/get/daily")
	@ApiOperation("获取老师端每日数据统计")
	public R getTchDaily(@RequestParam("start") @DateTimeFormat(pattern =
			LocalDateTimeSerializerConfig.DEFAULT_DATE_TIME_PATTERN) LocalDateTime start,
						 @RequestParam("end") @DateTimeFormat(pattern =
								 LocalDateTimeSerializerConfig.DEFAULT_DATE_TIME_PATTERN) LocalDateTime end,
						 HttpServletRequest request) {
		return statService.getTchDailyStat(start, end, request);
	}


}
