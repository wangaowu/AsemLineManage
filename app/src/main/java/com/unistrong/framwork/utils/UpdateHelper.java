package com.unistrong.framwork.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.unistrong.asemlinemanage.settings.UpdateActivity;
import com.unistrong.baselibs.style.Activity_;
import com.unistrong.framwork.resp.VersionResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.io.File;
import java.util.HashMap;

/**
 * 更新的帮助类
 */
public class UpdateHelper {
    public static String APK_FILE_DIR;

    static {
        APK_FILE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Asem";
        new File(APK_FILE_DIR).mkdirs();
    }

    public static void deleteInstalledApk() {
        //删除已安装过的文件
        for (File file : new File(APK_FILE_DIR).listFiles()) {
            if (file.isFile() && file.getName().contains(".apk")) {
                file.delete();
            }
        }
    }

    /**
     * 查询升级
     */
    public static void checkVersion(Activity_ context) {
        int localVersion = GetEdition.getVersionCode(context);
        HttpRequestImpl.getInstance().requestGet(Constant.Action.GET_VERSION, new HashMap(),
                new ResponseBody<VersionResp>(VersionResp.class) {
                    @Override
                    public void onFailure(String message) {
                    }

                    @Override
                    public void onSuccess(VersionResp resp) {
                        if (isFailure(resp.getCode())) return;
                        int serverCode = Integer.parseInt(resp.getResult().getAppVersionCode());
                        if (serverCode > localVersion) {
                            //有新版本,更新跳转
                            gotoUpdateActivity(context, resp.getResult().getAppPath());
                        } else {
                            UpdateHelper.deleteInstalledApk();
                        }
                    }
                });
    }

    private static void gotoUpdateActivity(Activity_ context, String apkUrl) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putExtra(UpdateActivity.INTENT_KEY, apkUrl);
        context.startActivity(intent);
        context.finish();
    }

    public static void autoInstall(Context context) {
        File apkFile = getTempFile();
        if (apkFile == null) return;
        if (Build.VERSION.SDK_INT >= 24) {
            //>=7.0
            //在AndroidManifest中的android:authorities值
            Uri apkUri = FileProvider.getUriForFile(context,
                    "com.unistrong.asemlinemanage.fileprovider", apkFile);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }
    }

    public static File getTempFile() {
        File file = new File(APK_FILE_DIR);
        if (file.listFiles().length != 0) {
            return file.listFiles(pathname -> pathname.getName().contains("apk"))[0];
        }
        return null;
    }


}
