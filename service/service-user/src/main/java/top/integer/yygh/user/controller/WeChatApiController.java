package top.integer.yygh.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.user.component.WeChatLoginComponent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/api/user/login/vx")
public class WeChatApiController {
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
}
