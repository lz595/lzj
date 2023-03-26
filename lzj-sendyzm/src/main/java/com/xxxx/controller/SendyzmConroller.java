package com.xxxx.controller;

import com.xxxx.commons.enums.result.LzjResult;
import com.xxxx.pojo.ValidateCode;
import com.xxxx.service.SendyzmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendyzm")
public class SendyzmConroller {

    @Autowired
    private SendyzmService sendyzmService;

    @PostMapping
    public LzjResult sendyzm(@RequestParam String phone){
        return sendyzmService.sendyzm(phone);
    }

    @GetMapping("/getCode")
    public ValidateCode getCode(@RequestParam String phone) throws InterruptedException {
        return sendyzmService.getCode(phone);
    }

    @DeleteMapping("/deleteCode")
    public LzjResult deleteCode(@RequestParam String phone){
        return sendyzmService.deleteCode(phone);
    }

}
