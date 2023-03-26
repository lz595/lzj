/**
 *
 * 验证码发送数据访问实现类
 */
package com.xxxx.dao.impl;

import com.xxxx.dao.SendyzmDao;
import com.xxxx.pojo.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class SendyzmDaoImpl implements SendyzmDao {

    @Autowired
    private RedisTemplate redisTemplate;


    @Value("${lzj.cache.sendyzm.prefix}")// 获取发送验证码的redis key的前缀，可以通过配置文件进行修改
    private String sendyzmPrefix;

    /**
     * 根据手机号获取验证码对象
     * @param phone 手机号
     * @return ValidateCode 验证码对象
     */
    @Override
    public ValidateCode getCode(String phone) {
        return (ValidateCode) redisTemplate.opsForValue().get(sendyzmPrefix + phone);
    }

    @Override
    public void setCode(String phone, ValidateCode validateCode) {
        redisTemplate.opsForValue().set(sendyzmPrefix+phone,validateCode, Duration.ofMinutes(2L));
    }

    @Override
    public Boolean deleteCode(String phone) {
        return redisTemplate.delete(sendyzmPrefix+phone);
    }
}
