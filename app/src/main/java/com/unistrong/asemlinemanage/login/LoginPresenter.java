package com.unistrong.asemlinemanage.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.unistrong.asemlinemanage.databinding.ActivityLoginBinding;
import com.unistrong.asemlinemanage.index.MainActivity;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.DensityUtils;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class LoginPresenter {
    public static final String USER_FROM_LOCAL = "1";
    public static final String USER_FROM_JINGXINTONG = "3";

    private int INIT_OFFSET;
    private ActivityLoginBinding binding;

    public LoginPresenter(BaseActivity context, ActivityLoginBinding binding) {
        this.binding = binding;

        INIT_OFFSET = DensityUtils.dp2px(context, 200);
    }

    public String getSpUserName(Context ctx) {
        return SPUtils.getString(ctx, Constant.SP_KEY.USER_ACCOUNT);
    }

    public boolean isAvailed(String... ele1) {
        for (String ele : ele1) {
            if (TextUtils.isEmpty(ele) || "000".equals(ele))
                //检查表单项自动忽略edittext输入的000
                return false;
        }
        return true;
    }

    public boolean isOk(LoginViewModel viewModel) {
        String userName = viewModel.getUserName();
        String password = viewModel.getPassword();
        if (TextUtils.isEmpty(userName)) {
            IToast.toast("用户名为空!");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            IToast.toast("密码为空!");
            return false;
        }
        return true;
    }

    public void offsetLayout() {
        LinearLayout.LayoutParams layoutParams = null;
        layoutParams = (LinearLayout.LayoutParams) binding.llEditLay.getLayoutParams();
        layoutParams.topMargin = -INIT_OFFSET;
        binding.llEditLay.requestLayout();
    }

    public void resetLayout() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(INIT_OFFSET, 0);
        valueAnimator.setDuration(400);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            ((LinearLayout.LayoutParams) binding.llEditLay.getLayoutParams()).topMargin = -animatedValue;
            binding.llEditLay.requestLayout();
        });
        valueAnimator.start();
    }

    /**
     * 登陆
     */
    public void login(Context ctx, String account, String pwd, String userType, ResponseBody listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", pwd);
        params.put("userType", userType);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.LOGIN, params, listener);
    }

    public void startMainActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
