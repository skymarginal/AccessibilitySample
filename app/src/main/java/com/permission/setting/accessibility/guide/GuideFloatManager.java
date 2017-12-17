package com.permission.setting.accessibility.guide;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.permission.setting.R;
import com.permission.setting.accessibility.guide.view.GuideFloatView;

/**
 * Created by 你的样子 on 2017/12/17.
 * 引导悬浮页管理
 * @author gerile
 */
public class GuideFloatManager {

    private static GuideFloatManager floatManager;

    private Context context;

    private WindowManager windowManager;
    private LayoutParams layoutParams;
    private GuideFloatView floatView;
    private View fullView;

    public static GuideFloatManager getInstance(Context context){
        if(floatManager == null){
            floatManager = new GuideFloatManager(context);
        }
        return floatManager;
    }

    private GuideFloatManager(Context context){
        this.context = context;
        init();
    }

    private void init(){
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        layoutParams = new LayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.type = LayoutParams.TYPE_TOAST;
        layoutParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.format = PixelFormat.RGBA_8888;
    }

    public void showBall(){
        destroyBall();
        floatView = new GuideFloatView(context);
        windowManager.addView(floatView,layoutParams);
    }

    public void showFullScreen(){
        destroyFullScreen();
        fullView = LayoutInflater.from(context).inflate(R.layout.window_full_guide,null);
        fullView.findViewById(R.id.full_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyFullScreen();
            }
        });
        windowManager.addView(fullView,layoutParams);
    }

    public void destroyBall(){
        if(floatView != null){
            windowManager.removeView(floatView);
            floatView = null;
        }
    }

    public void destroyFullScreen(){
        if(fullView != null){
            windowManager.removeView(fullView);
            fullView = null;
        }
    }

}
