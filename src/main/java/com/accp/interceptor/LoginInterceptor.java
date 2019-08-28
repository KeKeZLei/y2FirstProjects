package com.accp.interceptor;

import com.accp.action.UsersAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginInterceptor implements Interceptor {
    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }

    @Override
    public void init() {
        System.out.println("初始化方法");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //proxy.getActionName()；获取对应的action的名称
        Object  myAction = actionInvocation.getAction();
        System.out.println("登录方法");
        if(myAction instanceof UsersAction){
            System.out.println("登录成功");
            System.out.println("放行");
            return actionInvocation.invoke();
       }else{
//            String username = (String) ServletActionContext.getRequest().getSession().getAttribute("username");
//            System.out.println(username);
//            if(username==null){
//                System.out.println("用户未登录");
//                return Action.LOGIN;
//            }else{
                System.out.println("用户已登录");
                //放行
                return actionInvocation.invoke();
            }
    }
}
