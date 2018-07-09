package com;

import android.app.Application;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.picture.lib.R;

public class PictureApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initGlide();
    }

    private void initGlide() {
        GlideBuilder glideBuilder = new GlideBuilder();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.encodeFormat(Bitmap.CompressFormat.JPEG);
        requestOptions.encodeQuality(80);
        requestOptions.error(R.drawable.bg_picture_default);
        glideBuilder.setDefaultRequestOptions(requestOptions);
        Glide.init(this, glideBuilder);
    }
}
