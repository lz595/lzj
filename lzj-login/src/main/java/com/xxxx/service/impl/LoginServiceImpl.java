package com.xxxx.service.impl;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.dao.LoginDao;
import com.xxxx.feign.SendyzmService;
import com.xxxx.pojo.ValidateCode;
import com.xxxx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

//    @Autowired
//    private LoginDao loginDao;

    @Autowired
    private SendyzmService sendyzmService;

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 验证码
     * @return 登录结果
     */
    @Override
    public LzjResult login(String username, String password) {
        // 调用发送验证码的服务，获取验证码对象
        ValidateCode code = sendyzmService.getCode(username);
        // 如果获取验证码对象失败，返回登录失败的结果
        if (code != null && code.getCode().equals("error")) {
            return LzjResult.error("登录失败,请稍后再试");
        }
        // 如果获取验证码对象为空，返回验证码生成失败的结果
        if (code == null) {
            return LzjResult.error("验证码生成失败");
        }
        // 如果验证码不匹配，返回验证码输入错误的结果
        if (!code.getCode().equals(password)) {
            return LzjResult.error("验证码输入错误");
        }
        // 删除验证码对象，返回登录成功的结果
        sendyzmService.deleteCode(username);
        return LzjResult.success("登录成功");
    }
}
