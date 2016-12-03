package com.base.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by 俞智威
 * on 2016-02-03.
 * 09:26
 * Procedure Explain:
 */
public class ImageLoaderUtil {

    public static final String TAG = "ImageLoaderUtil";

    private static DisplayImageOptions options;

    private static DisplayImageOptions.Builder builder;

    private static Context applicationContext;

    private static int drawableId;

    public static void init(Context applicationContext, int drawable) {
        drawableId = drawable;
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(applicationContext)
                .threadPoolSize(Thread.NORM_PRIORITY - 2)//线程池大小
                .denyCacheImageMultipleSizesInMemory()//否定高速缓存图像多大小在内存中
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//磁盘缓存文件名生成器MD5
                .tasksProcessingOrder(QueueProcessingType.LIFO)//后进先出法
                .writeDebugLogs()//编写调试日志
                .build();//建立
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(imageLoaderConfiguration);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(drawable)//加载图片时显示的默认图片
                .showImageForEmptyUri(drawable)//显示空URI的图像
                .showImageOnFail(drawable)//显示图像失败
                .cacheInMemory(true)//是否添加到内存缓存中
                .cacheOnDisk(true)//是否添加到硬盘缓存中
                .considerExifParams(true)
                .build();

        builder = new DisplayImageOptions.Builder()
                .showImageOnLoading(drawable)//加载图片时显示的默认图片
                .showImageForEmptyUri(drawable)//显示空URI的图像
                .showImageOnFail(drawable)//显示图像失败
                .cacheInMemory(true)//是否添加到内存缓存中
                .cacheOnDisk(true)//是否添加到硬盘缓存中
                .considerExifParams(true);
        ImageLoaderUtil.applicationContext = applicationContext;
    }

    /**
     * 普通图片加载
     */
    public static void displayImage(String imageURL, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(urlTransformation(imageURL), imageView, options);
    }

    /**
     * 普通图片加载
     */
    public static void displayImage(int image, ImageView imageView) {
        displayImage("drawable://" + image, imageView);
    }

    /**
     * 圆角图片加载
     */
    public static void displayImage(int image, ImageView imageView, int cornerRadiusPixels) {
        displayImage("drawable://" + image, imageView, cornerRadiusPixels);
    }

    /**
     * 圆角图片加载
     */
    public static void displayImage(String imageURL, ImageView imageView, int cornerRadiusPixels) {
        builder.displayer(new RoundedBitmapDisplayer(Math.round(applicationContext.getResources().getDisplayMetrics().density * cornerRadiusPixels)));
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(urlTransformation(imageURL), imageView, builder.build());
    }

    /**
     * 高斯模糊
     */
    public static void displayBlurImage(String imageURL, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(urlTransformation(imageURL), imageView, builder.build());
    }

    /**
     * 返回图片路径
     */
    public static String getImagePath(String imageURL) {
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance().getDiskCache().get(imageURL).getPath();
    }

    /**
     * 地址转换
     * 把相对地址转换成实际地址
     */
    public static String urlTransformation(String imageURL) {
        if (imageURL == null || imageURL.trim().isEmpty()) {
            return "drawable://" + drawableId;
        } else if (imageURL.matches(NO_TRANSFORMATION)) {
            Log.e(TAG, "不转换 " + imageURL);
            return imageURL;
        } else {
            Log.e(TAG, "转换 " + imageURL);
            return imageURL;
        }
    }

    /**
     * 获取图片选项
     *
     * @param image  默认图片资源id 传负数表示没有默认图
     * @param radius 圆角(单位dp)   传负数表示没有圆角
     */
    public static DisplayImageOptions getImageOptions(int image, float radius) {

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true);
        if (image >= 0) {
            builder.showImageOnLoading(image)
                    .showImageForEmptyUri(image)
                    .showImageOnFail(image);
        }

        if (radius >= 0) {
            builder.displayer(new RoundedBitmapDisplayer(Math.round(radius)));
        }

        return builder.build();

    }


    public static DisplayImageOptions getImageOptions(int image) {
        return getImageOptions(image, -1);
    }

    public static DisplayImageOptions DisplayImageOptions(int drawable) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().
                showImageForEmptyUri(drawable).
                showImageOnLoading(drawable).//加载中的图片
                showImageOnFail(drawable).build();
        return options;
    }

    public static String FILE = "^[Ff][Ii][Ll][Ee]://[\\S]*";
    public static String HTTP = "^[Hh][Tt][Tt][Pp]://[\\S]*";
    public static String HTTPS = "^[Hh][Tt][Tt][Pp][Ss]://[\\S]*";
    public static String CONTENT = "^[Cc][Oo][Nn][Tt][Ee][Nn][Tt]://[\\S]*";
    public static String ASSETS = "^[Aa][Ss][Ee][Tt][Ss]://[\\S]*";
    public static String DRAWABLE = "^[Dd][Rr][Aa][Ww][Aa][Bb][Ll][Ee]://[\\S]*";

    public static String NO_TRANSFORMATION = "(" + FILE + ")|("
            + HTTP + ")|("
            + HTTPS + ")|("
            + CONTENT + ")|("
            + ASSETS + ")|("
            + FILE + ")|("
            + DRAWABLE + ")";
}

