package com.unistrong.asemlinemanage.recordinfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import com.picture.lib.PictureSelector;
import com.picture.lib.config.PictureMimeType;
import com.picture.lib.entity.LocalMedia;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityRecordHouseInfoBinding;
import com.unistrong.asemlinemanage.houseinfo.HouseInfoActivity;
import com.unistrong.asemlinemanage.mytask.MyTaskActivity;
import com.unistrong.baselibs.style.Activity_;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.BitmapUtils;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.common.ItemCheckBoxView;
import com.unistrong.framwork.common.ItemImageView;
import com.unistrong.framwork.common.WindowInfoState;
import com.unistrong.framwork.req.WindowInfoReq;
import com.unistrong.framwork.resp.RemoteImageResp;
import com.unistrong.framwork.resp.TaskListResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.DynamicDictUtils;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.framwork.utils.UploadService;
import com.unistrong.framwork.utils.VerifyGlide;
import com.unistrong.requestlibs.gson.GsonProvider;
import com.unistrong.requestlibs.inter.IRequest;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 采集房屋走访信息界面
 */
public class RecordHouseInfoActivity extends BaseActivity {
    public static final String TAG = "RecordHouseInfoActivity";
    private static final int CODE_PICK_IMAGE = 2;

    private ActivityRecordHouseInfoBinding binding;
    private ItemCheckBoxView constructorChangeView;
    private ItemCheckBoxView isNotifyCloseView;
    private ItemCheckBoxView rentPersonChangeView;
    private ItemCheckBoxView exceptionView;
    private List<WindowInfoReq.AddVisitDetailsBean> pickImageInfos = new ArrayList<>();
    private String constuctorChangeImagePath;
    private TaskListResp.ResultBean taskBean = new TaskListResp.ResultBean();
    private WindowInfoReq reqInfo = new WindowInfoReq();

