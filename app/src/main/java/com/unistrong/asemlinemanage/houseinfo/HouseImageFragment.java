package com.unistrong.asemlinemanage.houseinfo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picture.DisplayImageActivity;
import com.picture.lib.config.PictureConfig;
import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.FragmentHouseImageBinding;
import com.unistrong.baselibs.utils.DensityUtils;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.common.ItemImageView;
import com.unistrong.framwork.common.WindowInfoResp;
import com.unistrong.framwork.resp.WindowImageResp;
import com.unistrong.framwork.utils.VerifyGlide;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.List;

/**
 * 房屋照片
 */
public class HouseImageFragment extends Fragment {
    public static final String TAG = "HouseImageFragment";

    private FragmentHouseImageBinding binding;
    private HouseInfoActivity activity;
    private HouseInfoPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_house_image, container, false);
        setDefaultItem();
        return binding.getRoot();
    }

    private void setDefaultItem() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1,
                DensityUtils.dp2px(getContext(), 80));
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.LTGRAY);
        textView.setGravity(Gravity.CENTER);
        textView.setText("暂无房屋照片信息");
        binding.llImageContainer.addView(textView, layoutParams);
    }

    //设置走访信息
    private void setInfoViewsData(WindowInfoResp.ResultBean resultBean) {
        //设置异常备注
        String specialInfo = resultBean.getAbnomalReason();
        String structChangePicurl = resultBean.getStructChangePicurl();
        binding.tvSpecialInfo.setText(TextUtils.isEmpty(specialInfo) ? "-" : specialInfo);
        if (!TextUtils.isEmpty(structChangePicurl)) {
            //异常照片
            ((View) binding.ivConstructorChange.getParent()).setVisibility(View.VISIBLE);
            VerifyGlide.getInstance().load(structChangePicurl, binding.ivConstructorChange);
            setConstructImageListener(binding.ivConstructorChange, structChangePicurl);
        }
    }

    //设置走访图片
    private void setImageViewsData(List<WindowImageResp.ResultBean> visitDetailList) {
        binding.llImageContainer.removeAllViews();
        for (WindowImageResp.ResultBean bean : visitDetailList) {
            String imagePath = bean.getWindowPicurl();
            String windowInfo = bean.getWindowType() + " 窗户朝向" + bean.getWindowDirection();
            String windowDesc = TextUtils.isEmpty(bean.getWindowDesc()) ? "-" : bean.getWindowDesc();
            new ItemImageView(imagePath, windowInfo, windowDesc, binding.llImageContainer)
                    .setOnItemClickListener((v) ->
                            presenter.startUpdateHouseInfoActivity(getContext(), bean));
        }
    }

    private void setConstructImageListener(ImageView imageView, String structChangePicurl) {
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DisplayImageActivity.class);
            intent.putExtra(PictureConfig.DIRECTORY_PATH, structChangePicurl);
            startActivity(intent);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HouseInfoActivity) context;
        initRequest();
    }

    public void initRequest() {
        activity.createLoadingDialog();
        presenter = new HouseInfoPresenter();
        requestWindowInfo();
        requestWindowImage();
    }

    private void requestWindowImage() {
        presenter.requestWindowImageInfo(activity.taskInfo.getVisiteId(),
                new ResponseBody<WindowImageResp>(WindowImageResp.class) {

                    @Override
                    public void onFailure(String message) {
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(WindowImageResp resp) {
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        setImageViewsData(resp.getResult());
                    }
                });
    }

    private void requestWindowInfo() {
        presenter.requestWindowInfo(activity.taskInfo.getHouseId(),
                activity.taskInfo.getSubtaskId(), activity.taskInfo.getHouseType(),
                new ResponseBody<WindowInfoResp>(WindowInfoResp.class) {
                    @Override
                    public void onFailure(String message) {
                        activity.closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(WindowInfoResp resp) {
                        activity.closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        if (!resp.getResult().isEmpty()) {
                            //android端和web业务不同，取最新走访记录,不涉及列表,服务器端按时间降序
                            WindowInfoResp.ResultBean latestRecord = resp.getResult().get(0);
                            setInfoViewsData(latestRecord);
                        }
                    }
                });
    }
}
