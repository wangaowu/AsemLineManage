package com.unistrong.asemlinemanage.login;

import android.text.InputFilter;
import android.view.View;

import com.unistrong.asemlinemanage.databinding.ActivityLoginBinding;


/**
 * 登陆页
 */
public class LoginViewModel {

    private String loginTitle;
    private String userName;
    private String password;
    private ActivityLoginBinding binding;

    private static final int PASSWORD_LENGTH = 11;

    public LoginViewModel(ActivityLoginBinding binding) {
        this.binding = binding;
    }


    public String getLoginTitle() {
        return loginTitle;
    }

    public void setLoginTitle(String loginTitle) {
        this.loginTitle = loginTitle;
        binding.tvLoginTitle.setText(loginTitle);
    }

    /**
     * 根据title展示界面style
     */
    public void setActivityStyles(String extraTitle) {
        setLoginTitle(extraTitle);
        binding.tvForgetPassword.setVisibility(View.GONE);
        binding.etPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(PASSWORD_LENGTH)});
    }

    public void setUserName(String userName) {
        binding.etUserName.setText(this.userName = userName);
    }

    public void setPassword(String password) {
        binding.etPassword.setText(this.password = password);
    }

    public String getUserName() {
        return userName = binding.etUserName.getText().toString();
    }

    public String getPassword() {
        return password = binding.etPassword.getText().toString();
    }

    public void clearAccount() {
        clearPassword();
        binding.etUserName.setText("");
    }

    public void clearPassword() {
        binding.etPassword.setText("");
    }
}
