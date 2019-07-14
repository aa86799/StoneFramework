package com.stone.module.pet.api;

import android.content.Context;
import android.util.Log;
import com.blankj.utilcode.util.AppUtils;
import com.stone.module.pet.config.PetGlobalConfig;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * desc     :
 * author   : stone
 * email    : aa86799@163.com
 * time     : 2019/3/1 13 27
 */
class SRetrofit {

    private static OkHttpClient mClient;

    private static volatile Retrofit mRetrofit;

    private SRetrofit() {

    }

    private static class Builder {
        private static final SRetrofit instance = new SRetrofit();
    }

    private static SRetrofit getInstance() {
        return Builder.instance;
    }

    private OkHttpClient getClient(Context context, List<Interceptor> interceptors) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("sokhttp-> ", message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存文件夹
        File cacheFile = new File(context.getApplicationContext().getExternalCacheDir(), "cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        Cache cache = new Cache(cacheFile, cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.addInterceptor(logInterceptor);
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        /*
                         *　User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统
                         * 　及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                         *  这里标记为 android 设备
                         */
                        .addHeader("User-Agent", "jw_app_android_" + AppUtils.getAppVersionCode(PetGlobalConfig.INSTANCE.getApp().getPackageName()))
                        .build();
                return chain.proceed(request);
            }
        });

//           builder.addNetworkInterceptor();
//          builder.writeTimeout();
        builder.cache(cache); //缓存，需要设置缓存文件
        builder.cookieJar(CookieManger.getCookieManger()); //设置 响应和请求中的 cookie
//          builder.connectionPool(); //连接池

        HttpsUtils.SSLParams sslParams = createSSL();
        //访问 http， ssl-socket 相关规则
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//          builder.authenticator(); //token 认证
//        mClient =  builder.build();
        mClient = RetrofitUrlManager.getInstance().with(builder).build();
        return mClient;

        /*
         * https://github.com/JessYanCoding/RetrofitUrlManager
         *
         *  public interface ApiService {
                 @Headers({"Domain-Name: douban"}) // Add the Domain-Name header
                 @GET("/v2/book/{id}")
                 Observable<ResponseBody> getBook(@Path("id") int id);
            }

             // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
             RetrofitUrlManager.getInstance().putDomain("douban", "https://api.douban.com");

            If you want to change the global BaseUrl:
             // BaseUrl configured in the Domain-Name header will override BaseUrl in the global setting
             RetrofitUrlManager.getInstance().setGlobalDomain("your BaseUrl");

              //如果有需要可以注册监听器,当一个 Url 的 BaseUrl 被新的 Url 替代,则会回调这个监听器,调用时间是在接口请求服务器之前
             RetrofitUrlManager.getInstance().registerUrlChangeListener(mListener);

         */
    }

    public static Retrofit get(Context context, String baseUrl, List<Interceptor> interceptors) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(getInstance().getClient(context, interceptors))
                    .build();
        }
        return mRetrofit;
    }

    private static HttpsUtils.SSLParams createSSL() {
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();

        //方法二：自定义信任规则，校验服务端证书
//      HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new X509TrustManager());

        //方法三：使用预埋证书，校验服务端证书（自签名证书）
//      HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));

        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//      HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets()
//              .open("xxx.bks"), "123456", getAssets().open("yyy.cer"));

        return sslParams1;

    }
}
