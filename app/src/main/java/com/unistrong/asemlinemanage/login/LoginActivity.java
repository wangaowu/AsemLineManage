package com.unistrong.asemlinemanage.login;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityLoginBinding;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.resp.LoginResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.requestlibs.response.ResponseBody;


/**
 * 登陆页
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private ActivityLoginBinding binding;
    private LoginPresenter presenter;
    private LoginViewModel viewModel;

    @Override
    protected void initMvp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new LoginViewModel(binding);
        presenter = new LoginPresenter(this, binding);

        setUserName(presenter.getSpUserName(this));
        setActivityStyles("走访登陆");
        offsetLayout();
    }

    @Override
    protected void onFirstResume() {
        super.onFirstResume();
        resetLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setUserName(String spUserName) {
        viewModel.setUserName(spUserName);
    }

    @Override
    public void setActivityStyles(String title) {
        viewModel.setActivityStyles(title);
    }

    @Override
    public void offsetLayout() {
        presenter.offsetLayout();
    }

    @Override
    public void resetLayout() {
        presenter.resetLayout();
    }

    /**
     * 点击忘记密码
     */
    public void onForgetPasswordClick(View view) {
        IToast.toast("忘记密码请联系管理员进行初始化！");
    }


    public void onClearAccountClick(View view) {
        viewModel.clearAccount();
    }

    public void onClearPwdClick(View view) {
        viewModel.clearPassword();
    }

    /**
     * 点击登陆
     */
    public void onLoginClick(View view) {
        if (!presenter.isOk(viewModel)) {
            return;
        }
        String userName = viewModel.getUserName();
        String password = viewModel.getPassword();

        createLoadingDialog();
        presenter.login(this, userName, password, LoginPresenter.USER_FROM_LOCAL,
                new ResponseBody<LoginResp>(LoginResp.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(LoginResp resp) {
                        closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        saveSpUserAccount();
                        saveSpPassword();
                        saveSpToken(resp.getResult().getToken());
                        saveSpUserId(resp.getResult().getUserId());
                        presenter.startMainActivity(LoginActivity.this);
                        finish();
                    }
                });
    }

    private void saveSpUserId(int userId) {
        SPUtils.putString(this, Constant.SP_KEY.USER_ID, String.valueOf(userId));
    }

    private void saveSpToken(String token) {
        SPUtils.putString(this, Constant.SP_KEY.TOKEN, token);
    }

    private void saveSpUserAccount() {
        SPUtils.putString(this, Constant.SP_KEY.USER_ACCOUNT, viewModel.getUserName());
    }

    private void saveSpPassword() {
        SPUtils.putString(this, Constant.SP_KEY.USER_PWD, viewModel.getPassword());
    }
}

