package com.example.myapplication.proxy;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

public class FakeInstrumentation extends Instrumentation  {

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(className.equals("com.example.myapplication.NDKActivity")){
            className="com.example.myapplication.MainActivity";
        }
        return super.newActivity(cl, className, intent);
    }
}
