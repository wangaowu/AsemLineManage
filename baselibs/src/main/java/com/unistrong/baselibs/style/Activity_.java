package com.unistrong.baselibs.style;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.AppUtils;

/**
 * activity的功能基类
 */
public class Activity_ extends AppCompatActivity {
    public String TAG = "Activity_";
    private static final String TAG_TITLE = "title";
    protected int STATUS_BLUE = 0xff08A7D5;
    protected int statusBarColor = getStatusBarColor();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        AppUtils.getInstance().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initStatusBarStyle();
    }

    /**
     * 沉浸状态栏
     */
    private void initStatusBarStyle() {
        //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        int statusBarOption = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(statusBarOption);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(statusBarColor);
        }
    }

    protected int getStatusBarColor() {
        return Color.TRANSPARENT;
    }

    public Activity_ getActivityByTag(String tag) {
        int stackCount = AppUtils.activityList.size();
        //倒叙查找，避免不同包下的相同类
        for (int i = stackCount - 1; i >= 0; i--) {
            Activity_ activity_ = AppUtils.activityList.get(i);
            if (tag.equals(activity_.getClass().getSimpleName())) {
                return activity_;
            }
        }
        return this;
    }

    protected void popOtherActivities() {
        popAllActivities(false);
    }

    protected void popAllActivities(boolean finishSelf) {
        AppUtils.getInstance().popAllActivities(finishSelf, TAG);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置commonTitle的状态栏间距
     */
    protected void setCommonTitleMarginTop(int statusBarHeight) {
        try {
            View backLayout = (View) findViewById(R.id.tv_left).getParent(); //返回按钮布局
            View titleLayout = (View) backLayout.getParent();//title布局
            if (TAG_TITLE.equals(titleLayout.getTag())) {
                //is title layout
                ViewGroup.LayoutParams layoutParams = titleLayout.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams p_ = (LinearLayout.LayoutParams) layoutParams;
                    p_.topMargin = statusBarHeight;
                    titleLayout.setLayoutParams(p_);
                }
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams p_ = (RelativeLayout.LayoutParams) layoutParams;
                    p_.topMargin = statusBarHeight;
                    titleLayout.setLayoutParams(p_);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        AppUtils.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
