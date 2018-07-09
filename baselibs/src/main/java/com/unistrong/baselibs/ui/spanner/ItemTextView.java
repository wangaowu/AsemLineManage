package com.unistrong.baselibs.ui.spanner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;

/**
 * 右边展示栏样式
 */
public class ItemTextView extends BaseItemView {

    private final Context context;

    private final View itemView;
    private final TextView tvLeft;
    private final TextView tvRight;

    public ItemTextView(String left, String right, ViewGroup container) {
        context = container.getContext();
        itemView = View.inflate(context, R.layout.common_text_item_with_flag_layout, null);

        int height = DensityUtils.dp2px(context, ITEM_HEIGHT);
        container.addView(itemView, new LinearLayout.LayoutParams(-1, height));

        tvLeft = itemView.findViewById(R.id.tv_flag);
        tvRight = itemView.findViewById(R.id.tv_content);

        tvLeft.setText(left);
        tvRight.setHint(right);
        tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
        return tvRight.getText().toString();
    }

    @Override
    public void setLeftContent(int id) {
        tvLeft.setText(id);
    }

    @Override
    public void initRight() {

    }
}
