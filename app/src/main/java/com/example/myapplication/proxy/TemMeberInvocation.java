package com.example.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TemMeberInvocation<T> implements InvocationHandler {
    T target;

    public TemMeberInvocation(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备动态代理执行" + method.getName() + "方法");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("执行的时间为" + df.format(new Date()));
        Object result = method.invoke(target, args);
        return null;
    }
}
