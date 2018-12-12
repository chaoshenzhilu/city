package com.example.myapplication;

import android.util.Log;

public class JNIHandle {
    public String getStringForJava(JNIHandle jniHandle){
        Log.e("JNIHandle   ",jniHandle.toString());
        Log.e("JNIHandle   ",name);
        return "string form method in java";
    }

    private String name="aaaaaa";


    @Override
    public String toString() {
        return "JNIHandle{" +
                "name='" + name + '\'' +
                '}';
    }
}
