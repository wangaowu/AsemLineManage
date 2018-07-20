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
import com.unistrong.framwork.resp.CompanyInfoResp;
import com.unistrong.framwork.resp.HouseInfoResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.requestlibs.response.ResponseBody;

/**
 * 房屋信息
 */
public class BasicInfoFragment extends Fragment {

    public static final String TAG = "UndoingFragment";
    private HouseInfoActivity activity;
    private HouseInfoPresenter presenter;
    private FragmentBasicInfoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HouseInfoActivity) context;
        initRequest();
    }

    public void initRequest() {
        if (Constant.Value.TYPE_HOUSE.equals(activity.taskInfo.getHouseType())) {
            //房屋
            requestHouseBasicInfo();
        } else {
            //单位
            requestCompanyBasicInfo();
        }
    }

    //设置房屋信息
    private void setHouseInfoViewsData(HouseInfoResp.ResultBean resultBean) {
        binding.llPart1.removeAllViews();
        binding.llPart2.removeAllViews();
        ItemTextView hostNameView = new ItemTextView("房主姓名", "", binding.llPart1);
        ItemTextView hostNoView = new ItemTextView("房主身份证", "", binding.llPart1);
        ItemTextView useCateView = new ItemTextView("房屋用途", "", binding.llPart1);
        ItemTextView housePropertyView = new ItemTextView("房屋性质", "", binding.llPart1);

        ItemTextView dyhView = new ItemTextView("房间数", "", binding.llPart2);
        ItemTextView xqNameView = new ItemTextView("小区名称", "", binding.llPart2);
        ItemTextView detailAddressView = new ItemTextView("详细地址", "", binding.llPart2);
        ItemTextView sssqView = new ItemTextView("所属社区", "", binding.llPart2);
        ItemTextView sqgbView = new ItemTextView("社区干部", "", binding.llPart2);
        ItemTextView sqgbTeleView = new ItemTextView("干部电话", "", binding.llPart2);
        ItemTextView ssjqView = new ItemTextView("所属警区", "", binding.llPart2);
        ItemTextView policeView = new ItemTextView("警务室民警", "", binding.llPart2);
        ItemTextView policeTeleView = new ItemTextView("民警电话", "", binding.llPart2);

        setViewRightText(hostNameView, resultBean.getHouseHolderName());
        setViewRightText(hostNoView, resultBean.getHouseHolderIdNum());
        setViewRightText(useCateView, resultBean.getHouseUse());
        setViewRightText(housePropertyView, resultBean.getHouseProperty());

        setViewRightText(dyhView, resultBean.getUnitNo());
        setViewRightText(xqNameView, resultBean.getVillageName());
        setViewRightText(sssqView, resultBean.getCommunityName());
        setViewRightText(ssjqView, resultBean.getPoliceName());
        setViewRightText(sqgbView, resultBean.getCommunityManager());
        setViewRightText(sqgbTeleView, resultBean.getCommunityManagerTel());
        setViewRightText(detailAddressView, resultBean.getHouseAddress());
        setViewRightText(policeView, resultBean.getPoliceManager());
        setViewRightText(policeTeleView, resultBean.getPoliceManagerTel());
    }

    //设置单位信息
    private void setCompanyInfoViewsData(CompanyInfoResp.ResultBean resultBean) {
        binding.llPart1.removeAllViews();
        binding.llPart2.removeAllViews();
        ItemTextView companyNameView = new ItemTextView("单位名称", "", binding.llPart1);
        ItemTextView juridicalNameView = new ItemTextView("法人代表", "", binding.llPart1);
        ItemTextView juridicalTeleView = new ItemTextView("法人电话", "", binding.llPart1);
        ItemTextView tradePropertyView = new ItemTextView("行业性质", "", binding.llPart1);
        ItemTextView economicPropertyView = new ItemTextView("经济性质", "", binding.llPart1);

        ItemTextView communityNameView = new ItemTextView("小区名称", "", binding.llPart2);
        ItemTextView houseCountView = new ItemTextView("房间数", "", binding.llPart2);
        ItemTextView detailAddressView = new ItemTextView("详细地址", "", binding.llPart2);
        ItemTextView sssqView = new ItemTextView("所属社区", "", binding.llPart2);
        ItemTextView sqgbView = new ItemTextView("社区干部", "", binding.llPart2);
        ItemTextView sqgbTeleView = new ItemTextView("干部电话", "", binding.llPart2);
        ItemTextView ssjqView = new ItemTextView("所属警区", "", binding.llPart2);
        ItemTextView policeView = new ItemTextView("警务室民警", "", binding.llPart2);
        ItemTextView policeTeleView = new ItemTextView("民警电话", "", binding.llPart2);

        setViewRightText(companyNameView, resultBean.getDeptName());
        setViewRightText(juridicalNameView, resultBean.getDeptLegalRepresntative());
        setViewRightText(juridicalTeleView, resultBean.getDeptTelephone());
        setViewRightText(tradePropertyView, resultBean.getDeptCategory());
        setViewRightText(economicPropertyView, resultBean.getDeptEconomicNature());

        setViewRightText(communityNameView, resultBean.getCommunityName());
        setViewRightText(houseCountView, resultBean.getHouseRoomCount());
        setViewRightText(detailAddressView, resultBean.getHouseAddress());
        setViewRightText(sssqView, resultBean.getCommunityName());
        setViewRightText(sqgbView, resultBean.getCommunityManager());
        setViewRightText(sqgbTeleView, resultBean.getCommunityManagerTel());
        setViewRightText(ssjqView, resultBean.getPoliceName());
        setViewRightText(policeView, resultBean.getPoliceManager());
        setViewRightText(policeTeleView, resultBean.getPoliceManagerTel());
    }

    private void setViewRightText(ItemTextView parent, String text) {
        text = TextUtils.isEmpty(text) ? "-" : text;
        parent.getRightView().setText(text);
    }

    private void requestCompanyBasicInfo() {
        activity.createLoadingDialog();
        presenter = new HouseInfoPresenter();
        presenter.requestCompanyBasicInfo(activity.taskInfo.getHouseId(),
                new ResponseBody<CompanyInfoResp>(CompanyInfoResp.class) {
                    @Override
                    public void onFailure(String message) {
                        activity.closeLoadingDialog();
                        IToast.toast(message);
                    }

                    @Override
                    public void onSuccess(CompanyInfoResp resp) {
                        activity.closeLoadingDialog();
                        if (isFailure(resp.getCode())) {
                            IToast.toast(resp.getMsg());
                            return;
                        }
                        setCompanyInfoViewsData(resp.getResult().get(0));
                    }
                });
    }

    private void requestHouseBasicInfo() {
        activity.createLoadingDialog();
        presenter = new HouseInfoPresenter();
        presenter.requestHouseBasicInfo(activity.taskInfo.getHouseId(), new ResponseBody<HouseInfoResp>(HouseInfoResp.class) {
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
                setHouseInfoViewsData(resp.getResult().get(0));
            }
        });
    }

}
