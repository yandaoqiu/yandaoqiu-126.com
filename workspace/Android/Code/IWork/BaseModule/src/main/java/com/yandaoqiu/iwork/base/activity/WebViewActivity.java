package com.yandaoqiu.iwork.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.yandaoqiu.iwork.base.R;

/**
 * Created by yandaoqiu on 2018/1/31.
 */

public class WebViewActivity extends BaseActivity{

    public WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_webview);
        mWebView = (WebView) findViewById(R.id.base_webview);
    }


}
