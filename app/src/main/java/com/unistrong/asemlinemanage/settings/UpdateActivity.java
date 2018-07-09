package com.unistrong.asemlinemanage.settings;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import com.unistrong.asemlinemanage.R;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.ui.ProgressView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.utils.UpdateHelper;
import com.unistrong.requestlibs.request.MultiPartImpl;
import com.unistrong.requestlibs.response.ResponseBody;

/**
 * 更新界面
 */
public class UpdateActivity extends BaseActivity {
    public static String INTENT_KEY = "apkUrl";

    private ProgressView progressView;
    private String apkUrl;

    @Override
    protected void initMvp() {
        initIntent();
        setContentView(R.layout.activity_findnew);
        progressView = findViewById(R.id.progress_view);
    }

    private void initIntent() {
        apkUrl = getIntent().getStringExtra(INTENT_KEY);
    }

    //点击更新按钮
    public void onUpdateClick(View view) {
        if (null == UpdateHelper.getTempFile()) {
            //没有下载文件
            setUpdateEnable(false);
            downFile();
        } else {
            //已有下载文件
            progressView.setProgress(100);
            UpdateHelper.autoInstall(this);
        }
    }

    private void downFile() {
        MultiPartImpl.getInstance().downloadFile(apkUrl,
                UpdateHelper.APK_FILE_DIR, new Handler(),
                new ResponseBody(Object.class) {
                    @Override
                    public void onFailure(String message) {
                        setUpdateEnable(true);
                        IToast.toast(message);
                    }

                    @Override
                    public void onProgress(int progress) {
                        progressView.setProgress(progress);
                        if (progress == 100) setUpdateEnable(true);
                    }

                    @Override
                    public void onSuccess(Object json) {
                        //成功
                        setUpdateEnable(true);
                        UpdateHelper.autoInstall(UpdateActivity.this);
                    }
                });
    }

    private void setUpdateEnable(boolean enable) {
        findViewById(R.id.tv_update).setClickable(enable);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
