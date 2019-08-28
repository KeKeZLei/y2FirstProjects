package com.accp.dao;

import com.accp.pojo.Province;

import java.util.List;

public interface ProvinceDao {
    //查询
    public List<Province> getProAll();
    //添加
    public int addProvince(Province province);
}
