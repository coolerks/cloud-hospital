package top.integer.yygh.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.user.bean.AccessToken;
import top.integer.yygh.user.component.WeChatLoginComponent;
import top.integer.yygh.user.feign.AccessTokenClient;
import top.integer.yygh.user.service.WeChatApiService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/ucenter")
public class WeChatApiController {
    @Autowired
    private AccessTokenClient client;

    @Autowired
    private WeChatApiService service;

    @Value("${wx.front_end.redirect_url}")
    private String redirectUrl;

    @ResponseBody
    @RequestMapping("/param")
    public Result getQrCodeParam() throws UnsupportedEncodingException {
        return Result.ok(Map.of(
                "appid", WeChatLoginComponent.WX_OPEN_APP_ID,
                "scope", "snsapi_login",
                "state", String.valueOf(System.currentTimeMillis()),
                "redirect_uri", URLEncoder.encode(WeChatLoginComponent.WX_OPEN_REDIRECT_URL, "utf-8")
        ));
    }



    @GetMapping("/wx/callback")
    public String redirect(@RequestParam String code) {
        return "redirect:" + redirectUrl + "?code=" + code;
    }

    @ResponseBody
    @GetMapping("/vxlogin")
    public Result wechatLogin(@RequestParam String code) {
        AccessToken accessToken = client.getAccessToken(WeChatLoginComponent.WX_OPEN_APP_ID, WeChatLoginComponent.WX_OPEN_APP_SECRET,
                code, "authorization_code");
        String openid = accessToken.getOpenid();
//        System.out.println(accessToken);
        if (openid == null) {
            return Result.fail("认证失败");
        }
        return Result.ok(service.login(openid));
    }

    @ResponseBody
    @GetMapping("/bind")
    public Result bindPhone(@RequestParam String key, @RequestParam String code, @RequestParam String phone) {
        String openid = service.bindPhone(key, code, phone);
        return Result.ok(service.login(openid));
    }
}
