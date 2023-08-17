package com.chuang.bootplus.controller.test;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lcc
 * @create 2021-05-30
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Api(tags = {"测试"})
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("test1")
    public String test1(){
        return "测试成功！";
    }
}
