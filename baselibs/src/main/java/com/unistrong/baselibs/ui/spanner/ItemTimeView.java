package com.unistrong.baselibs.ui.spanner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unistrong.baselibs.R;
import com.unistrong.baselibs.utils.DensityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 右边选择时间样式
 */
public class ItemTimeView extends BaseItemView implements View.OnClickListener {
    private String PREFIX = "-";

    private final Activity activity;

    private final View itemView;
    private final TextView tvLeft;
    private final TextView tvRight;

//    private DateSelectDialog selTimeDialog;

    /**
     * @param left
     * @param timePrefix
     * @param container
     */
    public ItemTimeView(String left, String timePrefix, ViewGroup container) {
        this.PREFIX = timePrefix;
        this.activity = (Activity) container.getContext();
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
        return tvRight.getText().toString();
    }

    @Override
    public void setLeftContent(int id) {
        tvLeft.setText(id);
    }

    @Override
    public void initRight() {
        tvRight.setOnClickListener(this);
    }

    private String getCurrentYMD() {
        String format = new SimpleDateFormat("yyyy" + PREFIX + "MM" + PREFIX + "dd").format(new Date());
        return format;
    }

    @Override
    public void onClick(View v) {
        //点击右边
        showSelTimeDialog();
    }

    private void showSelTimeDialog() {
        String ymd = getCurrentYMD();

//        selTimeDialog = new DateSelectDialog(activity, R.style.Dialog_Fullscreen);
//        selTimeDialog.setOnFinishDateSelListener((y, m, d) -> {
//            tvRight.setText(y + PREFIX + m + PREFIX + d);
//            selTimeDialog.dismiss();
//        });
//        selTimeDialog.setOnClearDateSelListener(() -> {
//            tvRight.setText("");
//            selTimeDialog.dismiss();
//        });
//        String[] ymds = ymd.split(PREFIX);
////        selTimeDialog.setDatetime(Integer.parseInt(ymds[0]),
////                Integer.parseInt(ymds[1]),
////                Integer.parseInt(ymds[2]));
//        selTimeDialog.show();
    }
}
