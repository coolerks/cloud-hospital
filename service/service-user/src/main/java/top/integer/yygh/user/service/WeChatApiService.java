package top.integer.yygh.user.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.integer.yygh.common.exception.YyghException;
import top.integer.yygh.common.helper.JwtHelper;
import top.integer.yygh.common.result.ResultCodeEnum;
import top.integer.yygh.model.user.UserInfo;
import top.integer.yygh.user.mapper.UserInfoMapper;
import top.integer.yygh.user.service.operation.SmsCodeVerify;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class WeChatApiService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private SmsCodeVerify smsCodeVerify;

    public Map<String, Object> login(String openid) {
        LambdaUpdateWrapper<UserInfo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(UserInfo::getOpenid, openid);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            String key = UUID.randomUUID().toString();
            template.opsForValue().set(key, openid, 300, TimeUnit.SECONDS);
            throw new YyghException(ResultCodeEnum.NEED_BIND_PHONE, Map.of("key", key));
        }
        String name = userInfo.getName();
        if (name == null) {
            name = userInfo.getPhone();
        }
        return Map.of("name", name, "token", JwtHelper.createToken(userInfo.getId(), name));
    }

    public String bindPhone(String key, String code, String phone) {
        System.out.println("code = " + code);
        smsCodeVerify.verify(phone, code);
        String openid = template.opsForValue().get(key);
        if (openid == null) {
            throw new YyghException(ResultCodeEnum.DATA_ERROR);
        }
        LambdaUpdateWrapper<UserInfo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, phone);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        System.out.println("userInfo = " + userInfo);
        boolean exist = (userInfo == null);
        if (exist) {
            userInfo = new UserInfo();
            userInfo.setPhone(phone);
        }
        userInfo.setOpenid(openid);
        if (!exist) {
            userInfoMapper.update(userInfo, new LambdaUpdateWrapper<>());
        } else {
            userInfoMapper.insert(userInfo);
//            System.out.println("******************");
        }
        return openid;
    }
}
