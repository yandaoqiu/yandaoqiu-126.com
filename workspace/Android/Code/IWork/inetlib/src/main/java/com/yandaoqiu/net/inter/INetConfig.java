package com.yandaoqiu.net.inter;

/**
 * Created by Administrator on 2018/1/8.
 */

public abstract class INetConfig {

    /**
     * 是否debug
     * debug模式自动打印log，Response 不管什么模式直接增加返回报文原始内容
     * @return
     */
    public  boolean debug(){
        return true;
    }

    /**
     * 设置最大请求并发数量
     * @return
     */
    public int maxRequestCount(){
        return 8;
    }

    /**
     * 设置连接超时 单位 S
     * @return
     */
    public long connectedTimeout(){
        return 10;
    }

    /**
     * 设置写超时 单位 S
     * @return
     */
    public long writeTimeout(){
        return 10;
    }

    /**
     * 设置读超时 单位 S
     * @return
     */
    public long redTimeout(){
        return 10;
    }

    /**
     * 默认请求失败重新请求
     * @return
     */
    public boolean reConnect(){
        return true;
    }

    /**
     * 默认开缓存，缓存只有GET生效
     * @return
     */
    public boolean cache(){
        return true;
    }

    /**
     * 基础URL，适合单一服务器请求，如果多服务器，不要设置该配置
     * @return
     */
    public String baseURL(){
        return null;
    }
}
