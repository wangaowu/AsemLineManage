package com.unistrong.asemlinemanage.login;

import android.text.TextUtils;
import android.view.View;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.resp.LoginResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.requestlibs.response.ResponseBody;

/**
 * 其他应用的闪屏界面
 */
public class SplashActivity extends BaseActivity {

    private String account;

    @Override
    protected void initMvp() {
        setContentView(new View(this));
        account = getIntent().getStringExtra("dlzh");
        if (TextUtils.isEmpty(account)) {
            IToast.toast("该账户不存在！");
            finish();
        }
    }

    @Override
    protected void onFirstResume() {
        super.onFirstResume();
        LoginPresenter loginPresenter = new LoginPresenter(this, null);

        createLoadingDialog();
        loginPresenter.login(this, account, null, LoginPresenter.USER_FROM_JINGXINTONG,
                new ResponseBody<LoginResp>(LoginResp.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                        finish();
                    }

                    @Override
                    public void onSuccess(LoginResp resp) {
                        closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            finish();
                            return;
                        }
                        SPUtils.putString(SplashActivity.this,
                                Constant.SP_KEY.USER_ID, String.valueOf(resp.getResult().getUserId()));
                        SPUtils.putString(SplashActivity.this,
                                Constant.SP_KEY.TOKEN, String.valueOf(resp.getResult().getToken()));
                        loginPresenter.startMainActivity(SplashActivity.this);
                        finish();
                    }
                });
    }
}
