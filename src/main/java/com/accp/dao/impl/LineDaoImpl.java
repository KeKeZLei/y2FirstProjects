package com.accp.dao.impl;

import com.accp.dao.LineDao;
import com.accp.pojo.Line;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
public class LineDaoImpl  implements LineDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    @Override
    public List<Line> findlist(final Map<String, Object> map) {
        List<Line> list = (List<Line>) this.hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sbf = new StringBuffer("from Line l where 1=1");
                if(map.containsKey("lineno")){
                    sbf.append(" and l.lineno like :lineno");
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

    @Override
    public long count(final Map<String, Object> map) {
        long count = (long) this.hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sbf = new StringBuffer("select count(l.lineid) from Line l where 1=1");
                if(map.containsKey("lineno")){
                    sbf.append(" and l.lineno like :l.lineno");
                }
                Query query = session.createQuery(sbf.toString());
                query.setProperties(map);
                return query.uniqueResult();
            }
        });
        return count;
    }
}
