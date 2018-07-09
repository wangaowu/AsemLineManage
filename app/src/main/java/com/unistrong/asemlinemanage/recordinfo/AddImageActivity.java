package com.unistrong.asemlinemanage.recordinfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import com.picture.lib.PictureSelector;
import com.picture.lib.config.PictureMimeType;
import com.picture.lib.entity.LocalMedia;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ActivityAddImageBinding;
import com.unistrong.baselibs.style.Activity_;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.common.ItemPickImageLayout;
import com.unistrong.framwork.req.WindowInfoReq;
import com.unistrong.framwork.utils.DynamicDictUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加照片页面
 */
public class AddImageActivity extends BaseActivity {
    private static final String PICK_INIT = "请选择";
    private int INDEX_PICK_ITEM_IMAGE = 1;

    private ActivityAddImageBinding binding;
    private String[] houseCateArray;
    private String[] windowDirectArray;
    private int pickLayoutIndex = 0;

    @Override
    protected void initMvp() {
        initDict();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_image);
        binding.layoutTitle.tvTitle.setText("窗户信息");
        newNextPickLayout(pickLayoutIndex++);
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
        itemPickLayout.getPickImageView().setOnClickListener(v -> startPickImage(index));
        itemPickLayout.getAddLayout().setOnClickListener(v -> {
            if (isCompleted(index)) {
                //当前布局已完成
                hideSelfAddLayout(index);
                newNextPickLayout(pickLayoutIndex++);
            }
        });
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
        String imagePath = itemPickLayout.getImagePath();
        if (isEmptySelect(imagePath)) {
            IToast.toast("请上传图片!");
            return false;
        }
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

    private List<WindowInfoReq.AddVisitDetailsBean> composeDataList() {
        List<WindowInfoReq.AddVisitDetailsBean> pickImageInfos = new ArrayList<>();
        for (int i = 0; i < binding.llScContainer.getChildCount(); i++) {
            WindowInfoReq.AddVisitDetailsBean bean = composeViewData(i);
            if (bean == null) {
                return null;
            } else {
                pickImageInfos.add(bean);
            }
        }
        return pickImageInfos;
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
            bean.setWindowDesc(ps);
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
        List<WindowInfoReq.AddVisitDetailsBean> pickInfos = composeDataList();
        if (pickInfos == null || pickInfos.isEmpty()) {
            //当前只一个选择窗并且为空
            return;
        }
        notifyPreDataChanged(pickInfos);
    }

    //通知前一界面数据改变
    private void notifyPreDataChanged(List<WindowInfoReq.AddVisitDetailsBean> pickInfos) {
        Activity_ activity_ = getActivityByTag(RecordHouseInfoActivity.TAG);
        if (activity_ instanceof RecordHouseInfoActivity) {
            ((RecordHouseInfoActivity) activity_).setPickImageInfos(pickInfos);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_PICK_ITEM_IMAGE) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(resultCode, data);
            if (localMedia != null && !localMedia.isEmpty()) {
                getItemPickLayout(INDEX_PICK_ITEM_IMAGE).setImagePath( localMedia.get(0).getPath());
            }
        }
    }

}
