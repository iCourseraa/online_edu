package xyz.refrain.onlineedu.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.refrain.onlineedu.mapper.EduCourseMapper;
import xyz.refrain.onlineedu.mapper.RelCourseMemberMapper;
import xyz.refrain.onlineedu.model.entity.EduCourseEntity;
import xyz.refrain.onlineedu.model.entity.RelCourseMemberEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelCourseMemberService {

    @Autowired
    private RelCourseMemberMapper relCourseMemberMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;

    public void favorite(Integer courseId, Integer memberId) {
        QueryWrapper<RelCourseMemberEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId).eq("member_id", memberId);

        RelCourseMemberEntity entity = relCourseMemberMapper.selectOne(queryWrapper);
        if (entity == null) {
            entity = new RelCourseMemberEntity();
            entity.setCourseId(courseId);
            entity.setMemberId(memberId);
            entity.setIsFavorite(true);
            relCourseMemberMapper.insert(entity);
        } else {
            entity.setIsFavorite(true);
            relCourseMemberMapper.updateById(entity);
        }
    }

    public void unfavorite(Integer courseId, Integer memberId) {
        QueryWrapper<RelCourseMemberEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId).eq("member_id", memberId);

        RelCourseMemberEntity entity = relCourseMemberMapper.selectOne(queryWrapper);
        if (entity != null && entity.getIsFavorite()) {
            entity.setIsFavorite(false);
            relCourseMemberMapper.updateById(entity);
        }
    }

//    public List<RelCourseMemberEntity> list(Integer id){
//        LambdaQueryWrapper<RelCourseMemberEntity> queryWrapper = Wrappers.lambdaQuery();
//        queryWrapper.eq(RelCourseMemberEntity::getMemberId,id);
//        List<RelCourseMemberEntity> relCourseMemberEntities = relCourseMemberMapper.selectList(queryWrapper);
//
//        if (CollUtil.isEmpty(relCourseMemberEntities)){
//            return relCourseMemberEntities;
//        }
//
//        List<Integer> list = relCourseMemberEntities.stream().map(RelCourseMemberEntity::getCourseId).collect(Collectors.toList());
//        List<EduCourseEntity> eduCourseEntities = eduCourseMapper.selectBatchIds(list);
//
//        Map<Integer, String> collected = eduCourseEntities.stream().collect(Collectors.toMap(EduCourseEntity::getId, EduCourseEntity::getTitle));
//
//        relCourseMemberEntities.forEach( l ->{
//            l.setClassName(collected.get(l.getCourseId()));
//        });
//        return relCourseMemberEntities;
//    }
    public List<RelCourseMemberEntity> list(Integer id) {
        LambdaQueryWrapper<RelCourseMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelCourseMemberEntity::getMemberId, id);
        List<RelCourseMemberEntity> relCourseMemberEntities = relCourseMemberMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(relCourseMemberEntities)) {
            return relCourseMemberEntities;
        }

        // 获取课程ID列表
        List<Integer> courseIdList = relCourseMemberEntities.stream().map(RelCourseMemberEntity::getCourseId).collect(Collectors.toList());

        // 批量查询课程信息（包括 cover 字段）
        List<EduCourseEntity> eduCourseEntities = eduCourseMapper.selectBatchIds(courseIdList);

        // 创建两个映射，一个用于课程名称，一个用于图片链接
        Map<Integer, String> titleMap = eduCourseEntities.stream().collect(Collectors.toMap(EduCourseEntity::getId, EduCourseEntity::getTitle));
        Map<Integer, String> coverMap = eduCourseEntities.stream().collect(Collectors.toMap(EduCourseEntity::getId, EduCourseEntity::getCover));

        // 设置默认图片链接
        String defaultImageUrl = "";

        // 将课程名称和图片链接附加到结果中
        relCourseMemberEntities.forEach(l -> {
            l.setClassName(titleMap.getOrDefault(l.getCourseId(), "未知课程"));
            l.setCover(coverMap.getOrDefault(l.getCourseId(), defaultImageUrl));
        });

        return relCourseMemberEntities;
    }

    public List<RelCourseMemberEntity> listWarning(Integer id) {
        LambdaQueryWrapper<RelCourseMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelCourseMemberEntity::getMemberId, id);
        List<RelCourseMemberEntity> relCourseMemberEntities = relCourseMemberMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(relCourseMemberEntities)) {
            return relCourseMemberEntities; // 如果没有找到相关记录，直接返回空列表
        }

        // 提取所有课程ID
        List<Integer> courseIdList = relCourseMemberEntities.stream()
                .map(RelCourseMemberEntity::getCourseId)
                .distinct() // 确保课程ID唯一
                .collect(Collectors.toList());

        // 根据课程ID批量查询课程详情
        List<EduCourseEntity> eduCourseEntities = eduCourseMapper.selectBatchIds(courseIdList);

        // 过滤并构建更新状态为"修改"的课程列表
        List<EduCourseEntity> updatedCourses = eduCourseEntities.stream()
                .filter(x -> "修改".equals(x.getUpdateStatus()))
                .collect(Collectors.toList());

        // 构建最终结果列表，包含课程ID和标题信息
        List<RelCourseMemberEntity> result = updatedCourses.stream().map(course -> {
            RelCourseMemberEntity memberEntity = new RelCourseMemberEntity();
            memberEntity.setClassName("课程：" + course.getTitle() + ", 已更新内容，请注意查看。");
            memberEntity.setCourseId(course.getId()); // 设置课程ID
            // 假设RelCourseMemberEntity中有一个可以设置课程名称的方法，例如setCourseTitle
            // memberEntity.setCourseTitle(course.getTitle()); // 设置课程标题
            return memberEntity;
        }).collect(Collectors.toList());

        return result;
    }
}