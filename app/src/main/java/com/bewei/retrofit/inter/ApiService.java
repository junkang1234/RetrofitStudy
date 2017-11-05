package com.bewei.retrofit.inter;

import com.bewei.retrofit.R;
import com.bewei.retrofit.bean.News;
import com.bewei.retrofit.bean.Party;
import com.bewei.retrofit.bean.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 1. 类的用途 网络接口
 * 2. @author forever
 * 3. @date 2017/11/1 14:32
 */


public interface ApiService {
    /**
     * 无参get请求
     * http://service.meiyinkeqiu.com/service/ads/cptj
     *
     * @return
     */
    @GET("service/ads/cptj")
    Call<News> getNoParams();

    /**
     * 有参get请求
     * 拼接参数 /形式
     *
     * @return https://api.github.com/users/baiiu
     */
    @GET("users/{user}")
   Call<User> getHasParams(@Path("user") String user);

    /**
     * http://www.93.gov.cn/93app/data.do
     * channelId
     * startNum
     * 拼接 ? &
     * 为主
     */
    @GET("data.do")

   Call<Party> getHasParams2(@Query("channelId")int channelId, @Query("startNum") int startNum );

    /**
     * post请求 http://120.27.23.105/user/reg 注册
     */
    @POST("reg")
    @FormUrlEncoded//支持表单提交
    Call<User> register(@Field("mobile")String mobile,@Field("password")String password);

    /**
     * 上传图片 参数只有一个 File
     * @param
     * @param file
     * @return
     */
    @Multipart
    @POST("08web/FileUploadServlet")
  Call<User>  uploadPic( @Part MultipartBody.Part file);
    /**
     * 上传图片 参数有2个 uid  file
     * @param
     * @param file
     * @return
     */
    @Multipart
    @POST("file/upload")
  Call<User>  uploadPic2(@Part("uid")RequestBody uid, @Part MultipartBody.Part file);



}
