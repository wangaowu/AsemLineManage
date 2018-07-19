package com.unistrong.asemlinemanage.mytask;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityMyTaskBinding;
import com.unistrong.asemlinemanage.houseinfo.HouseInfoActivity;
import com.unistrong.asemlinemanage.houseinfo.HouseInfoPresenter;
import com.unistrong.asemlinemanage.recordinfo.RecordHouseInfoActivity;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.common.WindowInfoResp;
import com.unistrong.framwork.resp.TaskListResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.List;

/**
 * 我的任务列表界面
 */
public class MyTaskActivity extends BaseActivity {
    public static final String TAG = "MyTaskActivity";
    public static final String FRAGMENT_KEY = "showTag";
    public static final String HOUSE_TYPE = "houseType";
    public static final String HOUSE_ID = "houseId";
    public static final int TAG_SHOW_UNDOING = 1;
    public static final int TAG_SHOW_ALREADY_DOING = 2;

    private FragmentManager fragmentManager;
    private ActivityMyTaskBinding binding;
    private MyTaskViewModel viewModel;
    public String houseId;

    @Override
    protected int getStatusBarColor() {
        return STATUS_BLUE;
    }

    @Override
    protected void initMvp() {
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_task);
        viewModel = new MyTaskViewModel(binding);
        viewModel.setActivityStyle("我的任务列表", STATUS_BLUE);

        houseId = getIntent().getStringExtra(HOUSE_ID);
        makeBlue(getIntent().getStringExtra(HOUSE_TYPE));
        showFragmentAt(getIntent().getIntExtra(FRAGMENT_KEY, 0));
    }

    private void showFragmentAt(int showTag) {
        switch (showTag) {
            case TAG_SHOW_UNDOING:
                clickUndoing(binding.tvUndoing);
                break;
            case TAG_SHOW_ALREADY_DOING:
                clickAlreadyDoing(binding.tvAlreadyDoing);
                break;
        }
    }

    public void clickUndoing(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(UndoingFragment.TAG, fragmentManager);
    }

    public void clickAlreadyDoing(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(AlreadyDoingFragment.TAG, fragmentManager);
    }

    public void clickCateCompany(View view) {
        if (!Constant.Value.TYPE_COMPANY.equals(getSelectHouseType())) {
            makeBlue(Constant.Value.TYPE_COMPANY);
            refreshAlreadyList();
            refreshUndoList();
        }
    }

    public void clickCateHouse(View view) {
        if (!Constant.Value.TYPE_HOUSE.equals(getSelectHouseType())) {
            makeBlue(Constant.Value.TYPE_HOUSE);
            refreshAlreadyList();
            refreshUndoList();
        }
    }

    private void makeBlue(String type) {
        int whiteColor = Color.WHITE;
        int blueColor = getResources().getColor(R.color.global_blue);
        if (Constant.Value.TYPE_HOUSE.equals(type)) {
            //房屋
            binding.tvCateHouse.setTextColor(whiteColor);
            binding.tvCateCompany.setTextColor(blueColor);
            ((View) binding.tvCateHouse.getParent()).setBackgroundResource(R.drawable.bg_type_house);
            ((View) binding.tvCateHouse.getParent()).setTag(Constant.Value.TYPE_HOUSE);
        } else {
            //单位
            binding.tvCateHouse.setTextColor(blueColor);
            binding.tvCateCompany.setTextColor(whiteColor);
            ((View) binding.tvCateHouse.getParent()).setBackgroundResource(R.drawable.bg_type_company);
            ((View) binding.tvCateHouse.getParent()).setTag(Constant.Value.TYPE_COMPANY);
        }
    }

    public String getSelectHouseType() {
        return (String) ((View) binding.tvCateHouse.getParent()).getTag();
    }

    //请求走访信息接口
    public void requestVisitDetail(TaskListResp.ResultBean bean) {
        String houseId = bean.getHouseId();
        String subtaskId = bean.getSubtaskId();
        String houseType = bean.getHouseType();
        createLoadingDialog();
        new HouseInfoPresenter().requestWindowInfo(houseId, subtaskId, houseType,
                new ResponseBody<WindowInfoResp>(WindowInfoResp.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(WindowInfoResp resp) {
                        closeLoadingDialog();
                        if (isFailure(resp.getCode()) || resp.getResult() == null) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        showVisitListDialog(resp.getResult());
                    }
                });
    }

    //弹出历史窗口
    private void showVisitListDialog(List<WindowInfoResp.ResultBean> historyList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("走访历史");
        builder.setItems(buildStringArray(historyList), null);
        builder.create().show();
    }

    private String[] buildStringArray(List<WindowInfoResp.ResultBean> historyList) {
        String[] elements = new String[historyList.size()];
        for (int i = 0; i < historyList.size(); i++) {
            WindowInfoResp.ResultBean bean = historyList.get(i);
            elements[i] =
                    "走访人:" + getString(bean.getVisiteUser()) + "\n"
                            + "走访时间:" + getString(bean.getVisiteTime()) + "\n"
                            + "是否入户:" + getString(bean.getIsEnter()) + "\n"
                            + "是否异常:" + getString(bean.getAbnomal());
        }
        return elements;
    }

    private String getString(String src) {
        if (TextUtils.isEmpty(src)) return "未知";
        if ("Y".equalsIgnoreCase(src)) return "是";
        if ("N".equalsIgnoreCase(src)) return "否";
        return src;
    }

    /**
     * 打开房屋信息页面
     *
     * @param bean   条目信息
     * @param status
     */
    public void startHouseInfoActivity(TaskListResp.ResultBean bean, String status) {
        Intent intent = new Intent(this, HouseInfoActivity.class);
        intent.putExtra(HouseInfoActivity.TASK_INFO, bean);
        intent.putExtra(HouseInfoActivity.TASK_STATUS, status);
        startActivity(intent);
    }

    /**
     * 打开走访录入页面
     *
     * @param bean
     */
    public void startRecordHouseInfoActivity(TaskListResp.ResultBean bean) {
        Intent intent = new Intent(this, RecordHouseInfoActivity.class);
        intent.putExtra(HouseInfoActivity.TASK_INFO, bean);
        startActivity(intent);
    }

    public void refreshUndoList() {
        viewModel.refreshUndoFragment(fragmentManager);
    }

    public void refreshAlreadyList() {
        viewModel.refreshAlreadyFragment(fragmentManager);
    }
}
