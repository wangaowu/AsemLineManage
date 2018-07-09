package com.unistrong.asemlinemanage.settings;

import android.view.Gravity;
import android.widget.TextView;

import com.unistrong.baselibs.style.BaseActivity;

/**
 * 我的界面
 */
public class MySettingsActivity extends BaseActivity {

    @Override
    protected void initMvp() {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText("我的");
        setContentView(textView);
    }
}
