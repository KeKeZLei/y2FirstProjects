package com.accp.service.impl;

import com.accp.dao.LineDao;
import com.accp.pojo.Line;
import com.accp.service.LineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineServiceImpl implements LineService {
    private LineDao lineDao;

    public void setLineDao(LineDao lineDao) {
        this.lineDao = lineDao;
    }

    @Override
    public Map<String, Object> findMap(Map<String, Object> map1) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Line> list = this.lineDao.findlist(map1);
            map.put("data",list);
            long count = this.lineDao.count(map1);
            map.put("count", count);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "加载异常");
        }
        return map;
    }
}
