package com.permission.setting.accessibility.brand;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by 你的样子 on 2017/12/9.
 * 模拟辅助服务接口
 * @author gerile
 */
public interface BrandOperatorInterface {

    void init(Context context);

    void onAccessibilityEvent(AccessibilityEvent event);

}
