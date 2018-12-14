package com.example.myapplication.pickerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityConfig.WheelType;
import com.lljjcoder.style.citypickerview.R.id;
import com.lljjcoder.style.citypickerview.R.layout;
import com.lljjcoder.style.citypickerview.R.style;
import com.lljjcoder.style.citypickerview.widget.CanShow;
import com.lljjcoder.style.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lljjcoder.style.citypickerview.widget.wheel.WheelView;
import com.lljjcoder.style.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;
import com.lljjcoder.utils.utils;
import java.util.ArrayList;
import java.util.List;

public class CityPickerView implements CanShow, OnWheelChangedListener {
    private String TAG = "citypicker_log";
    private PopupWindow popwindow;
    private View popview;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private RelativeLayout mRelativeTitleBg;
    private TextView mTvOK;
    private TextView mTvTitle;
    private TextView mTvCancel;
    private OnCityItemClickListener mBaseListener;
    private CityParseHelper parseHelper;
    private CityConfig config;
    private Context context;
    private ProvinceBean[] proArra;

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        this.mBaseListener = listener;
    }

    public CityPickerView() {
    }

    public void setConfig(CityConfig config) {
        this.config = config;
    }

    public void init(Context context) {
        this.context = context;
        this.parseHelper = new CityParseHelper();
        if (this.parseHelper.getProvinceBeanArrayList().isEmpty()) {
            this.parseHelper.initData(context);
        }

    }

    private void initCityPickerPopwindow() {
        if (this.config == null) {
            throw new IllegalArgumentException("please set config first...");
        } else {
            if (this.parseHelper == null) {
                this.parseHelper = new CityParseHelper();
                if (this.parseHelper.getProvinceBeanArrayList().isEmpty()) {
                    throw new IllegalArgumentException("请在Activity中增加init操作");
                }
            }

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            this.popview = layoutInflater.inflate(layout.pop_citypicker, (ViewGroup)null);
            this.mViewProvince = (WheelView)this.popview.findViewById(id.id_province);
            this.mViewCity = (WheelView)this.popview.findViewById(id.id_city);
            this.mViewDistrict = (WheelView)this.popview.findViewById(id.id_district);
            this.mRelativeTitleBg = (RelativeLayout)this.popview.findViewById(id.rl_title);
            this.mTvOK = (TextView)this.popview.findViewById(id.tv_confirm);
            this.mTvTitle = (TextView)this.popview.findViewById(id.tv_title);
            this.mTvCancel = (TextView)this.popview.findViewById(id.tv_cancel);
            this.popwindow = new PopupWindow(this.popview, -1, -2);
            this.popwindow.setAnimationStyle(style.AnimBottom);
            this.popwindow.setBackgroundDrawable(new ColorDrawable());
            this.popwindow.setTouchable(true);
            this.popwindow.setOutsideTouchable(false);
            this.popwindow.setFocusable(true);
            this.popwindow.setOnDismissListener(new OnDismissListener() {
                public void onDismiss() {
                    if (CityPickerView.this.config.isShowBackground()) {
                        utils.setBackgroundAlpha(CityPickerView.this.context, 1.0F);
                    }

                }
            });
            if (!TextUtils.isEmpty(this.config.getTitleBackgroundColorStr())) {
                if (this.config.getTitleBackgroundColorStr().startsWith("#")) {
                    this.mRelativeTitleBg.setBackgroundColor(Color.parseColor(this.config.getTitleBackgroundColorStr()));
                } else {
                    this.mRelativeTitleBg.setBackgroundColor(Color.parseColor("#" + this.config.getTitleBackgroundColorStr()));
                }
            }

            if (!TextUtils.isEmpty(this.config.getTitle())) {
                this.mTvTitle.setText(this.config.getTitle());
            }

            if (this.config.getTitleTextSize() > 0) {
                this.mTvTitle.setTextSize((float)this.config.getTitleTextSize());
            }

            if (!TextUtils.isEmpty(this.config.getTitleTextColorStr())) {
                if (this.config.getTitleTextColorStr().startsWith("#")) {
                    this.mTvTitle.setTextColor(Color.parseColor(this.config.getTitleTextColorStr()));
                } else {
                    this.mTvTitle.setTextColor(Color.parseColor("#" + this.config.getTitleTextColorStr()));
                }
            }

            if (!TextUtils.isEmpty(this.config.getConfirmTextColorStr())) {
                if (this.config.getConfirmTextColorStr().startsWith("#")) {
                    this.mTvOK.setTextColor(Color.parseColor(this.config.getConfirmTextColorStr()));
                } else {
                    this.mTvOK.setTextColor(Color.parseColor("#" + this.config.getConfirmTextColorStr()));
                }
            }

            if (!TextUtils.isEmpty(this.config.getConfirmText())) {
                this.mTvOK.setText(this.config.getConfirmText());
            }

            if (this.config.getConfirmTextSize() > 0) {
                this.mTvOK.setTextSize((float)this.config.getConfirmTextSize());
            }

            if (!TextUtils.isEmpty(this.config.getCancelTextColorStr())) {
                if (this.config.getCancelTextColorStr().startsWith("#")) {
                    this.mTvCancel.setTextColor(Color.parseColor(this.config.getCancelTextColorStr()));
                } else {
                    this.mTvCancel.setTextColor(Color.parseColor("#" + this.config.getCancelTextColorStr()));
                }
            }

            if (!TextUtils.isEmpty(this.config.getCancelText())) {
                this.mTvCancel.setText(this.config.getCancelText());
            }

            if (this.config.getCancelTextSize() > 0) {
                this.mTvCancel.setTextSize((float)this.config.getCancelTextSize());
            }

            if (this.config.getWheelType() == WheelType.PRO) {
                this.mViewCity.setVisibility(8);
                this.mViewDistrict.setVisibility(8);
            } else if (this.config.getWheelType() == WheelType.PRO_CITY) {
                this.mViewDistrict.setVisibility(8);
            } else {
                this.mViewProvince.setVisibility(0);
                this.mViewCity.setVisibility(0);
                this.mViewDistrict.setVisibility(0);
            }

            this.mViewProvince.addChangingListener(this);
            this.mViewCity.addChangingListener(this);
            this.mViewDistrict.addChangingListener(this);
            this.mTvCancel.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    CityPickerView.this.mBaseListener.onCancel();
                    CityPickerView.this.hide();
                }
            });
            this.mTvOK.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (CityPickerView.this.parseHelper != null) {
                        if (CityPickerView.this.config.getWheelType() == WheelType.PRO) {
                            CityPickerView.this.mBaseListener.onSelected(CityPickerView.this.parseHelper.getProvinceBean(), new CityBean(), new DistrictBean());
                        } else if (CityPickerView.this.config.getWheelType() == WheelType.PRO_CITY) {
                            CityPickerView.this.mBaseListener.onSelected(CityPickerView.this.parseHelper.getProvinceBean(), CityPickerView.this.parseHelper.getCityBean(), new DistrictBean());
                        } else {
                            CityPickerView.this.mBaseListener.onSelected(CityPickerView.this.parseHelper.getProvinceBean(), CityPickerView.this.parseHelper.getCityBean(), CityPickerView.this.parseHelper.getDistrictBean());
                        }
                    } else {
                        CityPickerView.this.mBaseListener.onSelected(new ProvinceBean(), new CityBean(), new DistrictBean());
                    }

                    CityPickerView.this.hide();
                }
            });
            this.setUpData();
            if (this.config != null && this.config.isShowBackground()) {
                utils.setBackgroundAlpha(this.context, 0.5F);
            }

        }
    }

    private ProvinceBean[] getProArrData(ProvinceBean[] array) {
        List<ProvinceBean> provinceBeanList = new ArrayList();

        int i;
        for(i = 0; i < array.length; ++i) {
            provinceBeanList.add(array[i]);
        }

        if (!this.config.isShowGAT()) {
            provinceBeanList.remove(provinceBeanList.size() - 1);
            provinceBeanList.remove(provinceBeanList.size() - 1);
            provinceBeanList.remove(provinceBeanList.size() - 1);
        }

        this.proArra = new ProvinceBean[provinceBeanList.size()];

        for(i = 0; i < provinceBeanList.size(); ++i) {
            this.proArra[i] = (ProvinceBean)provinceBeanList.get(i);
        }

        return this.proArra;
    }

    private void setUpData() {
        if (this.parseHelper != null && this.config != null) {
            this.getProArrData(this.parseHelper.getProvinceBeenArray());
            int provinceDefault = -1;
            if (!TextUtils.isEmpty(this.config.getDefaultProvinceName()) && this.proArra.length > 0) {
                for(int i = 0; i < this.proArra.length; ++i) {
                    if (this.proArra[i].getName().contains(this.config.getDefaultProvinceName())) {
                        provinceDefault = i;
                        break;
                    }
                }
            }

            ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(this.context, this.proArra);
            this.mViewProvince.setViewAdapter(arrayWheelAdapter);
            if (this.config.getCustomItemLayout() != CityConfig.NONE && this.config.getCustomItemTextViewId() != CityConfig.NONE) {
                arrayWheelAdapter.setItemResource(this.config.getCustomItemLayout());
                arrayWheelAdapter.setItemTextResource(this.config.getCustomItemTextViewId());
            } else {
                arrayWheelAdapter.setItemResource(layout.default_item_city);
                arrayWheelAdapter.setItemTextResource(id.default_item_city_name_tv);
            }

            if (-1 != provinceDefault) {
                this.mViewProvince.setCurrentItem(provinceDefault);
            }

            this.mViewProvince.setVisibleItems(this.config.getVisibleItems());
            this.mViewCity.setVisibleItems(this.config.getVisibleItems());
            this.mViewDistrict.setVisibleItems(this.config.getVisibleItems());
            this.mViewProvince.setCyclic(this.config.isProvinceCyclic());
            this.mViewCity.setCyclic(this.config.isCityCyclic());
            this.mViewDistrict.setCyclic(this.config.isDistrictCyclic());
            this.mViewProvince.setDrawShadows(this.config.isDrawShadows());
            this.mViewCity.setDrawShadows(this.config.isDrawShadows());
            this.mViewDistrict.setDrawShadows(this.config.isDrawShadows());
            this.mViewProvince.setLineColorStr(this.config.getLineColor());
            this.mViewProvince.setLineWidth(this.config.getLineHeigh());
            this.mViewCity.setLineColorStr(this.config.getLineColor());
            this.mViewCity.setLineWidth(this.config.getLineHeigh());
            this.mViewDistrict.setLineColorStr(this.config.getLineColor());
            this.mViewDistrict.setLineWidth(this.config.getLineHeigh());
            this.updateCities();
            this.updateAreas();
        }
    }

    private void updateCities() {
        if (this.parseHelper != null && this.config != null) {
            int pCurrent = this.mViewProvince.getCurrentItem();
            ProvinceBean mProvinceBean = this.proArra[pCurrent];
            this.parseHelper.setProvinceBean(mProvinceBean);
            if (this.parseHelper.getPro_CityMap() != null) {
                CityBean[] cities = (CityBean[])this.parseHelper.getPro_CityMap().get(mProvinceBean.getName());
                if (cities != null) {
                    int cityDefault = -1;
                    if (!TextUtils.isEmpty(this.config.getDefaultCityName()) && cities.length > 0) {
                        for(int i = 0; i < cities.length; ++i) {
                            if (this.config.getDefaultCityName().contains(cities[i].getName())) {
                                cityDefault = i;
                                break;
                            }
                        }
                    }

                    ArrayWheelAdapter cityWheel = new ArrayWheelAdapter(this.context, cities);
                    if (this.config.getCustomItemLayout() != CityConfig.NONE && this.config.getCustomItemTextViewId() != CityConfig.NONE) {
                        cityWheel.setItemResource(this.config.getCustomItemLayout());
                        cityWheel.setItemTextResource(this.config.getCustomItemTextViewId());
                    } else {
                        cityWheel.setItemResource(layout.default_item_city);
                        cityWheel.setItemTextResource(id.default_item_city_name_tv);
                    }

                    this.mViewCity.setViewAdapter(cityWheel);
                    if (-1 != cityDefault) {
                        this.mViewCity.setCurrentItem(cityDefault);
                    } else {
                        this.mViewCity.setCurrentItem(0);
                    }

                    this.updateAreas();
                }
            }
        }
    }

    private void updateAreas() {
        int pCurrent = this.mViewCity.getCurrentItem();
        if (this.parseHelper.getPro_CityMap() != null && this.parseHelper.getCity_DisMap() != null) {
            if (this.config.getWheelType() == WheelType.PRO_CITY || this.config.getWheelType() == WheelType.PRO_CITY_DIS) {
                CityBean mCityBean = ((CityBean[])this.parseHelper.getPro_CityMap().get(this.parseHelper.getProvinceBean().getName()))[pCurrent];
                this.parseHelper.setCityBean(mCityBean);
                if (this.config.getWheelType() == WheelType.PRO_CITY_DIS) {
                    DistrictBean[] areas = (DistrictBean[])this.parseHelper.getCity_DisMap().get(this.parseHelper.getProvinceBean().getName() + mCityBean.getName());
                    if (areas == null) {
                        return;
                    }

                    int districtDefault = -1;
                    if (!TextUtils.isEmpty(this.config.getDefaultDistrict()) && areas.length > 0) {
                        for(int i = 0; i < areas.length; ++i) {
                            if (this.config.getDefaultDistrict().contains(areas[i].getName())) {
                                districtDefault = i;
                                break;
                            }
                        }
                    }

                    ArrayWheelAdapter districtWheel = new ArrayWheelAdapter(this.context, areas);
                    if (this.config.getCustomItemLayout() != CityConfig.NONE && this.config.getCustomItemTextViewId() != CityConfig.NONE) {
                        districtWheel.setItemResource(this.config.getCustomItemLayout());
                        districtWheel.setItemTextResource(this.config.getCustomItemTextViewId());
                    } else {
                        districtWheel.setItemResource(layout.default_item_city);
                        districtWheel.setItemTextResource(id.default_item_city_name_tv);
                    }

                    this.mViewDistrict.setViewAdapter(districtWheel);
                    DistrictBean mDistrictBean = null;
                    if (this.parseHelper.getDisMap() == null) {
                        return;
                    }

                    if (-1 != districtDefault) {
                        this.mViewDistrict.setCurrentItem(districtDefault);
                        mDistrictBean = (DistrictBean)this.parseHelper.getDisMap().get(this.parseHelper.getProvinceBean().getName() + mCityBean.getName() + this.config.getDefaultDistrict());
                    } else {
                        this.mViewDistrict.setCurrentItem(0);
                        if (areas.length > 0) {
                            mDistrictBean = areas[0];
                        }
                    }

                    this.parseHelper.setDistrictBean(mDistrictBean);
                }
            }

        }
    }

    public void showCityPicker() {
        this.initCityPickerPopwindow();
        if (!this.isShow()) {
            this.popwindow.showAtLocation(this.popview, 80, 0, 0);
        }

    }

    public void hide() {
        if (this.isShow()) {
            this.popwindow.dismiss();
        }

    }

    public boolean isShow() {
        return this.popwindow.isShowing();
    }

    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == this.mViewProvince) {
            this.updateCities();
        } else if (wheel == this.mViewCity) {
            this.updateAreas();
        } else if (wheel == this.mViewDistrict && this.parseHelper != null && this.parseHelper.getCity_DisMap() != null) {
            DistrictBean mDistrictBean = ((DistrictBean[])this.parseHelper.getCity_DisMap().get(this.parseHelper.getProvinceBean().getName() + this.parseHelper.getCityBean().getName()))[newValue];
            this.parseHelper.setDistrictBean(mDistrictBean);
        }

    }
}
