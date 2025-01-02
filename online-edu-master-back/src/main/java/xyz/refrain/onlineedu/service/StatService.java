package xyz.refrain.onlineedu.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.refrain.onlineedu.annotation.TimeCost;
import xyz.refrain.onlineedu.constant.StatConstant;
import xyz.refrain.onlineedu.mapper.*;
import xyz.refrain.onlineedu.model.entity.*;
import xyz.refrain.onlineedu.model.enums.CourseStatusEnum;
import xyz.refrain.onlineedu.model.enums.PayTypeEnum;
import xyz.refrain.onlineedu.model.enums.SexEnum;
import xyz.refrain.onlineedu.model.securtiy.EduTeacherDetail;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.model.vo.admin.StatCommonVO;
import xyz.refrain.onlineedu.model.vo.admin.StatDailyVO;
import xyz.refrain.onlineedu.model.vo.teacher.StatTchVO;
import xyz.refrain.onlineedu.utils.RUtils;
import xyz.refrain.onlineedu.utils.RedisUtils;
import xyz.refrain.onlineedu.utils.SessionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据统计业务类
 *
 */
@Service
@Slf4j
public class StatService {

	@Resource
	private AclUserMapper aclUserMapper;
	@Resource
	private EduTeacherMapper eduTeacherMapper;
	@Resource
	private UctrMemberMapper uctrMemberMapper;
	@Resource
	private EduCourseMapper courseMapper;
	@Resource
	private EduVideoMapper eduVideoMapper;
	@Resource
	private EduCommentMapper eduCommentMapper;
	@Resource
	private TOrderMapper tOrderMapper;
	@Resource
	private StatDailyMapper statDailyMapper;

	@Resource
	private TchStatDailyMapper TchStatDailyMapper;

	@Autowired
	private EduCourseService eduCourseService;

	/**
	 * 平台每日信息统计数据
	 */
	// @TimeCost
	public R getDailyStat(LocalDateTime start, LocalDateTime end) {
		// 获取数据
		List<StatDailyEntity> entityList = statDailyMapper.selectList(
				Wrappers.lambdaQuery(StatDailyEntity.class)
						.ge(StatDailyEntity::getDate, start)
						.le(StatDailyEntity::getDate, end)
		);
		// 转换数据
		List<StatDailyVO> voList = entityList.stream().parallel()
				.map(e -> (StatDailyVO) new StatDailyVO().convertFrom(e))
				.collect(Collectors.toList());
		return RUtils.success("平台每日统计数据", voList);
	}

