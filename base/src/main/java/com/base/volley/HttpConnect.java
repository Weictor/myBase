package com.base.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by 俞智威
 * on 2016-02-13.
 * 09:23
 * Procedure Explain:
 */
public class HttpConnect {

    public static final String TAG = "HttpConnect";

    private static HttpConnect httpConnect;

    private RequestQueue requestQueue;

    public static void init(Context context){
        httpConnect = new HttpConnect(context);
    }

    private HttpConnect(Context context){

        requestQueue =  Volley.newRequestQueue(context.getApplicationContext());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否添加到内存缓存中
                .cacheOnDisk(true)//是否添加到硬盘缓存中
                .considerExifParams(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(options)
//                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

    }


    public void add(Request request){
        requestQueue.add(request);

    }


    public void cancelAll(Object TAG){
        requestQueue.cancelAll(TAG);
    }




    public static synchronized HttpConnect getInstance() {
        return httpConnect;
    }


}
