package top.integer.yygh.user.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.integer.yygh.common.exception.YyghException;
import top.integer.yygh.common.helper.JwtHelper;
import top.integer.yygh.common.result.ResultCodeEnum;
import top.integer.yygh.enums.AuthStatusEnum;
import top.integer.yygh.model.user.UserInfo;
import top.integer.yygh.user.mapper.UserInfoMapper;
import top.integer.yygh.user.service.operation.SmsCodeVerify;
import top.integer.yygh.vo.user.LoginVo;
import top.integer.yygh.vo.user.UserAuthVo;

import java.util.Map;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> implements SmsCodeVerify {

    @Autowired
    StringRedisTemplate template;

    @Override
    public void verify(String phone, String verifyCode) {
        // 判断验证码一致性
        String code = template.opsForValue().get(phone);
        if (code == null || !code.equals(verifyCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }
        template.delete(phone);
    }

    public Map<String, Object> login(LoginVo loginVo) {
        // 获取手机号和验证码
        String phone = loginVo.getPhone();
        String verifyCode = loginVo.getCode();
        // 判断手机号验证码是否为空
        if (phone == null || "".equals(phone) || verifyCode == null || "".equals(verifyCode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }

        // 判断验证码一致性
        verify(phone, verifyCode);
        // 判断是否第一次登录
        LambdaUpdateWrapper<UserInfo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, phone);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(phone);
            userInfo.setName("");
            userInfo.setStatus(1);
            baseMapper.insert(userInfo);
        }
        if (userInfo.getStatus() == 0) {
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        // 返回登录信息，token等
        String name = userInfo.getName();
        if (name == null || "".equals(name)) {
            name = userInfo.getNickName();
        }
        if (name == null || "".equals(name)) {
            name = userInfo.getPhone();
        }
        return Map.of("name", name, "token", JwtHelper.createToken(userInfo.getId(), name));
    }

    public void userAuth(long userId, UserAuthVo userAuthVo) {
        UserInfo userInfo = baseMapper.selectById(userId);
        //设置认证信息
        //认证人姓名
        userInfo.setName(userAuthVo.getName());
        //其他认证信息
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());
        //进行信息更新
        baseMapper.updateById(userInfo);

    }
}
