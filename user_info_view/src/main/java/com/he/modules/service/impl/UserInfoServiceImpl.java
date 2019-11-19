package com.he.modules.service.impl;

import com.he.modules.dao.UserInfoDao;
import com.he.modules.service.UserInfoService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Resource(name = "userInfoDao")
    private UserInfoDao userInfoDao;

    @Override
    public JSONArray getRegion() {
        JSONArray region = userInfoDao.getRegion();
        return region;
    }

    @Override
    public JSONArray getBrowser() {
        JSONArray browser = userInfoDao.getBrowser();
        return browser;
    }
}
