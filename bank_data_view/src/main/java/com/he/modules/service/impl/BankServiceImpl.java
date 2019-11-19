package com.he.modules.service.impl;

import com.he.modules.dao.BankDao;
import com.he.modules.service.BankService;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class BankServiceImpl implements BankService{
    /**
     * 自动装载
     */
    @Resource(name = "bankDao")
    private BankDao bankDao;

    /**
     * 查询数据
     * @return
     */
    @Override
    public JSONArray queryData(String table){
        return bankDao.conHbase(table);
    }

}