    @Override
    protected void initMvp() {
        initIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_record_house_info);
        binding.layoutTitle.tvTitle.setText("走访录入");
        binding.blueTitle.title.setText("请上传窗户对外照片");
        initViews();
    }

    private void initIntent() {
        taskBean = ((TaskListResp.ResultBean) getIntent().
                getSerializableExtra(HouseInfoActivity.TASK_INFO));
    }

    private void initViews() {
        constructorChangeView = new ItemCheckBoxView("房屋结构是否改变?",
                ItemCheckBoxView.VERTICAL, binding.llCbContainer1);
        isNotifyCloseView = new ItemCheckBoxView("是否告知住户靠窗窗户不能打开?",
                ItemCheckBoxView.VERTICAL, binding.llCbContainer2);
        rentPersonChangeView = new ItemCheckBoxView("房屋承租人是否变更?",
                ItemCheckBoxView.VERTICAL, binding.llCbContainer2);
        exceptionView = new ItemCheckBoxView("走访是否存在异常现象?",
                ItemCheckBoxView.VERTICAL, binding.llCbContainer2);
        //房屋结构是否改变
        constructorChangeView.setListener((buttonView, isChecked) -> {
            if (isChecked) {
                IToast.toast("请上传改变后的照片！");
                PictureSelector.use(this).openGallery(PictureMimeType.TYPE_IMAGE()).maxSelectNum(1).forResult(CODE_PICK_IMAGE);
            } else {
                setGlobalConstructorChangeImage(null);
                reqInfo.setStructChangePicurl(null);
            }
        });
        //异常信息备注
        exceptionView.setListener((buttonView, isChecked) -> {
            View parent = (View) binding.etSpecialInfo.getParent();
            if (isChecked) {
                IToast.toast("请填写异常信息！");
                parent.setVisibility(View.VISIBLE);
            } else {
                parent.setVisibility(View.GONE);
            }
        });
        //承租人变更
        rentPersonChangeView.setListener((buttonView, isChecked) -> {
            if (isChecked) IToast.toast("请去警信通登记变更信息！");
        });
        //窗户不能打开
        isNotifyCloseView.setListener((buttonView, isChecked) -> {
            if (!isChecked) IToast.toast("不能关闭窗户！");
        });
    }

    private void setGlobalConstructorChangeImage(String imagePath) {
        this.constuctorChangeImagePath = imagePath;
        showConstructorChangeLayout(imagePath != null);
        if (null != imagePath) {
            VerifyGlide.getInstance().load(imagePath, binding.ivConstructorChange);
        }
    }

    private void showConstructorChangeLayout(boolean isShow) {
        View parent = (View) binding.ivConstructorChange.getParent();
        parent.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    //添加照片
    public void clickAddImage(View view) {
        startActivity(new Intent(this, AddImageActivity.class));
    }

    public void clickSaveWindowInfo(View view) {
        if (!isOk()) {
            IToast.toast("请检查走访信息!");
            return;
        }
        createLoadingDialog(false);
        Executors.newFixedThreadPool(1).execute(() -> {
            //1.上传窗户照片
            for (int i = 0; i < pickImageInfos.size(); i++) {
                WindowInfoReq.AddVisitDetailsBean pickInfoBean = pickImageInfos.get(i);
                if (!TextUtils.isEmpty(pickInfoBean.getWindowPicurl())) continue;//已经上传成功
                //上传
                String windowPicurl = uploadImage(pickInfoBean.getWindowImagePath());
                if (TextUtils.isEmpty(windowPicurl)) {//失败
                    runOnUiThread(() -> {
                        closeLoadingDialog();
                        IToast.toast("因照片上传错误,提交失败!");
                    });
                    return;
                }
                pickInfoBean.setWindowPicurl(windowPicurl);//成功
            }
            //2.上传异常照片
            if (constructorChangeView.isCheckedPositive() && !TextUtils.isEmpty(constuctorChangeImagePath)) {
                if (TextUtils.isEmpty(reqInfo.getStructChangePicurl())) { //是空,需要上传
                    String serverPath = uploadImage(constuctorChangeImagePath);
                    if (TextUtils.isEmpty(serverPath)) {//失败
                        runOnUiThread(() -> {
                            closeLoadingDialog();
                            IToast.toast("因照片上传错误,提交失败!");
                        });
                        return;
                    }
                    reqInfo.setStructChangePicurl(serverPath);//成功
                }
            }
            //上传照片全部成功
            runOnUiThread(() -> commitHouseInfo());
        });
    }

    private boolean isOk() {
        if (taskBean == null || pickImageInfos == null || pickImageInfos.isEmpty()) {
            return false;
        }
        if (constructorChangeView.isChecked()
                && isNotifyCloseView.isChecked()
                && rentPersonChangeView.isChecked()
                && exceptionView.isChecked()) {
            return true;
        }
        return false;
    }

    /**
     * 转换路径
     *
     * @param filePath
     * @return
     */
    private String uploadImage(String filePath) {
        String REGEX = "http://192.168.116.90:8028";
        try {
            UploadService uploadService = new UploadService();
            byte[] sampleBytes = new BitmapUtils().getSampleBytes(filePath);
            String json = uploadService.uploadSingleBitmap(Constant.UPLOAD_URL, sampleBytes);
            RemoteImageResp remoteImageResp = GsonProvider.instance().fromJson(json, RemoteImageResp.class);
            return remoteImageResp.getData().getImages().get(0).getOUrl().replaceAll(REGEX, Constant.PICTURE_HOST);
//        return  remoteImageResp.getData().getImages().get(0).getTUrl().replaceAll(REGEX, HOST);//缩略图
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提交请求
     */
    private void commitHouseInfo() {
        reqInfo.setHouseId(taskBean.getHouseId());//主键id
        reqInfo.setTaskId(taskBean.getTaskId());//任务id
        reqInfo.setSubtaskId(taskBean.getSubtaskId());//子任务id
        reqInfo.setAddVisitDetails(pickImageInfos);//窗户信息
        reqInfo.setStructChange(constructorChangeView.isCheckedPositive() ? "Y" : "N");//结构改变
        reqInfo.setNotify(isNotifyCloseView.isCheckedPositive() ? "Y" : "N");//告知关闭
        reqInfo.setRenterChange(rentPersonChangeView.isCheckedPositive() ? "Y" : "N");//承租人改变
        reqInfo.setAbnomal(exceptionView.isCheckedPositive() ? "Y" : "N");//异常改变
        String constructContent = binding.etConstructInfo.getText().toString();
        if (constructorChangeView.isCheckedPositive() && TextUtils.isEmpty(constructContent)) {
            //选中结构异常，但没有备注信息
            closeLoadingDialog();
            IToast.toast("请备注结构改变!");
            return;
        }
        if (!isNotifyCloseView.isCheckedPositive()) {
            IToast.toast("请关闭窗户!");
            return;
        }
        reqInfo.setStructChangeReason(constructContent);
        String exceptionContent = binding.etSpecialInfo.getText().toString();
        reqInfo.setAbnomalReason(exceptionContent);
        if (exceptionView.isCheckedPositive() && TextUtils.isEmpty(exceptionContent)) {
            //选中了异常yes,但不填入信息
            closeLoadingDialog();
            IToast.toast("请备注走访异常!");
            return;
        }
        String action = Constant.Action.POST_WINDOW_INFO;
        int method = IRequest.Method.POST_AS_JSON;
        int timeout = 30 * 1000;
        long tag = System.currentTimeMillis();
        HttpRequestImpl.getInstance().request(action, method,
                reqInfo,//请求参数
                timeout, tag, new ResponseBody<WindowInfoState>(WindowInfoState.class) {
                    @Override
                    public void onFailure(String message) {
                        closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(WindowInfoState state) {
                        closeLoadingDialog();
                        if (isFailure(state.getCode())) {
                            IToast.toast(state.getMsg());
                            return;
                        }
                        IToast.toast("提交成功！");
                        notifyHouseInfoActivityChanged();
                        notifyMyTaskListChanged();
                        finish();
                    }
                });
    }

    //通知房屋信息页面更新
    private void notifyHouseInfoActivityChanged() {
        Activity_ activity_ = getActivityByTag(HouseInfoActivity.TAG);
        if (activity_ instanceof HouseInfoActivity) {
            ((HouseInfoActivity) activity_).refreshHouseImageFragment();
        }
    }

    //通知列表页面更新
    private void notifyMyTaskListChanged() {
        Activity_ activity_ = getActivityByTag(MyTaskActivity.TAG);
        if (activity_ instanceof MyTaskActivity) {
            ((MyTaskActivity) activity_).refreshUndoList();
        }
    }

    public void setPickImageInfos(List<WindowInfoReq.AddVisitDetailsBean> pickImageInfos) {
        this.pickImageInfos.addAll(pickImageInfos);
        if (!this.pickImageInfos.isEmpty()) {
            refreshLayout();
        }
    }

    private void refreshLayout() {
        //显隐布局
        binding.blueTitle.rightContinueAdd.setVisibility(View.VISIBLE);
        binding.blueTitle.rightContinueAdd.setOnClickListener(this::clickAddImage);
        binding.ivAddImage.setVisibility(View.GONE);
        binding.llImageContainer.removeAllViews();
        //添加item
        for (WindowInfoReq.AddVisitDetailsBean pickImageInfo : pickImageInfos) {
            String imagePath = pickImageInfo.getWindowImagePath();
            String windowType = DynamicDictUtils.getInstance().getValue(pickImageInfo.getWindowType(),
                    DynamicDictUtils.houseCateList);
            String windowDirection = DynamicDictUtils.getInstance().getValue(pickImageInfo.getWindowDirection(),
                    DynamicDictUtils.windowDirectionList);
            String houseCateAndWindowInfo = windowType + "  窗户朝向" + windowDirection;
            String ps = pickImageInfo.getWindowDesc();
            new ItemImageView(imagePath, houseCateAndWindowInfo, ps, binding.llImageContainer, false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_PICK_IMAGE) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(resultCode, data);
            if (!localMedia.isEmpty()) {
                setGlobalConstructorChangeImage(localMedia.get(0).getPath());
            } else {
                constructorChangeView.selectNo();
            }
        }
    }
}
