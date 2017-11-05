package com.bewei.retrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1. 类的用途 单例设计模式
 * 2. @author forever
 * 3. @date 2017/11/2 11:16
 */


public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private RetrofitUtils(){

    }
//双重检验锁
    public  static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
              retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private static Retrofit retrofit;
   //封装创建Retrofit
    public static synchronized Retrofit getRetrofit(String url){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(5000, TimeUnit.SECONDS).build();

  if (retrofit==null){
      retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
  }
return  retrofit;
    }
    //此方法为了返回网络接口对象
    public  <T>T getApiService(String url,Class<T> cl){

        Retrofit retrofit = getRetrofit(url);
       return retrofit.create(cl);
    }



}
