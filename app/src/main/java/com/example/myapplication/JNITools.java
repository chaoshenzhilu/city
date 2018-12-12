package com.example.myapplication;

public class JNITools {
    NDKActivity activity;

    public JNITools(NDKActivity activity) {
        this. activity=activity;
    }
    public void setText(int num,String actvity){
        activity.setText(actvity+" NDK方法计算= "+num);
    }


    public native void addNum(int num1,int num2,String NDKActivity);

    public native void caLLJavaMehodFormJni();

}
