package com.he.modules.controller;

import com.he.modules.service.BankService;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class BankController {
    @Resource(name = "bankServiceImpl")
    private BankService bankService;

    /**
     * 查询数据
     * @return
     */
    @RequestMapping(value = "threeDayBig",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String threeDayBig(){
        JSONArray dataList = bankService.queryData("three_day_big");
        return dataList.toString();
    }
    /**
     * 查询数据
     * @return
     */
    @RequestMapping(value = "fiveDayTop",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String fiveDayTop(){
        JSONArray dataList = bankService.queryData("five_day_top");
        if (dataList!=null){
            return dataList.toString();
        }else {
            return "";
        }
    }
    /**
     * 查询数据
     * @return
     */
    @RequestMapping(value = "erroStatusCustomer",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String erroStatusCustomer(){
        JSONArray dataList = bankService.queryData("erro_status_customer");
        return dataList.toString();
    }
}
