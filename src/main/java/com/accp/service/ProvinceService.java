package com.accp.service;

import com.accp.pojo.Line;
import com.accp.pojo.Province;

import java.util.List;
import java.util.Map;

public interface ProvinceService {
    //查询
    public List<Province> getProAll();
    //添加
    public int addProvince(Line line);
    //修改
    public int updateProvince(Line line);
    //删除
    public int deleteProvince(int lineid);
    /**
     * 分页查询
     * @param map1
     * @return
     */
    public Map<String, Object> findMap(Map<String, Object> map1);
    //查询所有线路
    public List<Line> getLineAll();
}
