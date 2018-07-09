package com.unistrong.requestlibs.inter;

public interface IResponse {

    void onSuccess(String json);

    void onFailure(String message);

    /**
     * 下载中 int 为 XX%
     *
     * @param progress
     */
    void onProgress(int progress);
}