	@TimeCost
	public R getTchDailyStat(LocalDateTime start, LocalDateTime end, HttpServletRequest request) {
		// 获取当前登录的教师ID
		EduTeacherDetail teacher = SessionUtils.getTeacher(request);
//		if (teacher == null) {
//			return RUtils.error("教师信息未找到");
//		}

		// 获取当前登录教师的所有课程
		List<Map<String, Object>> courseList = eduCourseService.listTeacherCourseIdAndTitle(teacher.getId());

		// 存放课程统计数据
		List<Map<String, Object>> resultList = new ArrayList<>();

		// 遍历所有课程，查询每个课程在指定时间范围内的订阅量和观看量
		for (Map<String, Object> course : courseList) {
			Integer courseId = (Integer) course.get("id");
			String courseTitle = (String) course.get("title");

			// 查询课程的每日观看量（按天分组）
			List<Map<String, Object>> dailyVideoViewCounts = TchStatDailyMapper.selectDailyVideoViewCount(courseId, start, end);

			// 查询课程的每日订阅量（按天分组）
			List<Map<String, Object>> dailyCourseBuyCounts = TchStatDailyMapper.selectDailyCourseBuyCount(courseId, start, end);
			// 格式化数据：按天填充数据，缺失的日期用0填充
			Map<LocalDateTime, Integer> videoViewCountMap = new HashMap<>();
			for (Map<String, Object> dailyData : dailyVideoViewCounts) {
				LocalDateTime date = (LocalDateTime) dailyData.get("date");
				BigDecimal countDecimal = (BigDecimal) dailyData.get("videoViewCount");
				Integer count = countDecimal.intValue();
				videoViewCountMap.put(date, count);
			}

			Map<LocalDateTime, Integer> courseBuyCountMap = new HashMap<>();
			for (Map<String, Object> dailyData : dailyCourseBuyCounts) {
				LocalDateTime date = (LocalDateTime) dailyData.get("date");
				BigDecimal countDecimal = (BigDecimal) dailyData.get("courseBuyCount");
				Integer count = countDecimal.intValue();
				courseBuyCountMap.put(date, count);
			}

			// 准备时间范围内的所有日期
			List<Map<String, Object>> dailyStats = new ArrayList<>();
			LocalDateTime currentDate = start;
			while (!currentDate.isAfter(end)) {
				Map<String, Object> stats = new HashMap<>();
				stats.put("date", currentDate);
				stats.put("videoViewCount", videoViewCountMap.getOrDefault(currentDate, 0));
				stats.put("courseBuyCount", courseBuyCountMap.getOrDefault(currentDate, 0));
				dailyStats.add(stats);
				currentDate = currentDate.plusDays(1); // 增加一天
			}

			// 汇总每个课程的统计数据
			Map<String, Object> courseStats = new HashMap<>();
			courseStats.put("courseId", courseId);
			courseStats.put("courseTitle", courseTitle);
			courseStats.put("dailyStats", dailyStats);

			// 添加到结果列表
			resultList.add(courseStats);
			log.info("返回数据：");
			log.info(resultList.toString());
		}

		// 返回教师所有课程的统计数据
		return RUtils.success("教师所有课程的每日观看量", resultList);
	}
	/**
	 * 平台信息统计数据
	 */
	// @TimeCost
	public R getCommonStat() {
		StatCommonVO statVO = new StatCommonVO();
		statVO
				// 用户
				.setAdminCount(countAdmin(null))
				.setDisabledAdminCount(countAdmin(false))
				// 讲师
				.setTeacherCount(countTeacher(null))
				.setDisabledTeacherCount(countTeacher(false))
				// 学员
				.setStudentCount(countStudent(null, null))
				.setDisabledStudentCount(countStudent(false, null))
				.setFemaleStudentCount(countStudent(null, SexEnum.FEMALE))
				.setMaleStudentCount(countStudent(null, SexEnum.MALE))
				// 课程
				.setCourseCount(countCourse(null))
				// 二次审核的也是上架
				.setEnabledCourseCount(countCourse(true, CourseStatusEnum.PUBLISH, CourseStatusEnum.SECOND_AUDITING))
				.setDisabledCourseCount(countCourse(false, new CourseStatusEnum[0]))
				.setEditingCourseCount(countCourse(true, CourseStatusEnum.DRAFT))
				.setAuditingCourseCount(countCourse(true,
						CourseStatusEnum.FIRST_AUDITING, CourseStatusEnum.SECOND_AUDITING))
				.setRejectedCourseCount(countCourse(true, CourseStatusEnum.TURN_DOWN))
				// 视频
				.setVideoCount(countVideo())
				// 订单
				.setOrderCount(countOrder(null))
				.setOrderPayByWechatCount(countOrder(PayTypeEnum.WECHAT_PAY))
				.setOrderPayByAlipayCount(countOrder(PayTypeEnum.ALI_PAY))
				.setOrderPayByNoneCount(countOrder(PayTypeEnum.NONE))
		;

		return RUtils.success("平台统计数据", statVO);
	}

