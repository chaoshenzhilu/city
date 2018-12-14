package com.example.myapplication.pickerview;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import com.google.gson.Gson;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.utils.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityParseHelper {
    private ArrayList<ProvinceBean> mProvinceBeanArrayList = new ArrayList();
    private ArrayList<ArrayList<CityBean>> mCityBeanArrayList;
    private ArrayList<ArrayList<ArrayList<DistrictBean>>> mDistrictBeanArrayList;
    private ProvinceBean[] mProvinceBeenArray;
    private ProvinceBean mProvinceBean;
    private CityBean mCityBean;
    private DistrictBean mDistrictBean;
    private Map<String, CityBean[]> mPro_CityMap = new HashMap();
    private Map<String, DistrictBean[]> mCity_DisMap = new HashMap();
    private Map<String, DistrictBean> mDisMap = new HashMap();

    public ArrayList<ProvinceBean> getProvinceBeanArrayList() {
        return this.mProvinceBeanArrayList;
    }

    public void setProvinceBeanArrayList(ArrayList<ProvinceBean> provinceBeanArrayList) {
        this.mProvinceBeanArrayList = provinceBeanArrayList;
    }

    public ArrayList<ArrayList<CityBean>> getCityBeanArrayList() {
        return this.mCityBeanArrayList;
    }

    public void setCityBeanArrayList(ArrayList<ArrayList<CityBean>> cityBeanArrayList) {
        this.mCityBeanArrayList = cityBeanArrayList;
    }

    public ArrayList<ArrayList<ArrayList<DistrictBean>>> getDistrictBeanArrayList() {
        return this.mDistrictBeanArrayList;
    }

    public void setDistrictBeanArrayList(ArrayList<ArrayList<ArrayList<DistrictBean>>> districtBeanArrayList) {
        this.mDistrictBeanArrayList = districtBeanArrayList;
    }

    public ProvinceBean[] getProvinceBeenArray() {
        return this.mProvinceBeenArray;
    }

    public void setProvinceBeenArray(ProvinceBean[] provinceBeenArray) {
        this.mProvinceBeenArray = provinceBeenArray;
    }

    public ProvinceBean getProvinceBean() {
        return this.mProvinceBean;
    }

    public void setProvinceBean(ProvinceBean provinceBean) {
        this.mProvinceBean = provinceBean;
    }

    public CityBean getCityBean() {
        return this.mCityBean;
    }

    public void setCityBean(CityBean cityBean) {
        this.mCityBean = cityBean;
    }

    public DistrictBean getDistrictBean() {
        return this.mDistrictBean;
    }

    public void setDistrictBean(DistrictBean districtBean) {
        this.mDistrictBean = districtBean;
    }

    public Map<String, CityBean[]> getPro_CityMap() {
        return this.mPro_CityMap;
    }

    public void setPro_CityMap(Map<String, CityBean[]> pro_CityMap) {
        this.mPro_CityMap = pro_CityMap;
    }

    public Map<String, DistrictBean[]> getCity_DisMap() {
        return this.mCity_DisMap;
    }

    public void setCity_DisMap(Map<String, DistrictBean[]> city_DisMap) {
        this.mCity_DisMap = city_DisMap;
    }

    public Map<String, DistrictBean> getDisMap() {
        return this.mDisMap;
    }

    public void setDisMap(Map<String, DistrictBean> disMap) {
        this.mDisMap = disMap;
    }

    public CityParseHelper() {
    }

    public void initData(Context context) {
        String json = utils.getJson(context, "cityjson.txt");
//        Type type = (new TypeToken<ArrayList<CityResult>>() {
//        }).getType();

        CityResult cityResult=new Gson().fromJson(json,CityResult.class);
        ArrayList<CityJson> jsonList = cityResult.data;
        ArrayList<ProvinceBean> provinceBeans=new ArrayList<>();
        ArrayList<CityBean> cityBeans=new ArrayList<>();
        ArrayList<DistrictBean> districtBeans=new ArrayList<>();
        String id="";
        int TYPE=1;
        ProvinceBean provinceBean = null;
        CityBean citybean= null;
        DistrictBean districtBean= null;
        for (int i=jsonList.size()-1; i>=0; i--) {
            CityJson cityJson=jsonList.get(i);
            if (!id.equals(cityJson.parentId)) {
                if(TYPE==1){
                    if(provinceBean!=null){
                        Collections.reverse(cityBeans);
                        provinceBean.setCityList(cityBeans);
                    }
                    cityBeans=new ArrayList<>();
                }else if(TYPE==2){
                    if(citybean!=null){
                        Collections.reverse(districtBeans);
                        citybean.setCityList(districtBeans);
                    }
                    districtBeans=new ArrayList<>();
                }
            }
            TYPE=cityJson.levelType;

            if(TYPE==1){
                provinceBean=new ProvinceBean();
                provinceBean.setId(cityJson.id);
                provinceBean.setName(cityJson.name);
                provinceBeans.add(provinceBean);
                id=cityJson.id;
            }else if (TYPE==2){
                citybean=new CityBean();
                citybean.setId(cityJson.id);
                citybean.setName(cityJson.name);
                cityBeans.add(citybean);
                id=cityJson.parentId;
            }else if (TYPE==3){
                districtBean=new DistrictBean();
                districtBean.setId(cityJson.id);
                districtBean.setName(cityJson.name);
                districtBeans.add(districtBean);
                id=cityJson.parentId;
            }
        }
        if(provinceBean!=null){
            provinceBean.setCityList(cityBeans);
        }
        Collections.reverse(provinceBeans);
        this.mProvinceBeanArrayList =provinceBeans;
        if (this.mProvinceBeanArrayList != null && !this.mProvinceBeanArrayList.isEmpty()) {
            this.mCityBeanArrayList = new ArrayList(this.mProvinceBeanArrayList.size());
            this.mDistrictBeanArrayList = new ArrayList(this.mProvinceBeanArrayList.size());
            if (this.mProvinceBeanArrayList != null && !this.mProvinceBeanArrayList.isEmpty()) {
                this.mProvinceBean = (ProvinceBean)this.mProvinceBeanArrayList.get(0);
                List<CityBean> cityList = this.mProvinceBean.getCityList();
                if (cityList != null && !cityList.isEmpty() && cityList.size() > 0) {
                    this.mCityBean = (CityBean)cityList.get(0);
                    List<DistrictBean> districtList = this.mCityBean.getCityList();
                    if (districtList != null && !districtList.isEmpty() && districtList.size() > 0) {
                        this.mDistrictBean = (DistrictBean)districtList.get(0);
                    }
                }
            }

            this.mProvinceBeenArray = new ProvinceBean[this.mProvinceBeanArrayList.size()];

            for(int p = 0; p < this.mProvinceBeanArrayList.size(); ++p) {
                ProvinceBean itemProvince = (ProvinceBean)this.mProvinceBeanArrayList.get(p);
                ArrayList<CityBean> cityList = itemProvince.getCityList();
                CityBean[] cityNames = new CityBean[cityList.size()];

                for(int j = 0; j < cityList.size(); ++j) {
                    cityNames[j] = (CityBean)cityList.get(j);
                    List<DistrictBean> districtList = ((CityBean)cityList.get(j)).getCityList();
                    if (districtList == null) {
                        break;
                    }

                    DistrictBean[] distrinctArray = new DistrictBean[districtList.size()];

                    for(int k = 0; k < districtList.size(); ++k) {
                        DistrictBean districtModel = (DistrictBean)districtList.get(k);
                        this.mDisMap.put(itemProvince.getName() + cityNames[j].getName() + ((DistrictBean)districtList.get(k)).getName(), districtModel);
                        distrinctArray[k] = districtModel;
                    }

                    this.mCity_DisMap.put(itemProvince.getName() + cityNames[j].getName(), distrinctArray);
                }

                this.mPro_CityMap.put(itemProvince.getName(), cityNames);
                this.mCityBeanArrayList.add(cityList);
                ArrayList<ArrayList<DistrictBean>> array2DistrictLists = new ArrayList(cityList.size());

                for(int c = 0; c < cityList.size(); ++c) {
                    CityBean cityBean = (CityBean)cityList.get(c);
                    array2DistrictLists.add(cityBean.getCityList());
                }

                this.mDistrictBeanArrayList.add(array2DistrictLists);
                this.mProvinceBeenArray[p] = itemProvince;
            }

        }
    }
}
