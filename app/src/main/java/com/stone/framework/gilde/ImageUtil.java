package com.stone.framework.gilde;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stone.framework.R;

/**
 * desc   : Glide图片加载工具
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019-05-12 16:52
 */

public class ImageUtil {
    private static int defaultResId = R.mipmap.ic_launcher;

    public static void load(Object obj, Uri uri, ImageView imageView) {
        load(obj, uri.toString(), imageView);
    }

    public static void load(Object obj, String uri, ImageView imageView) {
        with(obj).load(uri).placeholder(defaultResId).into(imageView);
    }

    public static void loadCenterCrop(Object obj, Uri uri, ImageView imageView) {
        loadCenterCrop(obj, uri.toString(), imageView);
    }

    public static void loadCenterCrop(Object obj, String uri, ImageView imageView) {
        with(obj).load(uri).placeholder(defaultResId).centerCrop().into(imageView);
    }

    public static void loadFrontWH(Object obj, Uri uri, ImageView imageView, int wh) {
        loadFrontWH(obj, uri.toString(), imageView, wh);
    }

    public static void loadFrontWH(Object obj, String uri, ImageView imageView, int wh) {
        with(obj).load(uri).placeholder(defaultResId).override(wh).into(imageView);
    }

    public static void loadFrontWHCenterCrop(Object obj, Uri uri, ImageView imageView, int wh) {
        loadFrontWHCenterCrop(obj, uri.toString(), imageView, wh);
    }

    public static void loadFrontWHCenterCrop(Object obj, String uri, ImageView imageView, int wh) {
        with(obj).load(uri).placeholder(defaultResId).centerCrop().override(wh).into(imageView);
    }

    public static void loadCircle(Context obj, Uri uri, ImageView imageView) {
        loadCircle(obj, uri.toString(), imageView);
    }

    public static void loadCircle(Context obj, String uri, ImageView imageView) {
        with(obj).load(uri).placeholder(defaultResId).circleCrop().into(imageView);
    }

    public static void loadBackground(Context obj, String url, final ImageView view,
                                      final Runnable onStartOp,
                                      final Runnable onResourceReadyOp,
                                      final Runnable onLoadClearedOp,
                                      final Runnable onLoadFailedOp) {
        with(obj).load(url).into(new CustomTarget<Drawable>() {

            @Override //非必须重写
            public void onStart() {
                super.onStart();
                onStartOp.run();
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setBackgroundDrawable(resource);
                onResourceReadyOp.run();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                onLoadClearedOp.run();
            }

            @Override //非必须重写
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
//                onLoadFailedOp.run();
            }

        });
    }

    public static void loadBackground(Context obj, int resId, final ImageView view,
                                      final Runnable onStartOp,
                                      final Runnable onResourceReadyOp,
                                      final Runnable onLoadClearedOp,
                                      final Runnable onLoadFailedOp) {
        with(obj).load(resId).into(new CustomTarget<Drawable>() {

            @Override //非必须重写
            public void onStart() {
                super.onStart();
                onStartOp.run();
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setBackgroundDrawable(resource);
                onResourceReadyOp.run();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                onLoadClearedOp.run();
            }

            @Override //非必须重写
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
//                onLoadFailedOp.run();
            }

        });
    }

    private static GlideRequests with(Object obj) {
        if (obj instanceof Activity) {
            return GlideApp.with((Activity) obj);
        } else if (obj instanceof Fragment) {
            return GlideApp.with((Fragment) obj);
        } else if (obj instanceof View) {
            return GlideApp.with((View) obj);
        } else if (obj instanceof Context) {
            return GlideApp.with((Context) obj);
        }
        return null;
    }
}
