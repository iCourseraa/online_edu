package xyz.refrain.onlineedu.controller.app;


import cn.hutool.core.date.DateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.refrain.onlineedu.annotation.AccessLimit;
import xyz.refrain.onlineedu.constant.RS;
import xyz.refrain.onlineedu.constant.StatConstant;
import xyz.refrain.onlineedu.model.entity.EduVideoNumberEntity;
import xyz.refrain.onlineedu.model.params.RegisterParam;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.service.UserLookNumberService;
import xyz.refrain.onlineedu.utils.RUtils;
import xyz.refrain.onlineedu.utils.RedisUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Validated
@RestController("AppUserLookNumberController")
@RequestMapping("/api/app/user/number")
@Api(value = "前台学生观看数据", tags = {"前台学生观看数据员接口"})
public class UserLookNumberController {

    @Resource
    private UserLookNumberService userLookNumberService;

    @AccessLimit(maxCount = 10)
    @PostMapping("/insert")
    @ApiOperation("添加")
    public R insert(@RequestBody EduVideoNumberEntity numberEntity) {
        int insert = userLookNumberService.insert(numberEntity);

        return RUtils.success("成功",insert);

    }
}
