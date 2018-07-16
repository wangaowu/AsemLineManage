package com.unistrong.asemlinemanage.houseinfo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unistrong.asemlinemanage.R;
import com.unistrong.asemlinemanage.databinding.FragmentBasicInfoBinding;
import com.unistrong.baselibs.ui.spanner.ItemTextView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.resp.HouseInfoResp;
import com.unistrong.requestlibs.response.ResponseBody;

/**
 * 房屋信息
 */
public class BasicInfoFragment extends Fragment {

    public static final String TAG = "UndoingFragment";
    private HouseInfoActivity activity;
    private HouseInfoPresenter presenter;
    private FragmentBasicInfoBinding binding;
    private ItemTextView mpNumberView;
    private ItemTextView xqNameView;
    private ItemTextView dyhView;
    private ItemTextView floorView;
    private ItemTextView zlhzView;
    private ItemTextView zlNumberView;
    private ItemTextView jlxView;
    private ItemTextView sssqView;
    private ItemTextView ssjqView;
    private ItemTextView sqgbView;
    private ItemTextView sqgbTeleView;
    private ItemTextView detailAddressView;
    private ItemTextView houseCateView;
    private ItemTextView policeView;
    private ItemTextView policeTeleView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_info, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        xqNameView = new ItemTextView("小区名称", "", binding.llPart1);
        mpNumberView = new ItemTextView("门牌号", "", binding.llPart1);
        dyhView = new ItemTextView("单元号", "", binding.llPart1);
        floorView = new ItemTextView("楼层", "", binding.llPart1);
        zlhzView = new ItemTextView("幢楼后缀", "", binding.llPart1);
        zlNumberView = new ItemTextView("幢楼号", "", binding.llPart1);
        jlxView = new ItemTextView("街路巷", "", binding.llPart1);

        sssqView = new ItemTextView("所属社区", "", binding.llPart2);
        ssjqView = new ItemTextView("所属警区", "", binding.llPart2);
        sqgbView = new ItemTextView("社区干部", "", binding.llPart2);
        sqgbTeleView = new ItemTextView("社区干部联系电话", "", binding.llPart2);
        detailAddressView = new ItemTextView("详细地址", "", binding.llPart2);
        houseCateView = new ItemTextView("房屋类别", "", binding.llPart2);
        policeView = new ItemTextView("警务室民警", "", binding.llPart2);
        policeTeleView = new ItemTextView("民警电话", "", binding.llPart2);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HouseInfoActivity) context;
        initRequest();
    }

    private void setViewsData(HouseInfoResp.ResultBean resultBean) {
        setViewRightText(xqNameView, resultBean.getVillageName());
        setViewRightText(mpNumberView, resultBean.getRoomNo());
        setViewRightText(dyhView, resultBean.getUnitNo());
        setViewRightText(floorView, resultBean.getFloorNo());
        setViewRightText(zlhzView, resultBean.getBuildSuffixNo());
        setViewRightText(zlNumberView, resultBean.getBuildNo());
        setViewRightText(jlxView, resultBean.getStreetName());

        setViewRightText(sssqView, resultBean.getCommunityName());
        setViewRightText(ssjqView, resultBean.getPoliceName());
        setViewRightText(sqgbView, resultBean.getCommunityManager());
        setViewRightText(sqgbTeleView, resultBean.getCommunityManagerTel());
        setViewRightText(detailAddressView, resultBean.getHouseAddress());
        setViewRightText(houseCateView, resultBean.getHouseType());
        setViewRightText(policeView, resultBean.getPoliceManager());
        setViewRightText(policeTeleView, resultBean.getPoliceManagerTel());
    }

    private void setViewRightText(ItemTextView parent, String text) {
        text = TextUtils.isEmpty(text) ? "-" : text;
        parent.getRightView().setText(text);
    }

    public void initRequest() {
        activity.createLoadingDialog();
        presenter = new HouseInfoPresenter();
        presenter.requestBasicInfo(activity.taskInfo.getHouseId(), new ResponseBody<HouseInfoResp>(HouseInfoResp.class) {
            @Override
            public void onFailure(String message) {
                activity.closeLoadingDialog();
                IToast.toast(message);
            }

            @Override
            public void onSuccess(HouseInfoResp resp) {
                activity.closeLoadingDialog();
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                if (resp.getResult().isEmpty()) {
                    IToast.toast("该房间信息无记录!");
                    return;
                }
                setViewsData(resp.getResult().get(0));
            }
        });
    }

}
