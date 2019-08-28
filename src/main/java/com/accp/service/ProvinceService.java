package com.accp.service;

import com.accp.pojo.Province;

import java.util.List;

public interface ProvinceService {
    //查询
    public List<Province> getProAll();
    //添加
    public int addProvince(Province province);
}
