package com.unistrong.asemlinemanage.mytask;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityMyTaskBinding;
import com.unistrong.asemlinemanage.houseinfo.HouseInfoActivity;
import com.unistrong.asemlinemanage.recordinfo.RecordHouseInfoActivity;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.framwork.resp.TaskListResp;

/**
 * 我的任务列表界面
 */
public class MyTaskActivity extends BaseActivity {
    public static final String TAG = "MyTaskActivity";
    public static final String FRAGMENT_KEY = "showTag";
    public static final String HOUSE_ID = "houseId";
    public static final int TAG_SHOW_UNDOING = 1;
    public static final int TAG_SHOW_ALREADY_DOING = 2;

    private FragmentManager fragmentManager;
    private ActivityMyTaskBinding binding;
    private MyTaskViewModel viewModel;
    private MyTaskPresenter presenter;
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
        presenter = new MyTaskPresenter();
        viewModel.setActivityStyle("我的任务列表", STATUS_BLUE);

        houseId = getIntent().getStringExtra(HOUSE_ID);
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


}
