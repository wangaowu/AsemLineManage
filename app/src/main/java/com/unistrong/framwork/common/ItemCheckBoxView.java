package com.unistrong.framwork.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.ItemCheckboxSpannerBinding;

public class ItemCheckBoxView {
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    private final Context context;
    private String cbTitle;
    private int orientation;
    private LinearLayout viewParent;
    private ItemCheckboxSpannerBinding binding;

    /**
     * 带有选择项的布局
     *
     * @param cbTitle     标题
     * @param orientation 选择项与title的方向
     * @param viewParent  容器
     */
    public ItemCheckBoxView(String cbTitle, int orientation, LinearLayout viewParent) {
        this.cbTitle = cbTitle;
        this.orientation = orientation;
        this.viewParent = viewParent;
        this.context = viewParent.getContext();
        initViews();
    }

    private void initViews() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_checkbox_spanner, viewParent, true);
        binding.tvCbTitle.setText(cbTitle);
        requestOrientation();
    }

    private void requestOrientation() {
        switch (orientation) {
            case HORIZONTAL:
                binding.llRoot.setOrientation(LinearLayout.HORIZONTAL);
                break;
            case VERTICAL:
                binding.llRoot.setOrientation(LinearLayout.VERTICAL);
                break;
        }
    }

    /**
     * 是否选中了yes
     *
     * @return
     */
    public boolean isCheckedPositive() {
        return binding.rbYes.isChecked();
    }

    /**
     * 是否有选
     *
     * @return
     */
    public boolean isChecked() {
        return binding.rbYes.isChecked() || binding.rbNo.isChecked();
    }

    public void setListener(CompoundButton.OnCheckedChangeListener yesListener) {
        binding.rbYes.setOnCheckedChangeListener(yesListener);
    }

    public void selectNo() {
        binding.rbNo.setChecked(true);
    }
}
