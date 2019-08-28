package com.accp.service;

import com.accp.pojo.Line;

import java.util.List;
import java.util.Map;

public interface LineService {
    /**
     * 分页查询
     * @param map1
     * @return
     */
    public Map<String, Object> findMap(Map<String, Object> map1);
}
