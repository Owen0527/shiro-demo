package com.jc.springboot.shirodemo.controller;

import com.jc.cloud.common.constant.ResultModel;
import com.jc.cloud.common.controller.BaseController;
import com.jc.springboot.shirodemo.shiro.CachedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShiroController
 *
 * @author OwenZhu
 * @date 18/7/25
 */
@RestController
@Slf4j
@Api("用户登录后操作")
@RequestMapping("/rest/shiro")
public class ShiroController extends BaseController {

    @GetMapping("/perm")
    public ResultModel perm() {
        return buildSuccessResponse("有权限操作");
    }


    @GetMapping("/loginInfo")
    @ApiOperation("登录信息")
    public ResultModel loginInfo() {
        CachedUser cachedUser = (CachedUser) SecurityUtils.getSubject().getPrincipal();
        return buildSuccessResponse(cachedUser);
    }

    @ApiOperation(value = "退出登录，清除用户登录数据")
    @GetMapping(value = "/logout")
    @ResponseBody
    public ResultModel logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.info("登出成功");
        return buildSuccessResponse();
    }

}
