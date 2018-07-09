package com.unistrong.framwork.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ItemPickWindowPictureBinding;
import com.unistrong.baselibs.ui.spanner.ItemCheckView;

/**
 * 选择照片布局
 */
public class ItemPickImageLayout extends LinearLayout {

    private final Context context;
    private ItemPickWindowPictureBinding binding;
    private ItemCheckView checkHouseCateView;
    private ItemCheckView checkWindowHeadView;

    private String imagePath;

    public ItemCheckView getCheckHouseCateView() {
        return checkHouseCateView;
    }

    public ItemCheckView getCheckWindowHeadView() {
        return checkWindowHeadView;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        showImage(imagePath);
    }

    /**
     * @param viewParent 容器
     */
    public ItemPickImageLayout(LinearLayout viewParent) {
        super(viewParent.getContext());
        viewParent.addView(this);
        this.context = viewParent.getContext();
        initViews();
    }

    private void initViews() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_pick_window_picture, this, true);
        initCheckLayout();
    }

    private void initCheckLayout() {
        checkHouseCateView = new ItemCheckView("房间选择", binding.llCheck, "请选择房间");
        checkWindowHeadView = new ItemCheckView("朝向选择", binding.llCheck, "请选择朝向");
    }

    public void setShowElement(String[] houseCatElements, String[] headElements) {
        checkHouseCateView.setElements(houseCatElements);
        checkWindowHeadView.setElements(headElements);
    }

    public View getAddLayout() {
        return binding.llAdd;
    }

    public String getPs() {
        return binding.etSpecialInfo.getText().toString();
    }

    public void setPs(String ps) {
        ps = TextUtils.isEmpty(ps) ? "" : ps;
        binding.etSpecialInfo.setText(ps);
    }

    public ImageView getPickImageView() {
        return binding.ivPick;
    }

    private void showImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath))
            Glide.with(getContext()).load(imagePath).into(binding.ivPick);
    }

}
