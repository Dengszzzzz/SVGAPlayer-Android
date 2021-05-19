package com.example.ponycui_home.svgaplayer;

import android.app.Application;
import android.net.http.HttpResponseCache;

import com.opensource.svgaplayer.SVGACache;

import java.io.File;
import java.io.IOException;

/**
 * @author Dengzh
 * @date 2021/5/18
 * Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //设置 Svga 缓存
        try {
            File cacheDir = new File(getCacheDir(), "http");
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
