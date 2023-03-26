package com.xxxx.feign;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.pojo.ValidateCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 发送短信验证码服务Feign客户端接口
 */
@FeignClient(value = "lzj-sendyzm" , fallbackFactory = SendyzmServiceFallbackFactory.class)
public interface SendyzmService {

    /**
     * 获取短信验证码
     * @param phone 手机号码
     * @return ValidateCode
     */
    @GetMapping("/sendyzm/getCode")
    public ValidateCode getCode(@RequestParam("phone") String phone);

    /**
     * 删除短信验证码
     * @param phone 手机号码
     * @return LzjResult
     */
    @DeleteMapping("/sendyzm/deleteCode")
    public LzjResult deleteCode(@RequestParam("phone") String phone);

}
