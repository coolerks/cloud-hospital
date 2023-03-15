package top.integer.yygh.user.feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.integer.yygh.user.bean.AccessToken;
import top.integer.yygh.user.vo.AccessTokenVo;

@FeignClient(url = "https://api.weixin.qq.com/sns/oauth2", name = "wechat")
@Component
public interface AccessTokenClient {
    @GetMapping("/access_token")
    AccessToken getAccessToken(@RequestParam("appid") String appid, @RequestParam("secret") String secret,
                               @RequestParam("code") String code, @RequestParam("grant_type") String grant_type);
}
