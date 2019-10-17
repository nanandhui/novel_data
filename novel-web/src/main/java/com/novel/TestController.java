package com.novel;

import com.novel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program novel-core
 * @description: 测试test
 * @author: wang
 * @create: 2019/08/09 18:20
 */
@Controller
@RequestMapping("index")
public class TestController {

    @Resource
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        userService.test();
        System.out.println("TestController..........!");
        return "test......";
    }
}
