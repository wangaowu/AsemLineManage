package com.unistrong.requestlibs.response;

import com.unistrong.requestlibs.inter.IResponse;

public abstract class ResponseBody<T> implements IResponse {
    private static final int SUCCESS_CODE = 1;
    public Class clazz;

    public ResponseBody(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onSuccess(String json) {
    }

    @Override
    public void onProgress(int progress) {
    }

    @Override
    public abstract void onFailure(String message);

    public abstract void onSuccess(T json);

    public void onJsonFormateError(String message) {
    }

    public boolean isFailure(int code) {
        return SUCCESS_CODE != code;
    }
}
