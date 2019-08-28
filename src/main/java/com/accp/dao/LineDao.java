package com.accp.dao;

import com.accp.pojo.Line;

import java.util.List;
import java.util.Map;

public interface LineDao {
    /**
     * 分页查询
     * 模糊查询
     * @param map
     * @return
     */
    public List<Line> findlist(final Map<String, Object> map);

    /**
     * 查询总记录数
     * @param map
     * @return
     */
    public long count(Map<String, Object> map);
}
