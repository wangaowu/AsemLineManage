package com.unistrong.asemlinemanage.houseinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.unistrong.asemlinemanage.databinding.ActivityHouseInfoBinding;
import com.unistrong.baselibs.ui.UnderLineTextView;

/**
 * 房屋信息viewModel
 */
public class HouseInfoViewModel {

    private ActivityHouseInfoBinding binding;
    private static final int UNSELECT_COLOR = 0xFF3E3E3E;
    private static final int SELECT_COLOR = 0xFF08A7D5;

    public HouseInfoViewModel(ActivityHouseInfoBinding binding) {
        this.binding = binding;
    }

    public void setActivityStyle(String title, int statusColor) {
        binding.layoutTitle.tvTitle.setText(title);
        ViewGroup parent = (ViewGroup) binding.layoutTitle.tvTitle.getParent();
        parent.setBackgroundColor(statusColor);
    }

    public void clearBlue() {
        int count = binding.llCheckParent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = binding.llCheckParent.getChildAt(i);
            makeBlue(child, false);
        }
    }

    public void makeBlue(View view, boolean isBlue) {
        UnderLineTextView tv = (UnderLineTextView) view;
        tv.setTextColor(isBlue ? SELECT_COLOR : UNSELECT_COLOR);
        tv.setEnableUnderLine(isBlue);
    }

    private void hideFragment(FragmentManager fragmentManager) {
        if (0 == fragmentManager.getFragments().size()) return;
        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentManager.beginTransaction().hide(fragment).commitNowAllowingStateLoss();
        }
    }

    private Fragment newFragment(String tag) {
        Bundle bundle = new Bundle();

        if (BasicInfoFragment.TAG.equals(tag)) {
            BasicInfoFragment basicInfoFragment = new BasicInfoFragment();
            basicInfoFragment.setArguments(bundle);
            return basicInfoFragment;
        }
        if (PersonInfoFragment.TAG.equals(tag)) {
            PersonInfoFragment personInfoFragment = new PersonInfoFragment();
            personInfoFragment.setArguments(bundle);
            return personInfoFragment;
        }
        HouseImageFragment houseImageFragment = new HouseImageFragment();
        houseImageFragment.setArguments(bundle);
        return houseImageFragment;
    }

    private Fragment getFragment(String tag, FragmentManager fragmentManager) {
        Fragment existFragment = fragmentManager.findFragmentByTag(tag);
        if (existFragment == null) {
            existFragment = newFragment(tag);
            fragmentManager.beginTransaction()
                    .add(binding.flContainer.getId(), existFragment, tag)
                    .commitNowAllowingStateLoss();
        }
        return existFragment;
    }

    public void switchTo(String tag, FragmentManager fragmentManager) {
        hideFragment(fragmentManager);
        Fragment fragment = getFragment(tag, fragmentManager);
        fragmentManager.beginTransaction().show(fragment).commitNowAllowingStateLoss();
    }

    public void refreshHouseImageFragment(FragmentManager fragmentManager) {
        HouseImageFragment fragment = (HouseImageFragment) getFragment(HouseImageFragment.TAG, fragmentManager);
        fragment.initRequest();
    }
}
