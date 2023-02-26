package top.integer.yygh.sms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.integer.yygh.common.exception.YyghException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;


import com.aliyuncs.dysmsapi.model.v20170525.*;

@Service
@RefreshScope
@Slf4j
public class SmsService {
    @Value("${sms.access-key.id}")
    private String keyId;
    @Value("${sms.access-key.secret}")
    private String keySecret;
    @Autowired
    private StringRedisTemplate template;
    private Random random = new Random();

    public void sendCode(String phone) throws ParseException {
        String code = String.valueOf(random.nextInt(100000, 999999));
        String count = template.opsForValue().get("count" + phone);
        if (count != null && Integer.parseInt(count) >= 5) {
            throw new YyghException("今日获取验证码已达上限，请明日再试。", 400);
        }
        Long time = template.getExpire(phone);
        if (time != null && time > 0 && time >= 240) {
            throw new YyghException("请勿频繁获取验证码，请稍后再试。", 400);
        }
        template.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().plusDays(1).toString());
        template.opsForValue().increment("count" + phone);
        template.expire("count" + phone, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", this.keyId, this.keySecret);
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/

        IAcsClient client = new DefaultAcsClient(profile);


        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("Moyok");
        request.setTemplateCode("SMS_271410187");
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\""+ code +"\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
            throw new YyghException("短信发送失败", 400);
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            throw new YyghException("短信发送失败", 400);
        }
    }
}
