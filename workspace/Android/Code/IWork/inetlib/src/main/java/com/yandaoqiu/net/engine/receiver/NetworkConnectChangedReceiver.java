package com.yandaoqiu.net.engine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.yandaoqiu.net.engine.INet;
import com.yandaoqiu.net.projo.INET_STATE;

/**
 * Created by yandaoqiu on 2018/1/11.
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction()) || WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())
                || ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            INet.getInstatce().updateNetState(getNetWorkState(context));
        }
    }



    public static INET_STATE getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return INET_STATE.LINE_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return INET_STATE.LINE_MOBILE;
            }else {
                return INET_STATE.LINE_OTHER;
            }
        } else {
            return INET_STATE.OFFLINE;
        }
    }
}
