package com.xxxx.dao;

import com.xxxx.pojo.ValidateCode;

public interface SendyzmDao {
    ValidateCode getCode(String phone);

    void setCode(String phone, ValidateCode validateCode);

    Boolean deleteCode(String phone);
}