	/**
	 * 统计讲师的基本数据
	 */
	public R getTchStat(int teacherId) {
		StatTchVO vo = new StatTchVO();
		// 发表的课程数量
		Set<Integer> courseIdSet = courseMapper.selectList(
				Wrappers.lambdaQuery(EduCourseEntity.class)
						.select(EduCourseEntity::getId)
						.eq(EduCourseEntity::getTeacherId, teacherId)
						.eq(EduCourseEntity::getStatus, CourseStatusEnum.PUBLISH)
		).stream().parallel().map(EduCourseEntity::getId).collect(Collectors.toSet());
		vo.setCourseCount(courseIdSet.size());
		// 发表的课程视频数量
		Integer videoCount = 0;
		if (!CollectionUtils.isEmpty(courseIdSet)) {
			videoCount = eduVideoMapper.selectCount(
					Wrappers.lambdaQuery(EduVideoEntity.class)
							.in(EduVideoEntity::getCourseId, courseIdSet));
		}
		vo.setVideoCount(videoCount);
		// 评论数量
		Integer commentCount = eduCommentMapper.selectCount(
				Wrappers.lambdaQuery(EduCommentEntity.class)
						.eq(EduCommentEntity::getTeacherId, teacherId)
		);
		Integer auditingCommentCount = eduCommentMapper.selectCount(
				Wrappers.lambdaQuery(EduCommentEntity.class)
						.eq(EduCommentEntity::getTeacherId, teacherId)
						.eq(EduCommentEntity::getStatus, false)
		);
		vo.setCommentCount(commentCount)
				.setAuditingCommentCount(auditingCommentCount);
		// 加入平台的时间
		EduTeacherEntity teacherEntity = eduTeacherMapper.selectOne(
				Wrappers.lambdaQuery(EduTeacherEntity.class)
						.select(EduTeacherEntity::getCreateTime)
						.eq(EduTeacherEntity::getId, teacherId)
		);
		if (Objects.isNull(teacherEntity)) {
			vo.setJoinDateTime(LocalDateTime.now()).setJoinDaysCount(1);
		} else {
			int joinDaysCount = (int) LocalDateTimeUtil.between(teacherEntity.getCreateTime(),
					LocalDateTime.now(), ChronoUnit.DAYS);
			vo.setJoinDateTime(teacherEntity.getCreateTime()).setJoinDaysCount(joinDaysCount);
		}

		return RUtils.success("讲师统计数据", vo);
	}

	/**
	 * 根据禁用与否统计管理员数量
	 */
	public int countAdmin(Boolean enable) {
		return aclUserMapper.selectCount(
				Wrappers.lambdaQuery(AclUserEntity.class)
						.eq(Objects.nonNull(enable), AclUserEntity::getEnable, enable)
		);
	}

	/**
	 * 根据禁用与否统计讲师数量
	 */
	public int countTeacher(Boolean enable) {
		return eduTeacherMapper.selectCount(
				Wrappers.lambdaQuery(EduTeacherEntity.class)
						.eq(Objects.nonNull(enable), EduTeacherEntity::getEnable, enable)
		);
	}

	/**
	 * 根据禁用与否,性别统计学员数量
	 */
	public int countStudent(Boolean enable, SexEnum sex) {
		return uctrMemberMapper.selectCount(
				Wrappers.lambdaQuery(UctrMemberEntity.class)
						.eq(Objects.nonNull(enable), UctrMemberEntity::getEnable, enable)
						.eq(Objects.nonNull(sex), UctrMemberEntity::getSex, sex)
		);
	}


	/**
	 * 根据课程状态统计课程数量
	 */
	public int countCourse(Boolean enable, CourseStatusEnum... status) {
		Set<CourseStatusEnum> statusEnumSet = Arrays.stream(status).collect(Collectors.toSet());
		return courseMapper.selectCount(
				Wrappers.lambdaQuery(EduCourseEntity.class)
						.eq(Objects.nonNull(enable), EduCourseEntity::getEnable, enable)
						.in(!CollectionUtils.isEmpty(statusEnumSet), EduCourseEntity::getStatus, statusEnumSet)
		);
	}

	/**
	 * 根据课程是否免费统计课程数量
	 */
	public int countCourse(boolean free) {
		return courseMapper.selectCount(
				Wrappers.lambdaQuery(EduCourseEntity.class)
						.eq(free, EduCourseEntity::getPrice, 0)
						.gt(!free, EduCourseEntity::getPrice, 0)
		);
	}

	/**
	 * 统计视频数量
	 */
	public int countVideo() {
		return eduVideoMapper.selectCount(Wrappers.emptyWrapper());
	}

