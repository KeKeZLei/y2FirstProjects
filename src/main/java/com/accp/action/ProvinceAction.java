package com.accp.action;

import com.accp.pojo.Province;
import com.accp.service.ProvinceService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProvinceAction extends ActionSupport {
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

    /**
     * 查询
     * @return
     * @throws Exception
     */
    public String proAll() throws Exception {
        List<Province> list = provinceService.getProAll();
        ActionContext context = ActionContext.getContext();
        context.getContextMap().put("list",list);
        System.out.println("list:"+list);
        return super.execute();
    }
    /**
     * 添加
     */
    public String AddProvince() throws IOException {
        Province province = new Province(provinced,pname);
        HashMap<String, Object> map = new HashMap<>();
        try{
            this.provinceService.addProvince(province);
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
}
