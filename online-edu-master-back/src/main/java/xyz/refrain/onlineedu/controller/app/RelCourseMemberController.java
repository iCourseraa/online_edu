package xyz.refrain.onlineedu.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.refrain.onlineedu.constant.RS;
import xyz.refrain.onlineedu.model.securtiy.UctrMemberDetail;
import xyz.refrain.onlineedu.model.vo.R;
import xyz.refrain.onlineedu.service.RelCourseMemberService;
import xyz.refrain.onlineedu.service.RelCourseStudyService;
import xyz.refrain.onlineedu.utils.RUtils;
import xyz.refrain.onlineedu.utils.SessionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/rel-course-member")
public class RelCourseMemberController {

    @Autowired
    private RelCourseMemberService relCourseMemberService;
    @Resource
    private RelCourseStudyService relCourseStudyService;
    @PostMapping("/favorite")
    public ResponseEntity<Map<String, Object>> favorite(@RequestParam Integer courseId, @RequestParam Integer memberId) {
        try {
            relCourseMemberService.favorite(courseId, memberId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/unfavorite")
    public ResponseEntity<Map<String, Object>> unfavorite(@RequestParam Integer courseId, @RequestParam Integer memberId) {
        try {
            relCourseMemberService.unfavorite(courseId, memberId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/list/{id}")
    public R relList(@PathVariable("id") Integer id){
        return RUtils.success("success",relCourseMemberService.list(id));
    }

    @GetMapping("/isbuy/{id}")
    public R getIsStudy(@PathVariable("id") @Min(1) Integer id, HttpServletRequest request) {
        UctrMemberDetail member = SessionUtils.getMember(request);
        if (Objects.isNull(member)) {
            return new R(RS.NOT_LOGIN.status(), "请登录后再操作");
        }
        return relCourseStudyService.study(id,member.getId());
    }

    @GetMapping("/listStudy/{id}")
    public R listStudy(@PathVariable("id") Integer id){
        return RUtils.success("success",relCourseStudyService.list(id));
    }

    @GetMapping("/listWarning/{id}")
    public R listWarning(@PathVariable("id") Integer id){
        return RUtils.success("success",relCourseMemberService.listWarning(id));
    }
}