package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


import com.example.myapplication.pickerview.CityPickerView;
import com.example.myapplication.proxy.MemberProxy;
import com.example.myapplication.proxy.TeamMamber;
import com.example.myapplication.proxy.TeamMamberA;
import com.example.myapplication.proxy.TemMeberInvocation;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;

import java.lang.reflect.Proxy;


public class NDKActivity extends AppCompatActivity {
    Button button;
    TextView ndk_TextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        button = findViewById(R.id.ndk_Button);
        ndk_TextView = findViewById(R.id.ndk_TextView);

        CityPickerView cityPickerView = new CityPickerView();

        cityPickerView.init(this);
        CityConfig cityConfig = new CityConfig.Builder()
                .provinceCyclic(false)//省份滚轮是否可以循环滚动;
                .cityCyclic(false)
                .districtCyclic(false)
                .setShowGAT(true)//是否显示 香港澳门台湾  false的话会删除省的最后三条
                .build();
        cityPickerView.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                ToastUtils.showLongToast(NDKActivity.this, province.toString() + city.toString() + district.toString());
                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(NDKActivity.this, "已取消");
            }
        });
        button.setOnClickListener(v -> {
            JNITools jniTools = new JNITools(this);
            jniTools.addNum(55, 44, "NDKActivity");
            jniTools.caLLJavaMehodFormJni();
            //显示
            cityPickerView.showCityPicker();
        });
        //静态代理
//        TeamMamberA teamMamberA=new TeamMamberA("组员A");
//        MemberProxy proxy=new MemberProxy(teamMamberA);
//        proxy.reviewCode();
//      动态代理
        TeamMamberA teamMamberA = new TeamMamberA("组员B");
        TemMeberInvocation<TeamMamberA> invocation=new TemMeberInvocation<>(teamMamberA);
        TeamMamber teamMamber= (TeamMamber) Proxy.newProxyInstance(TeamMamber.class.getClassLoader(),new Class[]{TeamMamber.class},invocation);
        teamMamber.reviewCode();

    }

    public void setText(String num) {
        ndk_TextView.setText(num);
    }


}
