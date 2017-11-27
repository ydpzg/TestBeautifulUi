package com.dukelight.testbeautifului;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ScrollingActivity extends AppCompatActivity {

    private LinearLayout mLlNavi;
    private AppBarLayout mAppBar;
    private CoordinatorLayout mCoordinator;
    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            // 部分机型的statusbar会有半透明的黑色背景
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);// SDK21
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //After LOLLIPOP not translucent status bar
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Then call setStatusBarColor.
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mLlNavi = findViewById(R.id.ll_navi);
        mAppBar = findViewById(R.id.app_bar);
        mCoordinator = findViewById(R.id.coordinator);
        mScrollView = findViewById(R.id.scroll_view);

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {

                }
                System.out.println("verticalOffset=" + verticalOffset);
            }
        });
        mAppBar.setAlpha(0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    System.out.println("i=" + i + ",i1=" + i1);

                    if (i1 < 300) {
                        mAppBar.setAlpha(i1 * (1 / 300f));
                    } else {
                        mAppBar.setAlpha(1f);
                    }
                }
            });
        }
    }

}
