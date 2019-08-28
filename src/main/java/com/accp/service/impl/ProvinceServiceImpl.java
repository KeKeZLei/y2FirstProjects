package com.accp.service.impl;

import com.accp.dao.ProvinceDao;
import com.accp.pojo.Province;
import com.accp.service.ProvinceService;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao provinceDao;

    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    @Override
    public int addProvince(Province province) {
        return provinceDao.addProvince(province);
    }

    @Override
    public List<Province> getProAll() {
        return provinceDao.getProAll();
    }
}
