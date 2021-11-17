package com.wenshuo.eduService.controller;

import com.wenshuo.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","wenshuo").data("avatar","https://img2.baidu.com/it/u=2277439039,162033509&fm=26&fmt=auto");
    }

}
