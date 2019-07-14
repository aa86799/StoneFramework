package com.stone.module.pet.api;

import android.content.Context;

import java.util.List;

import com.blankj.utilcode.util.LogUtils;
import com.stone.module.pet.config.GlobalConfig;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

class CookieManger implements CookieJar {
    private static final String TAG = "CookieManger";
    private static PersistentCookieStore cookieStore;

    private static CookieManger cookieManger=new CookieManger();
    public static CookieManger getCookieManger(){
        return cookieManger;
    }

    /**
     * Mandatory constructor for the NovateCookieManger
     */
    public CookieManger(Context context) {
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(context);
        }
    }
    public CookieManger() {
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(GlobalConfig.INSTANCE.getApp().getApplicationContext());
        }
    }
    public void removeAll(){
        if (cookieStore!=null)
            cookieStore.removeAll();
    }
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        LogUtils.d("CookieManger","saveFromResponse");
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
                LogUtils.d("CookieManger","saveFromResponse"+item.toString());
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        LogUtils.d("CookieManger","loadForRequest");
        List<Cookie> cookies = cookieStore.get(url);
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                LogUtils.d("CookieManger","loadForRequest"+item.toString());
            }
        }
        return cookies;
    }
}
