package com.picture;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.picture.lib.PictureBaseActivity;
import com.picture.lib.R;
import com.picture.lib.config.PictureConfig;
import com.picture.lib.photoview.PhotoView;

/**
 * 展示照片
 */
public class DisplayImageActivity extends PictureBaseActivity implements View.OnClickListener {
    private static final String TAG = "DisplayImageActivity";

    private String imagePath;
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        updateNavigation();
        setContentView(R.layout.activity_dispaly_image);
        photoView = findViewById(R.id.photo_view);
        imagePath = getIntent().getStringExtra(PictureConfig.DIRECTORY_PATH);
        if (!TextUtils.isEmpty(imagePath)) {
            displayImage();
        }
    }

    private void updateNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
        }
    }

    private void displayImage() {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this).asBitmap()
                .load(imagePath)
                .apply(options)
                .into(photoView);
    }

    public void clickBack(View v) {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
