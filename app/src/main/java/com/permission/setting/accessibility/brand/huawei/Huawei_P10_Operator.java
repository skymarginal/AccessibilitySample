package com.permission.setting.accessibility.brand.huawei;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.permission.setting.accessibility.brand.BrandOperatorInterface;
import com.permission.setting.accessibility.guide.GuideFloatManager;
import com.permission.setting.activity.FullscreenActivity;

import java.util.List;


/**
 * Created by 你的样子 on 2017/12/9.
 * 华为适配
 * @author gerile
 */
public class Huawei_P10_Operator implements BrandOperatorInterface {

    private Context mContext;

    private static Huawei_P10_Operator mInstance;

    public static Huawei_P10_Operator getInstance(){
        if(mInstance == null){
            synchronized (Huawei_P10_Operator.class){
                if (mInstance == null){
                    mInstance = new Huawei_P10_Operator();
                }
            }
        }
        return mInstance;
    }

    private boolean isConcatenon = false;

    @Override
    public void init(Context context) {
        Log.i("==TAG==","-- init --");
        mContext = context;

        if(!isConcatenon){
            //打开遮挡层
            GuideFloatManager.getInstance(mContext).showFullScreen();
            //跳转应用详情
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
            mContext.startActivity(localIntent);
            isConcatenon = true;
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("==TAG==","-- onAccessibilityEvent --");
        AccessibilityNodeInfo info = event.getSource();
        if(info == null) return;
        openApplyDetail(info);
    }

    private boolean detail_1 = false;
    private boolean detail_2 = false;
    private boolean detail_3 = false;
    private boolean detail_4 = false;

    /**
     * 打开应用详情   开启悬浮窗、应用自启动
     * */
    private void openApplyDetail(AccessibilityNodeInfo nodeInfo){
        if (!detail_1) {
            // 通过文字模糊搜索出相关节点
            List<AccessibilityNodeInfo> mNode = nodeInfo.findAccessibilityNodeInfosByText("权限");
            // 如果没有则滑动，直到找到为止
            if (mNode.size() > 0) {
                detail_1 = true;
                // 模拟点击该节点
                mNode.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            } else {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
        if (!detail_2) {
            List<AccessibilityNodeInfo> mNode2 = nodeInfo.findAccessibilityNodeInfosByText("设置单项权限");
            if (mNode2.size() > 0) {
                detail_2 = true;
                mNode2.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            } else {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
        if(!detail_3){
            List<AccessibilityNodeInfo> mNode3 = nodeInfo.findAccessibilityNodeInfosByText("应用自动启动");
            if (mNode3.size() > 0) {
                AccessibilityNodeInfo parent3 = mNode3.get(0).getParent();
                for(int i=0;i< parent3.getChildCount();i++){
                    AccessibilityNodeInfo child3 = parent3.getChild(i);
                    if( child3.getClassName().equals("android.widget.Switch")) {
                        if(!child3.isChecked()) {
                            child3.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                        detail_3 = true;
                    }
                }
            }
        }
        if(!detail_4){
            List<AccessibilityNodeInfo> mNode4 = nodeInfo.findAccessibilityNodeInfosByText("悬浮窗");
            if (mNode4.size() > 0) {
                AccessibilityNodeInfo parent4 = mNode4.get(0).getParent();
                for(int i=0;i< parent4.getChildCount();i++){
                    AccessibilityNodeInfo child4 = parent4.getChild(i);
                    if( child4.getClassName().equals("android.widget.Switch")) {
                        if(!child4.isChecked()) {
                            child4.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                        detail_4 = true;
                        //返回主页面 启动模式为singleTask，所以不会新建activity
                        FullscreenActivity.startFullscreenActivity(mContext);
                    }
                }
            }
        }
    }

}
