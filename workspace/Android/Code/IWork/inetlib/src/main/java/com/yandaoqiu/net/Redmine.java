package com.yandaoqiu.net;

/**
 * Created by yandaoqiu on 2018/1/8.
 */

public final class Redmine {
    /****本框架集成 Retrofit + RxJava ＋ OkHttp****/

    /****RequestBody 使用方式******************/
    /**
    post请求创建request和get是一样的，只是post请求需要提交一个表单，就是RequestBody。表单的格式有好多种，普通的表单是：

    RequestBody body = new FormBody.Builder()
            .add("键", "值")
            .add("键", "值")
            ...
            .build();

    RequestBody的数据格式都要指定Content-Type，常见的有三种：
    application/x-www-form-urlencoded 数据是个普通表单
    multipart/form-data 数据里有文件
    application/json 数据是个json
    但是好像以上的普通表单并没有指定Content-Type，这是因为FormBody继承了RequestBody，它已经指定了数据类型为application/x-www-form-urlencoded。

    private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    再看看数据为其它类型的RequestBody的创建方式。

    如果表单是个json：
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(JSON, "你的json");

    如果数据包含文件：
    RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
            .build();

     **/

}
