package top.integer.yygh.user.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.Util;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     * 微信登录通过code获取access_token时，响应体的content-type为 text/plain
     * feign默认情况下只能够接受content-type为 application/json
     */
    @Bean
    public Decoder feignDecoder() {
        return (res, type) -> {
            String body = Util.toString(res.body().asReader(Util.UTF_8));
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            return new ObjectMapper().readValue(body, javaType);
        };
    }

}
