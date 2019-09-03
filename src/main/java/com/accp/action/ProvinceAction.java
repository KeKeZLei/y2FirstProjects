package com.accp.action;

import com.accp.pojo.Line;
import com.accp.pojo.Province;
import com.accp.service.ProvinceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ProvinceAction extends ActionSupport {
    private int lineid;
    private int lineno;
    private String linename;

    public void setLinename(String linename) {
        this.linename = linename;
    }

    private  int length;
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

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public void setLength(int length) {
        this.length = length;
    }
    private ProvinceService provinceService;

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }
    private String pname;
    private int provinced;

    public int getProvinced() {
        return provinced;
    }

    public void setProvinced(int provinced) {
        this.provinced = provinced;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    private Province province;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * 查询
     * @return
     * @throws Exception
     */
    public String proAll() throws Exception {
        List<Province> list = provinceService.getProAll();
        System.out.println("list:"+list);
        ActionContext context = ActionContext.getContext();
        context.getContextMap().put("list",list);

        return super.execute();
    }
    /**
     * 添加
     */
    public String AddProvince() throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        Line line = new Line();
        line.setLineno(lineno);
        line.setLinename(linename);
        line.setLength(length);
        try{
            int count = this.provinceService.addProvince(line);
            map.put("code",0);
            map.put("msg","添加成功...");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",-1);
            map.put("msg","添加失败...");
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/application;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(map));
        return null;
    }

    /**
     * 修改
     * @return
     * @throws Exception
     */
    public String updateProvince()throws Exception{
        Map map = new HashMap();
        Line line = new Line(lineid,lineno,linename,length);
        System.out.println("name:"+linename);
        System.out.println("Line:"+line);
        try {
            int count = this.provinceService.updateProvince(line);
            System.out.println("count:"+count);
            map.put("code", 0);
            map.put("msg", "修改成功...");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "修改失败...");
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/application;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(map));
        return null;
    }

    /**
     * 删除
     * @return
     * @throws Exception
     */
    public String deleteProvince() throws Exception {
        Map map = new HashMap();
        System.out.println("map:"+map);
        try {
            int count = this.provinceService.deleteProvince(lineid);
            System.out.println("count:"+count);
            map.put("code", 0);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "删除失败");
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/application;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(map));
        return null;
    }

    /**
     * 模糊查询
     * @return
     * @throws Exception
     */
    public String page() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("limit", limit);
        System.out.println("线路编号："+userInfoName);
        if(userInfoName != null && !userInfoName.equals("")){
            System.out.println("模糊查询");
            map.put("userInfoName","%"+userInfoName+"%");
        }
        System.out.println("数据1"+map);
        Map<String, Object> map1 = this.provinceService.findMap(map);
        System.out.println("数据"+map1);
        String json = JSON.toJSONString(map1);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/application;charset=utf-8");
        response.getWriter().print(json);
        return null;
    }
    /**
     * 查询线路
     * @throws IOException
     */
    public void getAllLineName() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("textml;charset=utf-8");
        PrintWriter writer = response.getWriter();
        List<Line> list = provinceService.getLineAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lineList",list);
        //输出到 ajax 对象中
        System.out.println(jsonObject.toString());
        writer.write(jsonObject.toString());
    }

    /**
     * 添加下拉框
     * @throws IOException
     */
    public void getCity() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("textml;charset=utf-8");
        PrintWriter writer = response.getWriter();
        List<Province> list = provinceService.getCity();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listCity",list);
        //输出到 ajax 对象中
        System.out.println(jsonObject.toString());
        writer.write(jsonObject.toString());
    }
}
