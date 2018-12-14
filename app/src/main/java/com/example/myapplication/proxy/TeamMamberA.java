package com.example.myapplication.proxy;

import android.util.Log;

public class TeamMamberA implements TeamMamber {

    public TeamMamberA(String name) {
        this.name=name;
    }
    String name;

    @Override
    public void reviewCode() {
        Log.e("TeamMamberA","A小组成员" + name + "代码review过了");
    }
}
