package com.yandaoqiu.net.engine;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.yandaoqiu.net.inter.INetConfig;
import com.yandaoqiu.net.inter.INetListener;
import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;


import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yandaoqiu on 2018/1/9.
 */

public class INetApiWather implements Runnable{
    private Object LOCK = new Object();
    //请求队列 优先级高
    private HashMap<Long,INetTask> mTaskQueue_H = new HashMap<>();
    private ArrayList<Long> mTaskID_H = new ArrayList<>();
    //请求队列 优先级低
    private HashMap<Long,INetTask> mTaskQueue_L = new HashMap<>();
    private ArrayList<Long> mTaskID_L = new ArrayList<>();

    private HashMap<Long,INetTask> mRequestTaskQueue = new HashMap<>();
    private ArrayList<Long> mTaskID_R = new ArrayList<>();

    private ArrayList<INetListener> mListeners;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private INetApi mApi;
    private INetConfig mConfig;

    public INetApiWather(INetApi api,INetConfig config, ArrayList<INetListener> listeners){
        this.mApi = api;
        this.mConfig = config;
        this.mListeners = listeners;
    }
    protected void setConfig(INetConfig config){
        this.mConfig = config;
        this.mApi.initRetrofit();
    }

    protected void net(INetTask task){
        synchronized (LOCK){

            //TODO 检查队列中是否有相同URL+参数+listener 的task 没有处理完，直接返回
            if (task.getLevel() == INetTask.INET_LEVEL.LEVEL_HIGHT){
                mTaskQueue_H.put(task.getTaskid(),task);
                mTaskID_H.add(task.getTaskid());
            }else {
                mTaskQueue_L.put(task.getTaskid(),task);
                mTaskID_L.add(task.getTaskid());
            }
            task.setState(INetTask.INET_PROGRESS.PROGRESS_WAITING);
        }
    }

    protected void cancel(long taskid){
        synchronized (LOCK) {
            INetTask task = mTaskQueue_H.get(taskid);
            if (task == null)task = mTaskQueue_L.get(taskid);
            if (task == null)return;
            if (task.okHttpCall != null)task.okHttpCall.cancel();
            removeTask(taskid);
        }
    }

    protected void cancel(INetTask task){
       cancel(task.getTaskid());
    }

    //获取最后一个Task
    private INetTask getTask(){
        synchronized (LOCK){
            INetTask task = null;
            if (mTaskID_H.size() > 0){
                task = mTaskQueue_H.get(mTaskID_H.get(mTaskID_H.size() -1));
            }

            if (task == null){
                if (mTaskID_L.size() > 0){
                    task = mTaskQueue_L.get(mTaskID_L.get(mTaskID_L.size() -1));
                }
            }
            return task;
        }
    }

    private void removeTask(INetTask task){
         removeTask(task.getTaskid());
    }
    private void removeTask(long taskid){
        synchronized (LOCK){
            mTaskID_H.remove(taskid);
            mTaskID_L.remove(taskid);
            mTaskQueue_H.remove(taskid);
            mTaskQueue_L.remove(taskid);
        }
    }
    protected void start(){
        INetTask currentTask = getTask();
        if (currentTask == null)return;
        mRequestTaskQueue.put(currentTask.getTaskid(),currentTask);
        mTaskID_R.add(currentTask.getTaskid());
        removeTask(currentTask);
        mHandler.post(this);
        start();
    }


    @Override
    public void run() {
        //从缓冲中拿一个task出来
        int size = mTaskID_R.size();
        if (size == 0)return;
        synchronized (mRequestTaskQueue){
            //TODO 判断当前网络，无网络直接返回
            long taskid = mTaskID_R.get(mTaskID_R.size() - 1);
            final INetTask requestTask = mRequestTaskQueue.get(taskid);
            mTaskID_R.remove(taskid);
            if (requestTask == null){
                Log.e("INet","Error:requestTask is null!");
                return;
            }

            Observable<INetResponse> observable = Observable.create(new ObservableOnSubscribe<INetResponse>() {
                @Override
                public void subscribe(ObservableEmitter<INetResponse> e)  {
                    INetResponse iNetResponse = new INetResponse();
                    iNetResponse.taskid = requestTask.getTaskid();
                    try {
                        requestTask.setState(INetTask.INET_PROGRESS.PROGRESS_REQUESTING);
                        Request.Builder reuqestBuilder =  new Request.Builder();
                        String url = requestTask.getUrl();
                        if (!TextUtils.isEmpty(mConfig.baseURL())){
                            url = mConfig.baseURL()+url;
                        }
                        reuqestBuilder.url(url);
                        reuqestBuilder.method(requestTask.getRequestType(),requestTask.getBody());
                        if (requestTask.getHeaders() != null) {
                            reuqestBuilder.headers(requestTask.getHeaders());
                        }

                        Request request = reuqestBuilder.build();

                        requestTask.okHttpCall = mApi.getClient().newCall(request);
                        Response response = requestTask.okHttpCall.execute();
                        if (mConfig.debug())
                            iNetResponse.response = response;
                        if (response.code() != 200){
                            iNetResponse.isError = true;
                        }
                        iNetResponse.code = response.code();
                        iNetResponse.erroMsg = response.message();
                        if (requestTask.getJsonObj() != null) {
                            Gson gson = new Gson();
                            iNetResponse.json = gson.fromJson(response.body().string(), requestTask.getJsonObj());
                        }else {
                            iNetResponse.xml = response.body().string();
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        iNetResponse.isError = true;
                        if (ex!=null)
                            iNetResponse.erroMsg = ex.getMessage();
                        else
                            iNetResponse.erroMsg = "请求异常";
                    }finally {
                        e.onNext(iNetResponse);
                    }
                }
            });

            Consumer<INetResponse> consumer = new Consumer<INetResponse>() {
                @Override
                public void accept(INetResponse response){
                    //抛给上层调用，清除Task
                    requestTask.setState(INetTask.INET_PROGRESS.PROGRESS_DONE);
                    synchronized (mListeners){
                        for(INetListener listerner : mListeners ){
                            listerner.end(response);
                        }
                        mRequestTaskQueue.remove(response.taskid);
                    }
                }
            };

            if (requestTask.getIsBackWithMain()){
                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer);
            }else {
                observable.subscribeOn(Schedulers.newThread()).subscribe(consumer);
            }
        }
    }
}
