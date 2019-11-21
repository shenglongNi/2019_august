package com.nsl.august.web.controller;

import com.nsl.august.web.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OkController {


    @RequestMapping("/ok")
    public Object ok() {
        return ResponseUtil.success();
    }
}
