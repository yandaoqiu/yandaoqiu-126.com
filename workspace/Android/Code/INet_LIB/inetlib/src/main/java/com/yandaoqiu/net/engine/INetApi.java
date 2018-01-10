package com.yandaoqiu.net.engine;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yandaoqiu.net.inter.INetConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yandaoqiu on 2018/1/9.
 */

public class INetApi {

    private INetConfig config;
    private Retrofit mRetrofit;
    private OkHttpClient mClient;

    public INetApi(INetConfig config){
        init(config);
    }

    private void init(final INetConfig config){
        this.config = config;
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(config.connectedTimeout(), TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(config.writeTimeout(),TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(config.redTimeout(),TimeUnit.SECONDS);//读操作超时时间

        /**
         * 应用拦截器
         */
        Interceptor appInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();
                String s = url.url().toString();
                //---------请求之前-----
                if (config.debug()){
                    Log.d("Interceptor","App request URL:"+s);
                }
                Response  response = chain.proceed(request);
                if (config.debug()){
                    Log.d("Interceptor","App request Response:"+response.toString());
                }
                //---------请求之后------------
                return response;
            }
        };

        //添加拦截器
        builder.addInterceptor(appInterceptor);

        if(config.cache()){
            try {
                File cacheFile = new File( Environment.getExternalStorageDirectory(), "INetCache");
                Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
                builder.cache(cache);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //添加并发数量
        mClient = builder.build();
        mClient.dispatcher().setMaxRequestsPerHost(config.maxRequestCount() > 0 ? config.maxRequestCount() : 8);


        initRetrofit();
    }

    protected void setConfig(INetConfig config){
        this.config = config;
    }
    protected void initRetrofit(){
        if (mRetrofit != null)return;
        // 创建Retrofit
        Retrofit.Builder rb = new Retrofit.Builder()
                .client(mClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        if (!TextUtils.isEmpty(config.baseURL())){
            rb.baseUrl(config.baseURL());
        }else {
           return;
        }
        mRetrofit = rb.build();
    }



    /**
     * 添加应用拦截器
     *
     * @param interceptor
     */
    protected void addInterceptor(Interceptor interceptor) {
        mClient.interceptors().add(interceptor);
    }

    /**
     * 添加网络层拦截器
     *
     * @param interceptor
     */
    protected void addNetworkInterceptor(Interceptor interceptor) {
        mClient.networkInterceptors().add(interceptor);
    }

    public OkHttpClient getClient(){
        return mClient;
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

}
