package xyz.refrain.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import xyz.refrain.onlineedu.model.entity.RelCourseMemberEntity;

/**
 * <p>
 * 课程订阅-学员关系表 Mapper 接口
 * </p>
 *
 */
public interface RelCourseMemberMapper extends BaseMapper<RelCourseMemberEntity> {

    @Select("select count(*) from rel_course_member where course_id = #{id}  and is_favorite = 1")
    public int selectTotal(long id);
}
