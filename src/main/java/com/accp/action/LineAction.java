package com.accp.action;

import com.accp.pojo.Line;
import com.accp.service.LineService;
import com.accp.service.UsersService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineAction extends ActionSupport {
    private LineService lineService;

    public void setLineService(LineService lineService) {
        this.lineService = lineService;
    }
    private int page = 1;
    private int limit = 10;
    private String userInfoName;
    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLineno() {
        return lineno;
    }

    public String page() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("limit", limit);
        System.out.println(lineno);
        if(lineno != 0){
            map.put("lineno","%"+lineno+"%");
        }
        System.out.println("数据1"+map);
        Map<String, Object> map1 = this.lineService.findMap(map);
        System.out.println("数据"+map1);
        String json = JSON.toJSONString(map1);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/application;charset=utf-8");
        response.getWriter().print(json);
        return null;
    }
    private int lineid;
    private int lineno;
    private String linename;

    public void setLinename(String linename) {
        this.linename = linename;
    }

    private  int length;

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
