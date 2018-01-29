package com.yandaoqiu.net.engine;

import android.content.Context;
import android.util.Log;

import com.yandaoqiu.net.engine.receiver.NetworkConnectChangedReceiver;
import com.yandaoqiu.net.inter.INetConfig;
import com.yandaoqiu.net.inter.INetInter;
import com.yandaoqiu.net.inter.INetListener;
import com.yandaoqiu.net.inter.INetStateChangeListener;
import com.yandaoqiu.net.projo.INET_STATE;
import com.yandaoqiu.net.projo.INetTask;

import java.util.ArrayList;

import okhttp3.Interceptor;


/**
 * Created by yandaoqiu on 2018/1/8.
 */

public class INet implements INetInter{

    private Context mContext;
    //网络状态监听
    private INetStateChangeListener mListener;
    //监听
    private ArrayList<INetListener> mListeners = new ArrayList<>();

    private INetApiWather mWather;

    private INetApi mNetApi = null;

    private static INet iNet;

    private INET_STATE netState;
    private INet(){
    }

    public static synchronized INet getInstatce(){
        if (iNet == null){
            iNet = new INet();
        }
        return iNet;
    }

    @Override
    public void init(Context context, INetStateChangeListener listener) {
        this.mContext = context;
        this.mListener = listener;
        //初始化Api
        mNetApi = new INetApi(mConfig);
        mWather = new INetApiWather(mNetApi,mConfig,mListeners);
        netState = NetworkConnectChangedReceiver.getNetWorkState(mContext);
    }

    /**
     * 异步请求
     *
     * @param task
     */
    @Override
    public long net(INetTask task) {
        if (mNetApi == null || mWather == null){
            throw new RuntimeException("INet未初始化，请先调用init初始化!");
        }
        mWather.net(task);
        mWather.start();
        return task.getTaskid();
    }

    /**
     * 监听
     *
     * @param listener
     */
    @Override
    public void registerListener(INetListener listener) {
        synchronized (mListeners){
            if (mListeners.contains(listener))return;
            mListeners.add(listener);
        }
    }

    /**
     * 移除监听
     *
     * @param listener
     */
    @Override
    public void unRegisterListner(INetListener listener) {
        synchronized (mListeners){
            mListeners.remove(listener);
        }
    }


    /**
     * 取消请求
     *
     * @param task
     */
    @Override
    public void cancel(INetTask task) {
        mWather.cancel(task);
    }

    /**
     * 取消请求
     * @param taskid
     */
    @Override
    public void cancel(long taskid) {
        mWather.cancel(taskid);
    }

    protected INetConfig mConfig = new INetConfig() {
        /**
         * 是否debug
         * debug模式自动打印log，Response 不管什么模式直接增加返回报文原始内容
         *
         * @return
         */
        @Override
        public boolean debug() {
            return super.debug();
        }

        /**
         * 设置最大请求并发数量
         *
         * @return
         */
        @Override
        public int maxRequestCount() {
            return super.maxRequestCount();
        }

        /**
         * 设置连接超时
         *
         * @return
         */
        @Override
        public long connectedTimeout() {
            return super.connectedTimeout();
        }

        /**
         * 设置写超时
         *
         * @return
         */
        @Override
        public long writeTimeout() {
            return super.writeTimeout();
        }

        /**
         * 设置读超时
         *
         * @return
         */
        @Override
        public long redTimeout() {
            return super.redTimeout();
        }

        /**
         * 默认请求失败重新请求
         *
         * @return
         */
        @Override
        public boolean reConnect() {
            return super.reConnect();
        }

        /**
         * 默认开缓存，缓存只有GET生效
         * @return
         */
        public boolean cache(){
            return true;
        }
    };
    /**
     * 设置配置
     *
     * @param config
     */
    @Override
    public void config(INetConfig config) {
        this.mConfig = config;
        //检查是否有baseurl，有则初始化出Retrofit
        mWather.setConfig(config);
    }

    /**
     * 添加应用拦截器
     *
     * @param interceptor
     */
    @Override
    public void addInterceptor(Interceptor interceptor) {
        mNetApi.addInterceptor(interceptor);
    }

    /**
     * 添加网络层拦截器
     *
     * @param interceptor
     */
    @Override
    public void addNetworkInterceptor(Interceptor interceptor) {
        mNetApi.addNetworkInterceptor(interceptor);
    }


    public void updateNetState(INET_STATE state){
        if (this.netState == state)return;
        Log.e("updateNetState",state+"");
        this.netState = state;
        if (mListener !=null){
            mListener.onNetStateChange(state);
        }
    }

    public INET_STATE getNetState(){
        return netState;
    }
    /**
     * Retrofit 模式的支持
     *
     * @param service
     * @return
     */
    @Override
    public <T> T create(Class<T> service) {
        return mNetApi.create(service);
    }
}
