package xyz.refrain.onlineedu.controller.teacher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.refrain.onlineedu.controller.app.UserLookNumberController;
import xyz.refrain.onlineedu.model.base.ValidGroupType;
import xyz.refrain.onlineedu.model.entity.EduVideoEntity;
import xyz.refrain.onlineedu.model.entity.EduVideoEntity_new;
import xyz.refrain.onlineedu.model.entity.EduVideoNumberEntity;
import xyz.refrain.onlineedu.model.securtiy.EduTeacherDetail;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.model.vo.teacher.EduVideoVO;
import xyz.refrain.onlineedu.service.EduVideoService;
import xyz.refrain.onlineedu.service.UserLookNumberService;
import xyz.refrain.onlineedu.utils.RUtils;
import xyz.refrain.onlineedu.utils.SessionUtils;


import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 讲师端视频控制器
 *
 */
@Validated
@RestController("TeacherEduVideoController")
@RequestMapping("/api/teacher/video")
@Api(value = "讲师端视频控制器", tags = {"讲师端视频接口"})
public class EduVideoController {

	@Autowired
	private EduVideoService eduVideoService;


	@Autowired
	private UserLookNumberService userLookNumberService;

	@GetMapping("/list/{chapterId}")
	@ApiOperation("获取章节视频")
	public R list(@PathVariable("chapterId") @Min(1) Integer chapterId) {
		List<EduVideoVO> list = eduVideoService.listVideos(chapterId);
		return RUtils.success("章节视频列表信息", list);
	}
	@GetMapping("/list/")
	@ApiOperation("所有的视频章节")
	public R listAll(HttpServletRequest request) {
		List<EduVideoEntity_new> list = eduVideoService.getListAll(request);
		return RUtils.success("章节视频列表信息", list);
	}
	@PostMapping("/select/number")
	@ApiOperation("视频观看情况")
	public R listNumber(@RequestBody EduVideoNumberEntity entity) {
		List<EduVideoNumberEntity> list = userLookNumberService.selectAll(entity);
		// 打印日志

//		List<EduVideoEntity> list = eduVideoService.getListAll();
		return RUtils.success("章节视频列表信息", list);
	}
//	@PostMapping("/select/number")
//	@ApiOperation("视频观看情况")
//	public R listNumber(@RequestBody EduVideoNumberEntity entity, HttpServletRequest request) {
//		// 使用 SessionUtils 获取当前登录教师信息
//		EduTeacherDetail teacher = SessionUtils.getTeacher(request);
////		if (teacher == null) {
////
////		}
//
//		// 调用服务层方法
//		List<EduVideoNumberEntity> list = userLookNumberService.selectByTeacher(teacher.getId(), entity);
//		return RUtils.success("章节视频列表信息", list);
//}


	@PostMapping("/create")
	@ApiOperation("上传视频")
	public R create(@Min(1) @NotNull Integer courseId,
	                @Min(1) @NotNull Integer chapterId,
	                @RequestPart("file") MultipartFile file) {
		return eduVideoService.create(courseId, chapterId, file);
	}

	@PostMapping("/update")
	@ApiOperation("更新视频信息")
	public R update(@RequestBody @Validated(ValidGroupType.Update.class) EduVideoVO vo) {
		return eduVideoService.update(vo);
	}

	@PostMapping("/delete/{id}")
	@ApiOperation("删除视频")
	public R delete(@PathVariable("id") @Min(1) Integer id) {
		return eduVideoService.delete(id);
	}

}
