package com.xxxx.service;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.pojo.ValidateCode;

public interface SendyzmService {
    LzjResult sendyzm(String phone);

    ValidateCode getCode(String phone) throws InterruptedException;

    LzjResult deleteCode(String phone);
}
