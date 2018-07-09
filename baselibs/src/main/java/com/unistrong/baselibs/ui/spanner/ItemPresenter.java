package com.unistrong.baselibs.ui.spanner;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;


/**
 * 条目实现(左边flag，右边editText)
 */
public class ItemPresenter {

    private final TextView tvFlag;
    private final EditText etContent;

    public ItemPresenter(String left, String rightHint, ViewGroup container) {
        Context ctx = container.getContext();
        int height = DensityUtils.dp2px(ctx, 60);

        View item = View.inflate(ctx, R.layout.common_edit_item_with_flag_layout, null);
        container.addView(item, new LinearLayout.LayoutParams(-1, height));

        tvFlag = item.findViewById(R.id.tv_flag);
        etContent = item.findViewById(R.id.et_content);

        tvFlag.setText(left);
        etContent.setText(rightHint);
    }

    public TextView getFlagView() {
        return tvFlag;
    }

    public EditText getContentView() {
        return etContent;
    }

    /**
     * null / string
     *
     * @return
     */
    public String getContent() {
        String content = etContent.getText().toString();
        return TextUtils.isEmpty(content) ? null : content;
    }

}
