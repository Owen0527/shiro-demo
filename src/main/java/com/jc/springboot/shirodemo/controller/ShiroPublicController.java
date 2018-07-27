package com.jc.springboot.shirodemo.controller;

import com.jc.cloud.common.constant.ResultModel;
import com.jc.cloud.common.controller.BaseController;
import com.jc.springboot.shirodemo.vo.LoginVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShiroPublicController
 *
 * @author OwenZhu
 * @date 18/7/25
 */
@RestController
@Api("用户不需要登录就能操作")
@Slf4j
public class ShiroPublicController extends BaseController {

    @GetMapping("/unAuth")
    public ResultModel unAuth() {
        log.info("unAuth");
        return buildAuthInvalidResponse("未登录");
    }

    @GetMapping("/403")
    public ResultModel noPermission() {
        log.info("noPermission");
        return buildNoPermissionResponse();
    }

    @GetMapping("/index")
    public ResultModel index() {
        log.info("index");
        return buildSuccessResponse("已登录");
    }

    @PostMapping("/ajaxLogin")
    public ResultModel unAuth(@RequestBody LoginVO vo) {
        log.info("ajaxLogin begin: {}", vo.getLoginName());
        ResultModel<Object> result = new ResultModel<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(vo.getLoginName(), vo.getPw());
        try {
            subject.login(token);
            result.setCode(ResultModel.RESULT_SUCCESS);
            result.setMsg("登录成功");
            log.debug("login sessionId={}", subject.getSession().getId());
        } catch (Exception e) {
            log.error("ajaxLogin error", e);
            result.setCode(ResultModel.RESULT_ERROR);
            result.setMsg("账户或者密码错误");
        }
        return result;
    }

}
