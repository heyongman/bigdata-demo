package com.he.modules.controller;

import com.he.modules.service.UserInfoService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class UserInfoController {
    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    /**
     * 获取地区
     * @return
     */
    @RequestMapping("getRegion")
    @ResponseBody
    public JSONArray getRegion(){
        JSONArray region = userInfoService.getRegion();
        return region;
    }
    /**
     * 获取浏览器
     * @return
     */
    @RequestMapping("getBrowser")
    @ResponseBody
    public JSONArray getBrowser(){
        JSONArray browser = userInfoService.getBrowser();
        return browser;
    }
}
