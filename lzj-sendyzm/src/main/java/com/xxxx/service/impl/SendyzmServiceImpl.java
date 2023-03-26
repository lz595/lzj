package com.xxxx.service.impl;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.dao.SendyzmDao;
import com.xxxx.pojo.ValidateCode;
import com.xxxx.service.SendyzmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class SendyzmServiceImpl implements SendyzmService {

    @Autowired
    private SendyzmDao sendyzmDao;
    @Override
    public LzjResult sendyzm(String phone) {

        if (sendyzmDao.getCode(phone) !=null){
            return LzjResult.error("验证码已发送，请问重新申请验证码！");
        }

        String code = generateVerificationCode(4);
        ValidateCode validateCode = new ValidateCode(phone,code);
        sendyzmDao.setCode(phone,validateCode);
        log.info("验证码为{}" , code);
        return LzjResult.success("验证码生成成功");
    }

    @Override
    public ValidateCode getCode(String phone) throws InterruptedException {

        return sendyzmDao.getCode(phone);
    }

    @Override
    public LzjResult deleteCode(String phone) {
        Boolean result = sendyzmDao.deleteCode(phone);
        if (result == true){
            return LzjResult.success("删除成功",result);
        }
        return LzjResult.error("删除失败");
    }


    public  String generateVerificationCode(int length) {
        String verificationCode = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10); // 生成 0~9 之间的随机数
            verificationCode += num; // 将生成的数字添加到验证码中
        }
        return verificationCode;
    }
}
