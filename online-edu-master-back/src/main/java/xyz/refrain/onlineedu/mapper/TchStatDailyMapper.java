package xyz.refrain.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.refrain.onlineedu.model.entity.TchStatDailyEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 */
public interface TchStatDailyMapper extends BaseMapper<TchStatDailyEntity> {

    /**
     * 查询指定时间范围内的课程订阅量总和
     *
     * @param courseId 课程ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 课程订阅量总和
     */
    @Select("SELECT date, SUM(video_view_count) AS videoViewCount " +
            "FROM tch_stat_daily " +
            "WHERE course_id = #{courseId} " +
            "AND date >= #{start} " +
            "AND date <= #{end} " +
            "GROUP BY date " +
            "ORDER BY date ASC")
    List<Map<String, Object>> selectDailyVideoViewCount(@Param("courseId") Integer courseId,
                                                        @Param("start") LocalDateTime start,
                                                        @Param("end") LocalDateTime end);

    /**
     * 查询指定时间范围内的课程观看量总和
     *
     * @param courseId 课程ID
     * @param start    开始时间
     * @param end      结束时间
     * @return 课程观看量总和
     */
    @Select("SELECT date, SUM(course_buy_count) AS courseBuyCount " +
            "FROM tch_stat_daily " +
            "WHERE course_id = #{courseId} " +
            "AND date >= #{start} " +
            "AND date <= #{end} " +
            "GROUP BY date " +
            "ORDER BY date ASC")
    List<Map<String, Object>> selectDailyCourseBuyCount(@Param("courseId") Integer courseId,
                                                        @Param("start") LocalDateTime start,
                                                        @Param("end") LocalDateTime end);
}
