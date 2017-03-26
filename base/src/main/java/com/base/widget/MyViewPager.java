package com.base.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 俞智威
 * on 2015-09-23
 * 09:18
 * Procedure Explain: 能自动滚动并且无限滑动的Viewpager
 */
public class MyViewPager extends RelativeLayout {

    private ViewPager viewPager;
    private PagerAdapter mAdapter;
    private List<View> viewList;//集合
    private ImageView[] imageVernier;//图片数组(包括小圆点)
    private AutoRoll autoRoll;//线程
    private int[] mImageVer;//小圆点的资源文件
    private TextView number;//文字提醒，类似1/3
    private OnItemClickListener onItemClickListener;
    private boolean isTwo;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        viewPager = new ViewPager(context);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addView(viewPager, params);
        autoRoll = new AutoRoll(viewPager);
    }

    /**
     * 获取viewpager实例
     *
     * @return
     */
    public ViewPager getViewPager() {
        return viewPager;
    }

    /**
     * ViewPager自动滑动时，textView变化监听
     */
    private ViewPager.OnPageChangeListener TextOnChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {
            for (int i = 0; i < viewList.size(); i++) {
                number.setText(String.valueOf(position % viewList.size() + 1) + "/" + viewList.size());
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    };

    /**
     * ViewPager自动滑动时，小圆点变化监听
     */
    private ViewPager.OnPageChangeListener onChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {
            for (int i = 0; i < viewList.size(); i++) {
                imageVernier[position % viewList.size()]
                        .setBackgroundResource(mImageVer[0]);
                if (position % viewList.size() != i) {
                    imageVernier[i]
                            .setBackgroundResource(mImageVer[1]);
                }
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    };
    /**
     * 用户滑动时关闭线程，用户操作完成后重新开始线程
     *
     * @param v
     * @param event
     * @return
     */
    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    stopThread();
                    break;
                case MotionEvent.ACTION_UP:
                    if (autoRoll.getAutoTurningTime() != 0 && viewList.size() > 1) {
                        startThread(autoRoll.getAutoTurningTime());
                    }
                    break;
            }
            return false;
        }
    };

    /**
     * viewpager 的setAdapter方法
     */
    private void setAdapter() {
        mAdapter = new AdvPagerAdapter(viewList);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(-1);// 设置缓存页面，当前页面的相邻N各页面都会被缓存
        viewPager.setCurrentItem(viewList.size() * 500);
    }

    /**
     * viewpager 的addOnPageChangeListener(TextView 的监听)
     *
     * @param number
     */
    public void addOnPageChangeListener(TextView number) {

        viewPager.addOnPageChangeListener(TextOnChangeListener);
    }

    /**
     * viewpager 的addOnPageChangeListener(小圆点 的监听)
     */
    public void addOnPageChangeListener() {

        viewPager.addOnPageChangeListener(onChangeListener);
    }

    /**
     * 初始化小图标
     */
    private void initDot(ViewGroup group, int[] imageVer) {
        if (group != null) {
            mImageVer = imageVer;
            if (isTwo) {
                imageVernier = new ImageView[viewList.size() / 2];
            } else
                imageVernier = new ImageView[viewList.size()];

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(17, 17);
            layoutParams.setMargins(5, 0, 5, 0);
            for (int i = 0; i < viewList.size(); i++) {
                ImageView vernier = new ImageView(getContext());
                vernier.setLayoutParams(layoutParams);
                imageVernier[i] = vernier;
                if (i == 0) {
                    imageVernier[i].setBackgroundResource(imageVer[0]);// 默认选中第一张图片
                } else {
                    imageVernier[i].setBackgroundResource(imageVer[1]);
                }
                group.addView(imageVernier[i]);
            }
        }

        setAdapter();
        if (group != null) {
            addOnPageChangeListener();
            setOnTouchListener();
        }
    }

    /**
     * 初始化ADV图片以及TextView,并且调用方法设置viewpager的属性，对外提供的方法(测试时使用)
     *
     * @param number 显示数字
     * @param image  轮播图图片数组
     */
    public void initImage(TextView number, int[] image) {

        this.number = number;

        viewList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int i = 0; i < image.length; i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(image[i]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        if (isTwo) {
            imageVernier = new ImageView[viewList.size() / 2];
        } else
            imageVernier = new ImageView[viewList.size()];

        setAdapter();
        addOnPageChangeListener(number);
        setOnTouchListener();
    }

    /**
     * 初始化ADV图片以及TextView,并且调用方法设置viewpager的属性，对外提供的方法
     *
     * @param number 显示数字
     * @param url    轮播图图片数组
     */
    public void initImage(TextView number, String[] url) {

        this.number = number;

        viewList = new ArrayList<>();
        for (int i = 0; i < url.length; i++) {
            ImageView imageView = new ImageView(getContext());
            ImageLoader.getInstance()
                    .displayImage(url[i], imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int i = 0; i < url.length; i++) {
                ImageView imageView = new ImageView(getContext());
                ImageLoader.getInstance()
                        .displayImage(url[i], imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        if (isTwo) {
            imageVernier = new ImageView[viewList.size() / 2];
        } else
            imageVernier = new ImageView[viewList.size()];

        setAdapter();
        addOnPageChangeListener(number);
        setOnTouchListener();
    }


    /**
     * 初始化ADV图片以及小原点,并且调用方法设置viewpager的属性，对外提供的方法(测试时使用)
     *
     * @param group    放置小圆点的layout布局
     * @param image    轮播图图片数组
     * @param imageVer 小圆点图图片数组
     */
    public void initImage(ViewGroup group, int[] image, int[] imageVer) {

        viewList = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int i = 0; i < image.length; i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(image[i]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        initDot(group, imageVer);
    }

    /**
     * 初始化ADV图片以及小原点,并且调用方法设置viewpager的属性，对外提供的方法,无跳转
     *
     * @param group    放置小圆点的layout布局
     * @param url      轮播图图片数组
     * @param imageVer 小圆点图图片数组
     */
    public void initImage(ViewGroup group, String[] url, int[] imageVer) {

        viewList = new ArrayList<>();
        for (int i = 0; i < url.length; i++) {
            ImageView imageView = new ImageView(getContext());
            ImageLoader.getInstance()
                    .displayImage(url[i], imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int i = 0; i < url.length; i++) {
                ImageView imageView = new ImageView(getContext());
                ImageLoader.getInstance()
                        .displayImage(url[i], imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        initDot(group, imageVer);
    }

    /**
     * 触摸时关闭自动滑动的方法
     */
    public void setOnTouchListener() {
        viewPager.setOnTouchListener(onTouchListener);
    }

    /**
     * 监听item点击
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * 开始线程,要是传的值为0的话将不会开启线程
     *
     * @param autoTurningTime
     */
    public void startThread(long autoTurningTime) {
        if (autoTurningTime != 0 && viewList.size() > 1) {
            autoRoll.start(autoTurningTime);
            autoRoll.setAutoTurningTime(autoTurningTime);
        } else
            autoRoll.setIsLooping(false);
    }

    /**
     * 关闭线程
     */
    public void stopThread() {
        if (autoRoll.isLooping()) {
            autoRoll.stop();
        }
    }

    /**
     * PagerAdapter类
     */
    public class AdvPagerAdapter extends PagerAdapter {

        private List<View> viewList;

        /**
         * 测试时使用
         *
         * @param viewList
         */
        public AdvPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * 该方法将返回所包含的 Item总个数。为了实现一种循环滚动的效果，返回了基本整型的最大值，这样就会创建很多的Item,
         * 其实这并非是真正的无限循环。
         */
        @Override
        public int getCount() {
            //实现循环 ,页面数设为最大值保证滑动不到最后

            return Integer.MAX_VALUE;
        }

        /**
         * 创建一个view，
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            //实现循环
            View view = viewList.get(position % viewList.size());
            if (view.getParent() != null) {
                container.removeView(view);
            }

            //add listeners here if necessary
            if (onItemClickListener != null) view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTwo)
                        onItemClickListener.onItemClick(viewPager.getCurrentItem() % viewList.size() < 2 ?
                                viewPager.getCurrentItem() % viewList.size() : viewPager.getCurrentItem() % viewList.size() % 2);
                    else
                        onItemClickListener.onItemClick(viewPager.getCurrentItem() % viewList.size());
                }
            });

            container.addView(view);
            return view;
        }

        /**
         * 判断出去的view是否等于进来的view 如果为true直接复用
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来，就是position，
         * 因为mImageViewList只有五条数据，而position将会取到很大的值，
         * 所以使用取余数的方法来获取每一条数据项。
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            //实现循环
//              container.removeView(viewList.get(position%viewList.size()));
        }
    }

    /**
     * 自动滑动的线程类
     */
    public static class AutoRoll implements Runnable {

        private ViewPager viewPager;
        private Handler handler;
        private boolean isLooping;
        private long autoTurningTime;

        public boolean isLooping() {
            return isLooping;
        }

        public void setIsLooping(boolean isLooping) {
            this.isLooping = isLooping;
        }

        public AutoRoll(ViewPager viewPager) {
            this.viewPager = viewPager;
            handler = new Handler();
        }

        public long getAutoTurningTime() {
            return autoTurningTime;
        }

        public void setAutoTurningTime(long autoTurningTime) {
            this.autoTurningTime = autoTurningTime;
        }

        private void start(long autoTurningTime) {
            //如果是正在翻页的话先停掉
            if (isLooping) {
                stop();
            }
            //设置可以翻页并开启翻页
            isLooping = true;
            handler.postDelayed(this, autoTurningTime);
        }

        private void stop() {
            isLooping = false;
            handler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (isLooping) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handler.postDelayed(this, autoTurningTime);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
