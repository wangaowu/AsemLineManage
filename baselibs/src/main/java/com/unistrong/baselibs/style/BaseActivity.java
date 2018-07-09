package com.unistrong.baselibs.style;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.ui.BoxLoaderView;

/**
 * activity样式基类
 */
public abstract class BaseActivity extends Activity_ {

    private AlertDialog alertDialog;
    private BoxLoaderView loaderView;
    private boolean isFirstResume = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarColor = getResources().getColor(R.color.global_blue);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initMvp();
        setCommonTitleMarginTop(getStatusBarHeight());
    }

    protected abstract void initMvp();

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirstResume) {
            onFirstResume();
            isFirstResume = false;
        }
    }

    /**
     * 第一次可见(调用一次)
     */
    protected void onFirstResume() {
    }

    /**
     * 关闭加载框
     */
    public void closeLoadingDialog() {
        if (alertDialog != null) {
            loaderView.cancelAnimation();
            alertDialog.dismiss();
        }
    }

    /**
     * 创建加载框
     */
    public void createLoadingDialog() {
        createLoadingDialog(true);
    }

    public void createLoadingDialog(boolean cancelable) {
        if (alertDialog == null) {
            View inflate = View.inflate(this, R.layout.loading_dialog_layout, null);
            loaderView = inflate.findViewById(R.id.loader_view);

            AlertDialog.Builder builder = new AlertDialog.Builder(this,
                    R.style.translucent_dialog);
            alertDialog = builder.setView(inflate).create();
        }
        alertDialog.setCancelable(cancelable);
        alertDialog.show();
        loaderView.startAnimation();
    }

    public void clickBack(View view) {
        finish();
    }

    /**
     * 设置下拉刷新加载完成
     */
    public void setRefreshComplete(SwipeRefreshLayout refreshLayout) {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
