package com.unistrong.asemlinemanage.houseinfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityHouseInfoBinding;
import com.unistrong.asemlinemanage.mytask.AlreadyDoingFragment;
import com.unistrong.asemlinemanage.mytask.UndoingFragment;
import com.unistrong.asemlinemanage.recordinfo.RecordHouseInfoActivity;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.framwork.resp.TaskListResp;

/**
 * 房屋信息
 */
public class HouseInfoActivity extends BaseActivity {
    public static final String TAG = "HouseInfoActivity";
    public static final String TASK_INFO = "taskInfo";
    public static final String TASK_STATUS = "status";

    private FragmentManager fragmentManager;
    private ActivityHouseInfoBinding binding;
    private HouseInfoViewModel viewModel;
    private HouseInfoPresenter presenter;
    public TaskListResp.ResultBean taskInfo;
    private String status;

    @Override
    protected int getStatusBarColor() {
        return STATUS_BLUE;
    }

    @Override
    protected void initMvp() {
        initIntent();
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_house_info);
        viewModel = new HouseInfoViewModel(binding);
        presenter = new HouseInfoPresenter();

        viewModel.setActivityStyle("走访信息", STATUS_BLUE);
        showRecordBtnVisible(UndoingFragment.STATUS.equals(status));
        showHouseImageLayoutVisible(AlreadyDoingFragment.STATUS.equals(status));
        showFragmentAt(binding.tvBasicInfo);
    }

    private void initIntent() {
        taskInfo = (TaskListResp.ResultBean) getIntent().getSerializableExtra(TASK_INFO);
        status = getIntent().getStringExtra(TASK_STATUS);
    }

    private void showFragmentAt(View view) {
        clickHouseInfo(view);
    }

    public void clickHouseInfo(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(BasicInfoFragment.TAG, fragmentManager);
    }

    public void clickOwnerInfo(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(PersonInfoFragment.TAG, fragmentManager);
    }

    public void clickCompanyInfo(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(HouseImageFragment.TAG, fragmentManager);
    }

    public void clickRecordWindowInfo(View view) {
        Intent intent = new Intent(this, RecordHouseInfoActivity.class);
        intent.putExtra(TASK_INFO, taskInfo);
        startActivity(intent);
    }

    //刷新
    public void refreshHouseImageFragment() {
        showRecordBtnVisible(false);
        showHouseImageLayoutVisible(true);
        viewModel.refreshHouseImageFragment(fragmentManager);
    }

    //隐藏录入按钮
    private void showRecordBtnVisible(boolean visible) {
        View parent = (View) binding.tvManageHouseInfo.getParent();
        parent.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    //隐藏房屋照片布局
    private void showHouseImageLayoutVisible(boolean visible) {
        binding.tvHouseImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
