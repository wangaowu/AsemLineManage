package com.unistrong.baselibs.ui.spanner;

import android.view.View;

public abstract class BaseItemView<T extends View> {

    public static final int ITEM_HEIGHT = 40;//dp

    public abstract View getLeftView();

    public abstract String getLeftText();

    public abstract T getRightView();

    public abstract String getRightText();

    public abstract String getRightKey();

    public abstract void setLeftContent(int id);

    protected abstract void initRight();

}