	/**
	 * 根据付款类型统计订单数量
	 */
	public int countOrder(PayTypeEnum payType) {
		return tOrderMapper.selectCount(
				Wrappers.lambdaQuery(TOrderEntity.class)
						.eq(Objects.nonNull(payType), TOrderEntity::getPayType, payType)
		);
	}

	/**
	 * 统计每日数据
	 */
	public void statDaily() {
		//log.info("开始统计数据...");
		// 1. 获取缓存数据
		StatDailyVO vo = new StatDailyVO();

		// 网站访问量
		String[] visitCountKeys = RedisUtils.keys(StatConstant.VISIT_COUNT + "*").toArray(new String[0]);
		int visitCount = visitCountKeys.length;
		vo.setVisitCount(visitCount);
		// 注册量
		Integer registerCount = (Integer) RedisUtils.get(StatConstant.REGISTER_COUNT);
		registerCount = Objects.isNull(registerCount) ? 0 : registerCount;
		vo.setRegisterCount(registerCount);
		// 登录量/活跃人数
		String[] loginCountKeys = RedisUtils.keys(StatConstant.LOGIN_COUNT + "*").toArray(new String[0]);
		int loginCount = loginCountKeys.length;
		vo.setLoginCount(loginCount);
		// 视频播放量
		String[] videoViewCountKeys = RedisUtils.keys(StatConstant.VIDEO_VIEW_COUNT + "*").toArray(new String[0]);
		int videoViewCount = videoViewCountKeys.length;
		vo.setVideoViewCount(videoViewCount);
		// 课程订阅量
		Integer courseBuyCount = (Integer) RedisUtils.get(StatConstant.COURSE_BUY_COUNT);
		courseBuyCount = Objects.isNull(courseBuyCount) ? 0 : courseBuyCount;
		vo.setCourseBuyCount(courseBuyCount);


		//log.info("视频观看量的 Redis 键: {}", Arrays.toString(videoViewCountKeys));

		String[] courseBuyCountKeys = RedisUtils.keys(StatConstant.COURSE_BUY_COUNT + "*").toArray(new String[0]);
		//log.info("课程订阅量的 Redis 键: {}", Arrays.toString(courseBuyCountKeys));


		//log.info("数据统计完成，准备保存至数据库...");

		// 2. 写入数据库
		// 获取昨天零点
		LocalDateTime yesterday = LocalDateTimeUtil.offset(LocalDateTime.now(), -1, ChronoUnit.DAYS);
		LocalDateTime midnightYesterday = LocalDateTimeUtil.beginOfDay(yesterday);
		vo.setDate(midnightYesterday);
		// 判断是否已经统计过了
		Integer count = statDailyMapper.selectCount(
				Wrappers.lambdaQuery(StatDailyEntity.class)
						.eq(StatDailyEntity::getDate, midnightYesterday)
		);
		if (count > 0) {
			return;
		}
		StatDailyEntity entity = vo.convertTo(new StatDailyEntity());
		int rowsAffected = statDailyMapper.insert(entity);

		// 判断是否插入成功
		if (rowsAffected > 0) {
			// 插入成功，打印日志
			//log.info("成功插入StatDaily数据: {}", entity);
		} else {
			// 插入失败，打印日志
			//log.error("插入StatDaily数据失败: {}", entity);
		}

		// 3. 删除缓存（在写入数据库之后）
		RedisUtils.del(visitCountKeys);
		RedisUtils.del(StatConstant.REGISTER_COUNT);
		RedisUtils.del(loginCountKeys);
//		RedisUtils.del(videoViewCountKeys);
		//RedisUtils.del(StatConstant.COURSE_BUY_COUNT);

		//log.info("统计数据已成功写入数据库...");
	}

