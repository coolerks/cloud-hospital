package top.integer.yygh.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.common.utils.AuthContextHolder;
import top.integer.yygh.model.user.UserInfo;
import top.integer.yygh.user.service.UserInfoService;
import top.integer.yygh.vo.user.LoginVo;
import top.integer.yygh.vo.user.UserAuthVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Autowired
    private UserInfoService service;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        Map<String, Object> result = service.login(loginVo);
        return Result.ok(result);
    }

    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        service.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
        return Result.ok();
    }

    //获取用户id信息接口
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = service.getById(userId);
        return Result.ok(userInfo);
    }

}
