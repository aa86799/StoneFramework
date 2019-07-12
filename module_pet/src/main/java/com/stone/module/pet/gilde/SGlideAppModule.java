package com.stone.module.pet.gilde;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * desc:
 * author:  stone
 * email:   aa86799@163.com
 * blog:    https://stone.blog.csdn.net
 * time:    2019-05-12 16:50
 */
@GlideModule
public class SGlideAppModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int memoryCacheSizeBytes = 1024 * 1024 * 200; // mb
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2) //内存缓存的屏数
                .setBitmapPoolScreens(2)
                .setMaxSizeMultiplier(0.35f) //应用最大内存的百分比
                .setLowMemoryMaxSizeMultiplier(0.2f) //低内存设备时的百分比
                .build();
//        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
//        builder.setDiskCache(new DiskLruCacheFactory()); //参数：磁盘缓存文件路径，size
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context,
                "imgCache", 1024 * 1024 * 200));
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, //内部存储
//                "imgCache", 1024 * 1024 * 150));
        builder.setDefaultRequestOptions(
                new GlideOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                /*
DiskCacheStrategy.NONE  表示不缓存任何内容。
DiskCacheStrategy.DAT   表示只缓存原始图片。
DiskCacheStrategy.RESOURCE  表示只缓存转换过后的图片。
DiskCacheStrategy.ALL   表示既缓存原始图片，也缓存转换过后的图片。
DiskCacheStrategy.AUTOMATIC 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
                 */
//                .disallowHardwareConfig()
        );
    }

}
