package com.unistrong.requestlibs.inter;

/**
 * 文件操作
 */
public interface IFile {

    /**
     * 截取文件名
     *
     * @param url
     * @return
     */
    String getFileName(String url);

    /**
     * 确认文件夹存在
     *
     * @param localPath
     */
    void confirmExsit(String localPath);
}
