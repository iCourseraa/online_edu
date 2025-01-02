package xyz.refrain.onlineedu.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import xyz.refrain.onlineedu.mapper.EduCourseMapper;
import xyz.refrain.onlineedu.mapper.RelCourseStudyMapper;
import xyz.refrain.onlineedu.model.entity.EduCourseEntity;
import xyz.refrain.onlineedu.model.entity.RelCourseStudyEntity;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.utils.RUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RelCourseStudyService {

    @Resource
    RelCourseStudyMapper relCourseStudyMapper;
    @Resource
    private EduCourseMapper eduCourseMapper;


    public R study(Integer cid , Integer mid){

        LambdaQueryWrapper<RelCourseStudyEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelCourseStudyEntity::getCourseId,cid)
                .eq(RelCourseStudyEntity::getMemberId,mid);
        Integer i = relCourseStudyMapper.selectCount(queryWrapper);
        if (i > 0){
            return RUtils.success("");
        }
        RelCourseStudyEntity relCourseStudyEntity = new RelCourseStudyEntity();
        relCourseStudyEntity.setMemberId(mid);
        relCourseStudyEntity.setCourseId(cid);
        relCourseStudyEntity.setCreateTime(LocalDateTime.now());
        relCourseStudyMapper.insert(relCourseStudyEntity);
        return RUtils.success("加入成功");
    }

    public List<RelCourseStudyEntity> list(Integer id){
        LambdaQueryWrapper<RelCourseStudyEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RelCourseStudyEntity::getMemberId,id);
        List<RelCourseStudyEntity> relCourseMemberEntities = relCourseStudyMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(relCourseMemberEntities)){
            return relCourseMemberEntities;
        }

        List<Integer> list = relCourseMemberEntities.stream().map(RelCourseStudyEntity::getCourseId).collect(Collectors.toList());
        List<EduCourseEntity> eduCourseEntities = eduCourseMapper.selectBatchIds(list);

        Map<Integer, String> collected = eduCourseEntities.stream().collect(Collectors.toMap(EduCourseEntity::getId, EduCourseEntity::getTitle));

        relCourseMemberEntities.forEach( l ->{
            l.setClassName(collected.get(l.getCourseId()));
        });
        return relCourseMemberEntities;
    }
}