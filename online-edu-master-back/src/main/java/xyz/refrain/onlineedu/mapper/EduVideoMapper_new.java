package xyz.refrain.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import xyz.refrain.onlineedu.model.entity.EduVideoEntity;
import xyz.refrain.onlineedu.model.entity.EduVideoEntity_new;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 */
public interface EduVideoMapper_new extends BaseMapper<EduVideoEntity_new> {
    List<EduVideoEntity_new> selectListWithCourse(@Param("teacherId") Integer teacherId);

}
