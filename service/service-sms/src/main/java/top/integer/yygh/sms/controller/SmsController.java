package top.integer.yygh.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.integer.yygh.common.result.Result;
import top.integer.yygh.sms.service.SmsService;

import java.text.ParseException;


@RequestMapping("/api/sms")
@RestController
public class SmsController {
    @Autowired
    private SmsService service;


    @GetMapping("/send/{phone}")
    public Result sendCode(@PathVariable String phone) throws ParseException {
        service.sendCode(phone);
        return Result.ok();
    }
}
