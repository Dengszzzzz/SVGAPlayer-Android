package com.example.ponycui_home.svgaplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dengzh
 * @date 2021/5/18
 * Description:
 */
public class TestActivity extends AppCompatActivity {

    private TestAdapter mAdapter;
    private List<String> svgaList = new ArrayList();
    private List<String> nameList = new ArrayList();
    private   RecyclerView recyclerView;
    private SVGAImageView svgaImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        nameList.add("风之子");
        nameList.add("火焰精灵");
        nameList.add("海洋精灵");
        nameList.add("蔷薇天使");
        nameList.add("森林之神");
        nameList.add("曙光女皇");

        svgaList.add("http://pic.yyxingqiu.com/FolW0tNO-8qk4qKY_dMKAf0CyaI4?imageslim");
        svgaList.add("http://pic.yyxingqiu.com/ll2VTGdrLXXzboYR9a6R72L8L0AZ?imageslim");
        svgaList.add("http://pic.yyxingqiu.com/lq50k6un3D7YsLmnhVqUn0FjOHgr?imageslim");
        svgaList.add("http://pic.yyxingqiu.com/FnwYdZyh77eSTcX7LvswRefuLorp?imageslim");
        svgaList.add("http://pic.yyxingqiu.com/lvR5HNJCx9hOpZ3Gda4LpcXDPhwb?imageslim");
        svgaList.add("http://pic.yyxingqiu.com/lrX2LQpW2EKUZ4aC1FlcZ22FJce3?imageslim");



        mAdapter = new TestAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                loadAnimation(svgaList.get(position));
            }
        });
        mAdapter.setNewData(nameList);


        recyclerView = findViewById(R.id.recyclerView);
        svgaImageView = findViewById(R.id.svgaImageView);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(mAdapter);
    }

    private void loadAnimation(String url) {
        try { // new URL needs try catch.
            SVGAParser svgaParser = SVGAParser.Companion.shareParser();
          //  svgaParser.setFrameSize(100,100);
            svgaParser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    Log.d("##","## FromNetworkActivity load onComplete");
                    svgaImageView.setVideoItem(videoItem);
                    svgaImageView.startAnimation();
                }
                @Override
                public void onError() {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
