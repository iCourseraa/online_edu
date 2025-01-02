package xyz.refrain.onlineedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.refrain.onlineedu.model.entity.EduVideoNumberEntity;
import xyz.refrain.onlineedu.model.entity.UctrMemberEntity;

import java.util.List;

@Mapper
public interface UserLookNumberMapper  extends BaseMapper<EduVideoNumberEntity> {

    List<EduVideoNumberEntity> selectAll(@Param("entity") EduVideoNumberEntity entity);
}
//@Mapper
//public interface UserLookNumberMapper extends BaseMapper<EduVideoNumberEntity> {
//
//    List<EduVideoNumberEntity> selectAll(EduVideoNumberEntity entity);
//    @Select("SELECT id FROM edu_course WHERE teacher_id = #{teacherId}")
//    List<Integer> selectCourseIdsByTeacherId(@Param("teacherId") Integer teacherId);
//
//    @Select("<script>" +
//            "SELECT id FROM edu_video WHERE course_id IN " +
//            "<foreach item='id' collection='courseIds' open='(' separator=',' close=')'>" +
//            "#{id}" +
//            "</foreach>" +
//            "</script>")
//    List<Integer> selectVideoIdsByCourseIds(@Param("courseIds") List<Integer> courseIds);
//
//    @Select("<script>" +
//            "SELECT * FROM edu_video_number WHERE video_id IN " +
//            "<foreach item='id' collection='videoIds' open='(' separator=',' close=')'>" +
//            "#{id}" +
//            "</foreach>" +
//            "</script>")
//    List<EduVideoNumberEntity> selectAllByVideoIds(@Param("videoIds") List<String> videoIds);
//
//}
