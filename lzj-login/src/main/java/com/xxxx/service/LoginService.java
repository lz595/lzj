package com.xxxx.service;

import com.xxxx.commons.enums.result.LzjResult;

public interface LoginService {
    LzjResult login(String username, String password);
}
