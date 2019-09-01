package com.accp.dao;

import com.accp.pojo.Line;
import com.accp.pojo.Province;

import java.util.List;
import java.util.Map;

public interface ProvinceDao {
    //查询所有线路
    public List<Line> getLineAll();
    //查询
    public List<Province> getProAll();
    //添加
    public int addProvince(Province province);
    //修改
    public int updateProvince(Line line);
    //删除
    public int deleteProvince(int lineid);
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
