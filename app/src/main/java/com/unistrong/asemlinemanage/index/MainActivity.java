package com.unistrong.asemlinemanage.index;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.unistrong.MipAddressUtils;
import com.unistrong.MipcaActivityCapture;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityMainBinding;
import com.unistrong.asemlinemanage.mytask.MyTaskActivity;
import com.unistrong.baidulocation.LocationService;
import com.unistrong.baidulocation.OnReceiveInfo;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.resp.TaskCountResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.DynamicDictUtils;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.framwork.utils.UpdateHelper;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int SCAN_CODE = 0;

    private ActivityMainBinding binding;
    private LocationService locationService;
    private long firstTime;

    @Override
    protected void initMvp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        DynamicDictUtils.getInstance().init();
        requestMyTask();
        startLocation();
        UpdateHelper.checkVersion(this);
    }

    private void startLocation() {
        locationService = LocationService.getInstance(getApplication());
        locationService.startLocation(800, new OnReceiveInfo() {
            @Override
            public void onReceive(String province, String city, String district) {
                setAddress(province, city, district);
                saveSp(Constant.SP_KEY.PROVINCE, province);
                saveSp(Constant.SP_KEY.CITY, city);
                saveSp(Constant.SP_KEY.DISTRICT, district);
            }

            @Override
            public void onReceive(double lat, double lng) {
                saveSp(Constant.SP_KEY.LAT, String.valueOf(lat));
                saveSp(Constant.SP_KEY.LNG, String.valueOf(lng));
            }

            @Override
            public void justOutCountry(boolean out) {

            }
        });
    }

    private void saveSp(String key, String value) {
        SPUtils.putString(this, key, value);
    }

    private void setAddress(String province, String city, String district) {
        if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(district))
            binding.tvLocation.setText(province + city + district);
    }

    /**
     * 设置数量
     *
     * @param list
     */
    private void setCount(List<TaskCountResp.ResultBean> list) {
        for (TaskCountResp.ResultBean bean : list) {
            String status = bean.getStatus();
            String num = String.valueOf(bean.getNum());
            if ("officialProceding".equals(status)) {
                //单位未完成
                binding.tvNotVisitCompanyCount.setText(num);
            } else if ("rentProceding".equals(status)) {
                //房屋未完成
                binding.tvNotVisitHouseCount.setText(num);
            } else if ("officialFinished".equals(status)) {
                //单位已完成
                binding.tvVisitedCompanyCount.setText(num);
            } else if ("rentFinished".equals(status)) {
                //房屋已完成
                binding.tvVisitedHouseCount.setText(num);
            }
        }
    }

    private void requestMyTask() {
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_TASK_COUNT
                , new HashMap<>(), new ResponseBody<TaskCountResp>(TaskCountResp.class) {
                    @Override
                    public void onFailure(String message) {
                        setRefreshComplete(binding.refreshLayout);
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(TaskCountResp resp) {
                        setRefreshComplete(binding.refreshLayout);
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        setCount(resp.getResult());
                    }
                });
    }

    private void initView() {
        binding.layoutTitle.ivLeft.setVisibility(View.INVISIBLE);
        binding.layoutTitle.tvTitle.setText("亚欧博览会管理系统");
        binding.layoutTitle.ivRight.setVisibility(View.VISIBLE);
        binding.layoutTitle.ivRight.setOnClickListener(v -> startMySettingsActivity());
        binding.refreshLayout.setOnRefreshListener(this);
    }

    private void startMySettingsActivity() {
        IToast.toast("该模块暂未开放!");
//        startActivity(new Intent(this, MySettingsActivity.class));
    }

    /**
     * 点击扫描二维码
     */
    public void scanAddressCode2(View view) {
        startScanCodeActivity();
    }

    private void startScanCodeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCAN_CODE);
    }

    /**
     * 打开我的任务列表界面
     *
     * @param houseId   扫描时用
     * @param houseType 房屋类型
     * @param showTag   展示标记
     */
    private void startMyTaskActivity(String houseId, String houseType, int showTag) {
        Intent intent = new Intent(this, MyTaskActivity.class);
        intent.putExtra(MyTaskActivity.HOUSE_ID, houseId);
        intent.putExtra(MyTaskActivity.FRAGMENT_KEY, showTag);
        intent.putExtra(MyTaskActivity.HOUSE_TYPE, houseType);
        startActivity(intent);
    }

    //点击走访房屋
    public void clickVisitHouse(View view) {
        startMyTaskActivity(null, Constant.Value.TYPE_HOUSE, MyTaskActivity.TAG_SHOW_UNDOING);
    }

    //点击走访单位
    public void clickVisitCompany(View view) {
        startMyTaskActivity(null, Constant.Value.TYPE_COMPANY, MyTaskActivity.TAG_SHOW_UNDOING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != SCAN_CODE) return;
        String houseId = MipAddressUtils.getHouseIdString(resultCode, data);
        Log.e(TAG, "HOUSE_ID: " + houseId);
        if (TextUtils.isEmpty(houseId)) return;
        startMyTaskActivity(houseId, Constant.Value.TYPE_HOUSE, MyTaskActivity.TAG_SHOW_UNDOING);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstTime > 2000) {
            //如果两次按键时间间隔大于2000毫秒，则不退出
            IToast.toast("再按一次退出程序...");
            firstTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        locationService.stopLocation();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        requestMyTask();
    }

}
