package com.accp.service.impl;

import com.accp.dao.ProvinceDao;
import com.accp.pojo.Line;
import com.accp.pojo.Province;
import com.accp.service.ProvinceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao provinceDao;

    /**
     * 查询
     * @param provinceDao
     */
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    /**
     * 添加
     * @param province
     * @return
     */
    @Override
    public int addProvince(Province province) {
        return provinceDao.addProvince(province);
    }

    /**
     * 修改
     * @param line
     * @return
     */
    @Override
    public int updateProvince(Line line) {
        return this.provinceDao.updateProvince(line);
    }
    @Override
    public Map<String, Object> findMap(Map<String, Object> map1) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Line> list = this.provinceDao.findlist(map1);
            map.put("data",list);
            long count = this.provinceDao.count(map1);
            map.put("count", count);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "加载异常");
        }
        return map;
    }

    /**
     * 删除
     * @param lineid
     * @return
     */
    @Override
    public int deleteProvince(int lineid) {
        return this.provinceDao.deleteProvince(lineid);
    }

    @Override
    public List<Province> getProAll() {
        return provinceDao.getProAll();
    }
    //查询所有线路
    public List<Line> getLineAll(){
        return provinceDao.getLineAll();
    }
}
