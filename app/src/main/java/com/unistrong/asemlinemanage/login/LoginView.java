package com.unistrong.asemlinemanage.login;


public interface LoginView {
    /**
     * 偏移布局
     */
    void offsetLayout();

    /**
     * 重置布局
     */
    void resetLayout();

    /**
     * 設置sp存放的用戶名
     *
     * @param spUserName
     */
    void setUserName(String spUserName);

    /**
     * 設置界面風格
     *
     * @param title 當前標題
     */
    void setActivityStyles(String title);
}
