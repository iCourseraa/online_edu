package xyz.refrain.onlineedu.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.refrain.onlineedu.constant.RS;
import xyz.refrain.onlineedu.mapper.UctrMemberMapper;
import xyz.refrain.onlineedu.mapper.UserLookNumberMapper;
import xyz.refrain.onlineedu.model.entity.AclUserEntity;
import xyz.refrain.onlineedu.model.entity.EduVideoNumberEntity;
import xyz.refrain.onlineedu.model.params.LoginParam;
import xyz.refrain.onlineedu.model.securtiy.AclUserDetail;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.model.vo.admin.AclUserVO;
import xyz.refrain.onlineedu.utils.RUtils;
import xyz.refrain.onlineedu.utils.SessionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserLookNumberService {
    @Resource
    private UserLookNumberMapper userLookNumberMapper;

    public int insert(EduVideoNumberEntity numberEntity) {
        int insert = userLookNumberMapper.insert(numberEntity);
        return insert;
    }

    public List<EduVideoNumberEntity> selectAll(EduVideoNumberEntity entity) {
       List<EduVideoNumberEntity> list =  userLookNumberMapper.selectAll(entity);
//       log.info(list.toString());
       return list;
    }
//public List<EduVideoNumberEntity> selectByTeacher(Integer teacherId, EduVideoNumberEntity entity) {
//    // 查询 teacherId 对应的 course_id 列表
//    List<Integer> courseIds = userLookNumberMapper.selectCourseIdsByTeacherId(teacherId);
//    if (courseIds.isEmpty()) {
//        return Collections.emptyList(); // 如果没有课程，直接返回空列表
//    }
//
//    // 查询 course_id 对应的 video_id 列表
//    List<Integer> videoIds = userLookNumberMapper.selectVideoIdsByCourseIds(courseIds);
//    if (videoIds.isEmpty()) {
//        return Collections.emptyList(); // 如果没有视频，直接返回空列表
//    }
//    // 将 videoIds 转换为 List<String>
//    List<String> videoIdStrings = videoIds.stream()
//            .map(String::valueOf)
//            .collect(Collectors.toList());
//
//    // 查询 video_id 对应的观看数据
////    entity.setVideoIds(videoIdStrings); // 设置过滤条件
//    return userLookNumberMapper.selectAllByVideoIds(videoIdStrings);
//}

}
