package com.permission.setting.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;

import com.permission.setting.accessibility.brand.BrandConstant;
import com.permission.setting.accessibility.brand.BrandOperatorInterface;
import com.permission.setting.accessibility.brand.huawei.Huawei_P10_Operator;
import com.permission.setting.utils.CommonUtil;

/**
 * Created by 你的样子 on 2017/12/9.
 * 辅助服务
 * @author gerile
 */
public class MyAccessibilityService extends AccessibilityService {

    private String mBrand = "";
    private String mModel = "";

    private BrandOperatorInterface mOperatorService;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        configBrand();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        mOperatorService.onAccessibilityEvent(event);
        //回退到上一级
        //performGlobalAction(GLOBAL_ACTION_BACK);
    }

    @Override
    public void onInterrupt() {

    }

    //识别品牌
    private void configBrand(){
        String brandStr = CommonUtil.getBrand();
        String modelStr = CommonUtil.getModel();
        mBrand = (TextUtils.isEmpty(brandStr) ? "" : CommonUtil.letterTurn2Lowercase(brandStr));
        mModel = (TextUtils.isEmpty(modelStr) ? "" : modelStr);
        switch (mBrand){
            case BrandConstant.HUAWEI:
                configHuaweiModel(mModel);
                break;
            default:
                break;
        }
        if(mOperatorService != null){
            mOperatorService.init(this);
        }
    }

    //适配品牌机型
    private void configHuaweiModel(String model){
        switch (model){
            case BrandConstant.HUAWEI_P10:  //华为p10
                mOperatorService = Huawei_P10_Operator.getInstance();
                break;
            default:
                break;
        }
    }

}
