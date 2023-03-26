package com.xxxx.feign;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.pojo.ValidateCode;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * SendyzmService的fallback工厂类
 */
@Component
public class SendyzmServiceFallbackFactory implements FallbackFactory<SendyzmService> {

    /**
     * 创建SendyzmService的fallback实例
     * @param throwable 服务调用失败时的异常信息
     * @return SendyzmService的fallback实例
     */
    @Override
    public SendyzmService create(Throwable throwable) {
        // 返回一个匿名内部类，实现SendyzmService接口
        return new SendyzmService() {
            /**
             * 获取验证码的方法，调用服务失败时触发
             * @param phone 手机号
             * @return 默认的验证码对象
             */
            @Override
            public ValidateCode getCode(String phone) {
                return new ValidateCode(phone,"error");
            }

            /**
             * 删除验证码的方法，调用服务失败时触发
             * @param phone 手机号
             * @return 默认的操作结果
             */
            @Override
            public LzjResult deleteCode(String phone) {
                return null;
            }
        };
    }
}
