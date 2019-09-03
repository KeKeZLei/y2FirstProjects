package com.accp.dao.impl;

import com.accp.dao.ProvinceDao;
import com.accp.pojo.Employee;
import com.accp.pojo.Line;
import com.accp.pojo.Province;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ProvinceDaoImpl implements ProvinceDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    /**
     * 查询
     * @return
     */
    @Override
    public List<Province> getProAll() {
        String hql =  "from Province p";
        List<Province> list = (List<Province>)this.hibernateTemplate.find(hql);
        return list;
    }

    /**
     * 修改
     * @param province
     * @return
     */
    @Override
    public int updateProvince(Line line) {
        this.hibernateTemplate.update(line);
        return 1;
    }

    /**
     * 获取城市
     * @return
     */
    @Override
    public List<Province> getCity() {
        String hql = "from Province";
        List<Province> list = (List<Province>)hibernateTemplate.find(hql);
        return list;
    }

    /**
     * 添加
     * @param province
     * @return
     */
    @Override
    public int addProvince(Line line) {
        return (int)this.hibernateTemplate.save(line);
    }

    /**
     * 模糊查询
     * @param map
     * @return
     */
    @Override
    public List<Line> findlist(final Map<String, Object> map) {
        List<Line> list = (List<Line>) this.hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sbf = new StringBuffer("from Line l where 1=1");
                if(map.containsKey("linename")){
                    sbf.append(" and l.linename like :linename");
                }
                Query query = session.createQuery(sbf.toString());
                query.setProperties(map);
                int page = (int) map.get("page");
                int limit = (int) map.get("limit");
                query.setFirstResult((page-1)*limit);
                query.setMaxResults(limit);
                return query.list();
            }
        });
        return list;
    }

    /**
     * 总数
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public long count(final Map<String, Object> map) {
        long count = (long) this.hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sbf = new StringBuffer("select count(l.lineid) from Line l where 1=1");
                if(map.containsKey("linename")){
                    sbf.append(" and l.linename like :linename");
                }
                Query query = session.createQuery(sbf.toString());
                query.setProperties(map);
                return query.uniqueResult();
            }
        });
        return count;
    }

    @Override
    public int deleteProvince(int lineid) {
        Line line = new Line();
        line.setLineid(lineid);
        hibernateTemplate.delete(line);
        return 1;
    }
    //查询所有线路
    public List<Line> getLineAll(){
        String hql="from Line";
        List<Line> list = (List<Line>) this.hibernateTemplate.find(hql);
        System.out.println(list.toString());
        return list;
    }
}
