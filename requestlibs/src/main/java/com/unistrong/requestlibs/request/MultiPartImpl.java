package com.unistrong.requestlibs.request;

import android.os.Handler;
import android.util.Log;

import com.unistrong.requestlibs.inter.IFile;
import com.unistrong.requestlibs.response.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 二进制文件的上传、下载操作
 */
public class MultiPartImpl extends BasePolicy implements IFile {
    private static final String TAG = "MultiPartImpl";

    private static final MediaType MEDIA_BINARY_STREAM = MediaType.parse("multipart/form-data");

    private static MultiPartImpl instance;

    public static MultiPartImpl getInstance() {
        return instance == null ? new MultiPartImpl() : instance;
    }

    @Override
    public String getFileName(String url) {
        String[] elements = url.split("/");
        String fileName = elements[elements.length - 1];
        if (!fileName.contains(".")) {
            //非xx.xx形式
            fileName = System.currentTimeMillis() + ".temp";
        }
        return fileName;
    }

    @Override
    public void confirmExsit(String localPath) {
        File file = new File(localPath);
        if (file.isDirectory() && !file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 下载文件
     *
     * @param serverUrl
     * @param localPath
     * @param handler
     * @param listener
     */
    public void downloadFile(final String serverUrl, final String localPath, Handler handler, ResponseBody listener) {
        confirmExsit(localPath);
        String fileName = localPath + "/" + getFileName(serverUrl);
        Request request = new Request.Builder().url(serverUrl).build();
        newClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
                MainThread.runOnUIThread(handler, () -> listener.onFailure(e.toString()));
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buf = new byte[1024 * 8];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(new File(fileName));
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        MainThread.runOnUIThread(handler, () -> listener.onProgress(progress));
                    }
                    fos.flush();
                    // 下载完成
                    Log.e("【download Success】", fileName);
                    MainThread.runOnUIThread(handler, () -> listener.onSuccess(fileName));
                } catch (Exception e) {
                    Log.e(TAG, "download failed!" + e.toString());
                    MainThread.runOnUIThread(handler, () -> listener.onFailure(e.toString()));
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 上传文件
     *
     * @param serverUrl 服务器地址
     * @param localPath 本地文件地址
     * @param handler
     * @param listener
     */
    public void uploadFile(String serverUrl, String localPath, Handler handler, ResponseBody listener) {
        RequestBody body = RequestBody.create(MEDIA_BINARY_STREAM, new File(localPath));
        Request request = new Request.Builder().url(serverUrl).post(body).build();
        Call call = newClient().newCall(request);
        if (listener != null) {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "上传失败_CAUSE-->" + e.toString());
                    MainThread.runOnUIThread(handler, () -> listener.onFailure(e.toString()));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e(TAG, "上传成功_RESULT-->" + string);
                        MainThread.runOnUIThread(handler, () -> listener.onSuccess(string));
                    } else {
                        Log.e(TAG, "上传失败_CAUSE--> Response Failed!");
                        MainThread.runOnUIThread(handler, () -> listener.onFailure("Response Failed!"));
                    }
                }
            });
        }
    }
}
