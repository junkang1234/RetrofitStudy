package com.bewei.retrofit.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bewei.retrofit.R;
import com.bewei.retrofit.RetrofitUtils;
import com.bewei.retrofit.api.Api;
import com.bewei.retrofit.bean.News;
import com.bewei.retrofit.bean.Party;
import com.bewei.retrofit.bean.User;
import com.bewei.retrofit.inter.ApiService;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   getNoParams();
      //  getHasParams();
       // getHasParams2();
       // register();

      //  uploadPic();
       // uploadPic2();

        addOk();

        retrofitUtils();


    }

    private void retrofitUtils() {
      //  Call<News> call = RetrofitUtils.getInstance().getApiService(Api.baseUrl1, ApiService.class).getNoParams();
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.baseUrl1, ApiService.class);
        Call<News> noParams =apiService.getNoParams();
        noParams.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    private void addOk() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(5000, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(Api.baseUrl1).addConverterFactory(GsonConverterFactory.create()).build();
}


    //上传图片 参数有2个 uid  file
    private void uploadPic2() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl5).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //图片文件
        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures/Screenshots/a.jpg");

        //创建RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        String uids = "123";//动态获取
        RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), uids);
        Call<User> call = apiService.uploadPic2(uid, body);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
//上传图片 参数只有一个 File
    private void uploadPic() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl5).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //图片文件
        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures/Screenshots/a.jpg");

        //创建RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Call<User> userCall = apiService.uploadPic(body);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //post请求
    private void register() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl4).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.register("13511237846", "123456");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    //有参get请求
    private void getHasParams2() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl3).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Party> call = apiService.getHasParams2(0, 0);
        call.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Call<Party> call, Response<Party> response) {
                Party party = response.body();
                String result = party.getResult();
                Log.i("xxx",result);
            }

            @Override
            public void onFailure(Call<Party> call, Throwable t) {

            }
        });

    }

    //有参get请求
    private void getHasParams() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl2).addConverterFactory(GsonConverterFactory.create()).build();
        ////通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> call = apiService.getHasParams("forever");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                String avatar_url = user.getAvatar_url();
                Log.i("xxx",avatar_url);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    //无参get请求
    private void getNoParams() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl1).addConverterFactory(GsonConverterFactory.create()).build();
        //通过动态代理的方式得到网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //得到Call对象
        Call<News> call = apiService.getNoParams();
        //执行异步请求 回调在主线程
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                List<News.AdsBean> ads = news.getAds();
                for (int i = 0; i < ads.size(); i++) {
                    News.AdsBean adsBean = ads.get(i);
                    Log.i("xxx",adsBean.getGonggaoren());
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }
}
