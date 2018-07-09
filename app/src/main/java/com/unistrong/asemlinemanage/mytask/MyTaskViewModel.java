package com.unistrong.asemlinemanage.mytask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.unistrong.asemlinemanage.databinding.ActivityMyTaskBinding;
import com.unistrong.asemlinemanage.houseinfo.HouseImageFragment;
import com.unistrong.baselibs.ui.UnderLineTextView;

/**
 * 我的任务viewModel
 */
public class MyTaskViewModel {

    private ActivityMyTaskBinding binding;
    private static final int UNSELECT_COLOR = 0xFF3E3E3E;
    private static final int SELECT_COLOR = 0xFF08A7D5;

    public MyTaskViewModel(ActivityMyTaskBinding binding) {
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

        if (UndoingFragment.TAG.equals(tag)) {
            UndoingFragment undoingFragment = new UndoingFragment();
            undoingFragment.setArguments(bundle);
            return undoingFragment;
        }
        if (AlreadyDoingFragment.TAG.equals(tag)) {
            AlreadyDoingFragment alreadyDoingFragment = new AlreadyDoingFragment();
            alreadyDoingFragment.setArguments(bundle);
            return alreadyDoingFragment;
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

    public void refreshUndoFragment(FragmentManager fragmentManager) {
        UndoingFragment fragment = (UndoingFragment) getFragment(UndoingFragment.TAG, fragmentManager);
        fragment.initRequest();
    }
}
