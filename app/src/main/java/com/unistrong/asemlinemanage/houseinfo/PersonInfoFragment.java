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
import com.unistrong.asemlinemanage.databinding.FragmentPersonInfoBinding;
import com.unistrong.baselibs.ui.spanner.ItemTextView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.resp.PersonInfoResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.List;

/**
 * 人员信息
 */
public class PersonInfoFragment extends Fragment {

    public static final String TAG = "PersonInfoFragment";
    private FragmentPersonInfoBinding binding;
    private ItemTextView hostNameView;
    private ItemTextView hjDetailView;
    private ItemTextView hkssxqView;
    private ItemTextView birthdayView;
    private ItemTextView ethnicView;
    private ItemTextView maleView;
    private ItemTextView hostTeleView;
    private HouseInfoActivity activity;
    private HouseInfoPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HouseInfoActivity) context;
        initRequest();
    }

    private void setViewsData(List<PersonInfoResp.ResultBean> result) {
        binding.llScContainer.removeAllViews();
        int index = 0;
        for (PersonInfoResp.ResultBean bean : result) {
            if (index++ != 0) addLine();//给非首项添加分隔线
            addItems(bean);
        }
    }

    private void addLine() {
        View horLine = View.inflate(getContext(), R.layout.common_1px_hor_line, null);
        binding.llScContainer.addView(horLine);
    }

    private void addItems(PersonInfoResp.ResultBean resultBean) {
        hostNameView = new ItemTextView("人员姓名", "", binding.llScContainer);
        hostTeleView = new ItemTextView("联系电话", "", binding.llScContainer);
        maleView = new ItemTextView("性别", "", binding.llScContainer);
        ethnicView = new ItemTextView("民族", "", binding.llScContainer);
        birthdayView = new ItemTextView("出生日期", "", binding.llScContainer);
        hkssxqView = new ItemTextView("户口地所属辖区", "", binding.llScContainer);
        hjDetailView = new ItemTextView("户籍详细地址", "", binding.llScContainer);
//        houseCateView = new ItemTextView("房屋类别", "", binding.llScContainer);
//        personCateView = new ItemTextView("人员类型", "", binding.llScContainer);
        setViewRightText(hostNameView, resultBean.getName());
        setViewRightText(hostTeleView, resultBean.getTelephone());
        setViewRightText(maleView, resultBean.getSex());
        setViewRightText(ethnicView, resultBean.getNation());
        setViewRightText(birthdayView, resultBean.getBirth());
        setViewRightText(hkssxqView, resultBean.getLivePoliceStation());
        setViewRightText(hjDetailView, resultBean.getLiveAddress());
    }

    private void setViewRightText(ItemTextView parent, String text) {
        text = TextUtils.isEmpty(text) ? "-" : text;
        parent.getRightView().setText(text);
    }

    private void initRequest() {
        activity.createLoadingDialog();
        presenter = new HouseInfoPresenter();
        presenter.requestPersonInfo(activity.taskInfo.getHouseId(), new ResponseBody<PersonInfoResp>(PersonInfoResp.class) {
            @Override
            public void onFailure(String message) {
                activity.closeLoadingDialog();
                IToast.toast(message);
            }

            @Override
            public void onSuccess(PersonInfoResp resp) {
                activity.closeLoadingDialog();
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                if (resp.getResult().isEmpty()) {
                    IToast.toast("该房间无人员信息!");
                    return;
                }
                setViewsData(resp.getResult());
            }
        });
    }
}
