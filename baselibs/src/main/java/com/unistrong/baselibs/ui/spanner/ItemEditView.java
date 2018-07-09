package com.unistrong.baselibs.ui.spanner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;

/**
 * 右边编辑框样式
 */
public class ItemEditView extends BaseItemView {

    private final Context context;

    private final View itemView;
    private final TextView tvLeft;
    private final EditText etRight;

    public ItemEditView(String left, String rightHint, ViewGroup container) {
        context = container.getContext();
        itemView = View.inflate(context, R.layout.common_edit_item_with_flag_layout, null);

        int height = DensityUtils.dp2px(context, ITEM_HEIGHT);
        container.addView(itemView, new LinearLayout.LayoutParams(-1, height));

        tvLeft = itemView.findViewById(R.id.tv_flag);
        etRight = itemView.findViewById(R.id.et_content);

        tvLeft.setText(left);
        etRight.setHint(rightHint);
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
    public EditText getRightView() {
        return etRight;
    }

    @Override
    public String getRightText() {
        return etRight.getText().toString();
    }

    @Override
    public String getRightKey() {
        return etRight.getText().toString();
    }

    @Override
    public void setLeftContent(int id) {
        tvLeft.setText(id);
    }

    @Override
    public void initRight() {

    }
}
