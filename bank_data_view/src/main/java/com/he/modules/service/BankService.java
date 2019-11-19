package com.he.modules.service;

import org.codehaus.jettison.json.JSONArray;

public interface BankService {
    /**
     * 查询数据
     * @return
     */
    JSONArray queryData(String table);
}
