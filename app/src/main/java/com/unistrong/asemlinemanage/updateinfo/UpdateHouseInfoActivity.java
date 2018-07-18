package com.unistrong.asemlinemanage.updateinfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import com.picture.lib.PictureSelector;
import com.picture.lib.config.PictureMimeType;
import com.picture.lib.entity.LocalMedia;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityUpdateImageBinding;
import com.unistrong.asemlinemanage.houseinfo.HouseInfoActivity;
import com.unistrong.baselibs.style.Activity_;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.BitmapUtils;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.common.ItemPickImageLayout;
import com.unistrong.framwork.common.WindowInfoState;
import com.unistrong.framwork.req.WindowInfoReq;
import com.unistrong.framwork.resp.RemoteImageResp;
import com.unistrong.framwork.resp.WindowImageResp;
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
 * 修改走访信息界面
 */
public class UpdateHouseInfoActivity extends BaseActivity {
    private static final String PICK_INIT = "请选择";
    public static final String INTENT_KEY = "windowInfo";

    private int INDEX_PICK_ITEM_IMAGE = 1;

    private ActivityUpdateImageBinding binding;
    private String[] houseCateArray;
    private String[] windowDirectArray;
    private WindowImageResp.ResultBean oldBean;

    @Override
    protected void initMvp() {
        initDict();
        initIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_image);
        binding.layoutTitle.tvTitle.setText("修改走访信息");
        newNextPickLayout(0);
        initData();
    }

    private void initData() {
        if (oldBean != null) {
            //图片
            ItemPickImageLayout itemPickLayout = getItemPickLayout(0);
            VerifyGlide.getInstance().load(oldBean.getWindowPicurl(), itemPickLayout.getPickImageView());
            //房间类型
            itemPickLayout.getCheckHouseCateView().setRightText(oldBean.getWindowType());
            itemPickLayout.getCheckWindowHeadView().setRightText(oldBean.getWindowDirection());
            //备注信息
            itemPickLayout.setPs(oldBean.getWindowDesc());
        }
    }

    private void initIntent() {
        oldBean = (WindowImageResp.ResultBean) getIntent()
                .getSerializableExtra(INTENT_KEY);
    }

    private void initDict() {
        houseCateArray = DynamicDictUtils.getInstance().getValueArray(DynamicDictUtils.houseCateList);
        windowDirectArray = DynamicDictUtils.getInstance().getValueArray(DynamicDictUtils.windowDirectionList);
    }

    /**
     * 添加下一条选择布局
     */
    private void newNextPickLayout(int index) {
        ItemPickImageLayout itemPickLayout = new ItemPickImageLayout(binding.llScContainer);
        itemPickLayout.setShowElement(houseCateArray, windowDirectArray);
        hideSelfAddLayout(index);
        itemPickLayout.getPickImageView().setOnClickListener(v -> startPickImage(index));
    }

    private void hideSelfAddLayout(int itemIndex) {
        ItemPickImageLayout itemPickLayout = getItemPickLayout(itemIndex);
        itemPickLayout.getAddLayout().setVisibility(View.GONE);
    }

    private ItemPickImageLayout getItemPickLayout(int itemIndex) {
        return ((ItemPickImageLayout) binding.llScContainer.getChildAt(itemIndex));
    }

    /**
     * 当前布局是否完成
     */
    private boolean isCompleted(int itemIndex) {
        ItemPickImageLayout itemPickLayout = getItemPickLayout(itemIndex);
        String houseCateText = itemPickLayout.getCheckHouseCateView().getRightText();
        String headText = itemPickLayout.getCheckWindowHeadView().getRightText();
        if (isEmptySelect(houseCateText)) {
            IToast.toast("请选择房间类型!");
            return false;
        }
        if (isEmptySelect(headText)) {
            IToast.toast("请选择窗户朝向!");
            return false;
        }
        return true;
    }

    private boolean isEmptySelect(String src) {
        return TextUtils.isEmpty(src) || PICK_INIT.equals(src);
    }

    private WindowInfoReq.AddVisitDetailsBean composeViewData(int itemIndex) {
        boolean isLastElement = itemIndex == binding.llScContainer.getChildCount() - 1;
        boolean isSingle = binding.llScContainer.getChildCount() == 1;
        if (isSingle && !isCompleted(itemIndex)) {
            //仅第一项,且未完成
            return null;
        }
        if (!isSingle && isLastElement && !isCompleted(itemIndex)) {
            //末尾项
            return null;
        }
        if (isCompleted(itemIndex)) {
            ItemPickImageLayout itemPickLayout = getItemPickLayout(itemIndex);
            String houseCateText = itemPickLayout.getCheckHouseCateView().getRightText();
            String headText = itemPickLayout.getCheckWindowHeadView().getRightText();
            String imagePath = itemPickLayout.getImagePath();
            String ps = itemPickLayout.getPs();
            //映射字典
            String houseCateCode = DynamicDictUtils.getInstance().getKey(houseCateText, DynamicDictUtils.houseCateList);
            String headCode = DynamicDictUtils.getInstance().getKey(headText, DynamicDictUtils.windowDirectionList);

            WindowInfoReq.AddVisitDetailsBean bean =
                    new WindowInfoReq.AddVisitDetailsBean(imagePath, houseCateCode, headCode);
            bean.setVisiteDetailId(oldBean.getVisiteDetailId());
            bean.setVisiteId(oldBean.getVisiteId());
            bean.setWindowDesc(ps);
            bean.setWindowPicurl(oldBean.getWindowPicurl());
            return bean;
        }
        return null;
    }

    /**
     * 选择照片
     *
     * @param itemIndex
     */
    private void startPickImage(int itemIndex) {
        INDEX_PICK_ITEM_IMAGE = itemIndex;
        PictureSelector.use(this).openGallery(PictureMimeType.TYPE_IMAGE()).maxSelectNum(1).forResult(INDEX_PICK_ITEM_IMAGE);
    }

    //点击完成
    public void clickSaveWindowInfo(View view) {
        WindowInfoReq.AddVisitDetailsBean newBean = composeViewData(0);
        if (newBean == null) return;
        createLoadingDialog(false);
        String windowImagePath = newBean.getWindowImagePath();
        if (TextUtils.isEmpty(windowImagePath)) {
            //没有修改过照片
            saveChangeInfo(newBean);
            return;
        }
        //修改过照片
        Executors.newSingleThreadExecutor().execute(() -> {
            String serverUrl = uploadImage(windowImagePath);
            if (TextUtils.isEmpty(serverUrl)) {//失败
                runOnUiThread(() -> {
                    closeLoadingDialog();
                    IToast.toast("因照片上传错误,提交失败!");
                });
                return;
            }
            newBean.setWindowPicurl(serverUrl);
            runOnUiThread(() -> saveChangeInfo(newBean));
        });
    }

    private void saveChangeInfo(WindowInfoReq.AddVisitDetailsBean newBean) {
        ArrayList<WindowInfoReq.AddVisitDetailsBean> params = new ArrayList<>();
        params.add(newBean);
        String action = Constant.Action.UPDATE_WINDOW_INFO;
        int method = IRequest.Method.POST_AS_JSON;
        int timeout = 30 * 1000;
        long tag = System.currentTimeMillis();
        HttpRequestImpl.getInstance().request(action, method,
                params,//请求参数
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
                        IToast.toast("修改成功！");
                        notifyHouseInfoActivityChanged();
                        finish();
                    }
                });
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

    //通知房屋信息页面更新
    private void notifyHouseInfoActivityChanged() {
        Activity_ activity_ = getActivityByTag(HouseInfoActivity.TAG);
        if (activity_ instanceof HouseInfoActivity) {
            ((HouseInfoActivity) activity_).refreshHouseImageFragment();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_PICK_ITEM_IMAGE) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(resultCode, data);
            if (localMedia != null && !localMedia.isEmpty()) {
                getItemPickLayout(INDEX_PICK_ITEM_IMAGE).setImagePath(localMedia.get(0).getPath());
            }
        }
    }

}
