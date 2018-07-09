package com.unistrong.baselibs.ui.spanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.annotation.ArrayRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;


/**
 * 右边选择条目样式  该类中用dictModel==null区分是不是字典形式
 */
public class ItemCheckView extends BaseItemView implements View.OnClickListener {

    private final DictModel dictModel;
    private String[] elements;
    private final Activity activity;

    private final View itemView;
    private final TextView tvLeft;
    private final TextView tvRight;
    private String alertTitle = "请选择";

    private AlertDialog checkItemDialog;

    /**
     * @param left
     * @param container
     */
    public ItemCheckView(String left, ViewGroup container, String alertTitle) {
        this.activity = (Activity) container.getContext();
        this.dictModel = null;
        this.alertTitle = alertTitle;
        int height = DensityUtils.dp2px(activity, ITEM_HEIGHT);

        itemView = View.inflate(activity, R.layout.common_text_item_with_flag_layout, null);
        container.addView(itemView, new LinearLayout.LayoutParams(-1, height));

        tvLeft = itemView.findViewById(R.id.tv_flag);
        tvRight = itemView.findViewById(R.id.tv_content);

        tvLeft.setText(left);
        initRight();
    }

    /**
     * 设置数据 注:调用该方法针对2参构造对象
     *
     * @param elements
     */
    public void setElements(String[] elements) {
        this.elements = elements;
        tvRight.setHint("请选择");
    }

    public void setRightText(String text) {
        if ("请选择".equals(text)) {
            tvRight.setHint(text);
        } else {
            tvRight.setText(text);
        }
    }

    /**
     * @param left
     * @param dictResID
     * @param container
     */
    public ItemCheckView(String left, @ArrayRes int dictResID, ViewGroup container) {
        this.activity = (Activity) container.getContext();
        this.dictModel = new DictModel(activity, dictResID);
        int height = DensityUtils.dp2px(activity, ITEM_HEIGHT);

        itemView = View.inflate(activity, R.layout.common_text_item_with_flag_layout, null);
        container.addView(itemView, new LinearLayout.LayoutParams(-1, height));

        tvLeft = itemView.findViewById(R.id.tv_flag);
        tvRight = itemView.findViewById(R.id.tv_content);

        tvLeft.setText(left);
        initRight();
    }

    @Override
    public View getLeftView() {
        return tvLeft;
    }

    @Override
    public String getLeftText() {
        return tvLeft.getText().toString();
    }

    @Override
    public TextView getRightView() {
        return tvRight;
    }

    @Override
    public String getRightText() {
        return tvRight.getText().toString();
    }

    @Override
    public String getRightKey() {
        if (dictModel == null) {
            return getRightText();
        }
        return dictModel.getAliasKey(getRightText());
    }

    @Override
    public void setLeftContent(int id) {
        tvLeft.setText(id);
    }

    @Override
    public void initRight() {
        tvRight.setOnClickListener(this);
        if (dictModel != null) {
            setRightText(dictModel.getInitValue());
        }
    }

    @Override
    public void onClick(View v) {
        //点击右边
        showSelItemDialog();
    }


    private void showSelItemDialog() {
        if (checkItemDialog != null) {
            checkItemDialog.show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(alertTitle);
        if (dictModel != null) {
            String[] valueArrays = dictModel.getValueArrays();
            builder.setItems(valueArrays, ((dialog, which) -> {
                tvRight.setText(valueArrays[which]);
                dialog.dismiss();
            }));
        } else {
            builder.setItems(elements, ((dialog, which) -> {
                tvRight.setText(elements[which]);
                dialog.dismiss();
            }));
        }
        checkItemDialog = builder.create();
        checkItemDialog.show();
    }
}
