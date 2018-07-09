package com.unistrong.framwork.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.unistrong.baselibs.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Glide带验证
 */
public class VerifyGlide {
    private static VerifyGlide instance;

    public static VerifyGlide getInstance() {
        return instance == null ? new VerifyGlide() : instance;
    }

    /**
     * 加载验证token
     *
     * @param srcUrl
     * @param imageView
     */
    public void load(String srcUrl, ImageView imageView) {
        Glide.with(imageView.getContext()).load(srcUrl).into(imageView);
    }

    /**
     * 加载验证token
     *
     * @param srcUrl
     * @param imageView
     */
    public void loadVerify(String srcUrl, ImageView imageView) {
        String token = SPUtils.getString(imageView.getContext(), Constant.SP_KEY.TOKEN);
        GlideUrl glideUrl = new GlideUrl(srcUrl, new TokenHeader(token));
        Glide.with(imageView.getContext()).load(glideUrl).into(imageView);
    }

    static class TokenHeader implements Headers {
        private String token;

        public TokenHeader(String token) {
            this.token = token;
        }

        @Override
        public Map<String, String> getHeaders() {
            Map<String, String> header = new HashMap<>();
            //不一定都要添加，具体看原站的请求信息
            header.put("token", token);
            return header;
        }
    }

}