	public void TchstatDaily() {
		//log.info("tch开始统计数据...");

		// 1. 获取缓存数据
		// 视频播放量
//------------------------------------------put到map里面的时候映射有问题，key有值但是map为空----------------------------------------------------------------------------
		String[] courseViewCountKeys = RedisUtils.keys(StatConstant.COURSE_VIEW_COUNT + "*").toArray(new String[0]);
		Map<String, Integer> courseViewCountMap = new HashMap<>();
		//log.info("courseViewCountKeys length: {}", courseViewCountKeys.length);

		for (String key : courseViewCountKeys) {
			String videoIdStr = key.replace(StatConstant.COURSE_VIEW_COUNT, "");
			String[] parts = videoIdStr.split(":");
			String courseIdStr = parts[1];
			Integer courseViewCount = (Integer) RedisUtils.get(key);
			//log.info("courseIdStr:{}",courseIdStr);
			//log.info("courseViewCount:{}",courseViewCount);
			if (courseIdStr != null && courseViewCount != null) {
				courseViewCountMap.put(courseIdStr, courseViewCountMap.getOrDefault(courseIdStr, 0) + courseViewCount);
			}
		}

		// 课程订阅量
		Map<String, Integer> courseBuyCountMap = new HashMap<>();
		String[] courseBuyCountKeys = RedisUtils.keys(StatConstant.COURSE_BUY_COUNT + "*").toArray(new String[0]);
		for (String key : courseBuyCountKeys) {
			String courseIdStr = key.replace(StatConstant.COURSE_BUY_COUNT, "");
			Integer courseBuyCount = (Integer) RedisUtils.get(key);
			if (courseIdStr != null && courseBuyCount != null) {
				courseBuyCountMap.put(courseIdStr, courseBuyCountMap.getOrDefault(courseIdStr, 0) + courseBuyCount);
			}
		}
//	log.info("视频观看量前缀: {}", courseViewCountKeys);
//	log.info("视频观看量前缀: {}", courseViewCountMap);
//	log.info("课程订阅量前缀: {}", StatConstant.COURSE_BUY_COUNT);
//
//	log.info("tch数据统计完成，准备保存至数据库...");
//----------------------------------------------------------------------------------------------------------------------
		// 2. 写入数据库
		// 获取昨天零点
		LocalDateTime yesterday = LocalDateTimeUtil.offset(LocalDateTime.now(), -1, ChronoUnit.DAYS);
		LocalDateTime midnightYesterday = LocalDateTimeUtil.beginOfDay(yesterday);

		// 判断是否已经统计过了
		Integer count = TchStatDailyMapper.selectCount(
				Wrappers.lambdaQuery(TchStatDailyEntity.class)
						.eq(TchStatDailyEntity::getDate, midnightYesterday)
		);
		if (count > 0) {
			return;
		}

		// 遍历每个 course_id，分别写入数据库
		for (Map.Entry<String, Integer> entry : courseViewCountMap.entrySet()) {
			String courseId = entry.getKey();
			Integer videoCount = entry.getValue();
			Integer courseBuyCount = courseBuyCountMap.getOrDefault(courseId, 0);

			// 创建一个 TchStatDailyEntity 实体对象
			TchStatDailyEntity entity = new TchStatDailyEntity();
			entity.setDate(midnightYesterday);
			entity.setCourseId(Integer.parseInt(courseId));  // 将课程ID设置到实体中
			entity.setVideoViewCount(videoCount);
			entity.setCourseBuyCount(courseBuyCount);
			entity.setCreateTime(midnightYesterday);
			entity.setUpdateTime(midnightYesterday);

			// 写入数据库
			int rowsAffected = TchStatDailyMapper.insert(entity);

			// 判断是否插入成功
			if (rowsAffected > 0) {
				// 插入成功，打印日志
				log.info("成功插入StatDaily数据: {}", entity);
			} else {
				// 插入失败，打印日志
				log.error("插入StatDaily数据失败: {}", entity);
			}

		}

		// 3. 删除缓存（在写入数据库之后）
		// 删除与视频播放量相关的缓存
		RedisUtils.del(courseViewCountKeys);
		// 删除课程订阅量的缓存
		RedisUtils.del(courseBuyCountKeys);

		//log.info("tch统计数据已成功写入数据库...");
	}


}
