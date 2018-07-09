package com.unistrong.framwork.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ItemImageLayoutBinding;
import com.unistrong.framwork.utils.VerifyGlide;

/**
 * 照片展示布局
 */
public class ItemImageView {
    private Context context;
    private ItemImageLayoutBinding binding;
    private String imagePath;
    private String windowInfo;
    private String psInfo;
    private LinearLayout container;

    public ItemImageView(String imagePath, String houseCateAndWindowInfo, String psInfo, LinearLayout viewParent) {
        this(imagePath, houseCateAndWindowInfo, psInfo, viewParent, true);
    }

    public ItemImageView(String imagePath, String houseCateAndWindowInfo, String ps, LinearLayout viewParent, boolean showEdit) {
        this.imagePath = imagePath;
        this.windowInfo = houseCateAndWindowInfo;
        this.psInfo = ps;
        this.container = viewParent;
        context = container.getContext();
        initViews();
        setImageInfos();
        if (!showEdit) {
            binding.tvEdit.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.item_image_layout, container, true);
        binding.getRoot().setBackgroundResource(R.drawable.bg_btn_selector);
    }

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        binding.getRoot().setOnClickListener(clickListener);
    }

    public void setImageInfos() {
        setImagePath(imagePath);
        setWindowInfo(windowInfo);
        setPs(psInfo);
    }

    public void setImagePath(String imagePath) {
        if (!TextUtils.isEmpty(imagePath))
            VerifyGlide.getInstance().load(imagePath, binding.ivImage);
    }

    public void setWindowInfo(String windowInfo) {
        binding.tvWindowInfo.setText(windowInfo);
    }

    public void setPs(String psInfo) {
        psInfo = TextUtils.isEmpty(psInfo) ? "-" : psInfo;
        binding.tvPs.setText("备注: " + psInfo);
    }
}
