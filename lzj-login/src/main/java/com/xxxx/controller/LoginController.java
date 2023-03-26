package com.xxxx.controller;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录接口
     * @param username 用户名
     * @param password 验证码
     * @return 登录结果
     */
    @PostMapping
    public LzjResult login(String username,String password){
        // 校验用户名和验证码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return LzjResult.error("用户名和验证码不能为空");
        }
        // 校验用户名和验证码是否匹配
        if (!Pattern.matches("^1[3-9]\\d{9}$", username)) {
            return LzjResult.error("请输入正确的手机号");
        }
        if (!Pattern.matches("^\\d{4}$", password)) {
            return LzjResult.error("请输入正确的验证码");
        }
        // 调用登录服务，返回登录结果
        return loginService.login(username,password);
    }
}
