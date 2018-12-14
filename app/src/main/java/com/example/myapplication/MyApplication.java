package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.proxy.FakeInstrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyApplication extends Application {
    static Object activityThreadInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Class<?>  activityThread =Class.forName("android.app.ActivityThread");
            Method currenActivity=activityThread.getDeclaredMethod("currentActivityThread");
            activityThreadInstance=currenActivity.invoke(null);
            Field field=activityThreadInstance.getClass().getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            FakeInstrumentation fakeInstrumentation=new FakeInstrumentation();
            field.set(activityThreadInstance,fakeInstrumentation);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


//        try{
//
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

    }
}
