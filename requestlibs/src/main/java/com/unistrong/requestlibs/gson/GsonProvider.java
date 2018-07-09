package com.unistrong.requestlibs.gson;


import com.google.gson.Gson;

/**
 * Gson单例类
 */
public class GsonProvider {

    static Gson gson = new Gson();

    public static Gson instance() {
        return gson;
    }
}
