package com.accp.dao.impl;

import com.accp.dao.ProvinceDao;
import com.accp.pojo.Province;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;
@SuppressWarnings("all")
public class ProvinceDaoImpl extends HibernateDaoSupport implements ProvinceDao {
    @Override
    public List<Province> getProAll() {
        String hql =  "select p.pname from Province p";
        List<Province> list = (List<Province>)this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public int addProvince(Province province) {
        return (int)this.getHibernateTemplate().save(province);
    }
}
