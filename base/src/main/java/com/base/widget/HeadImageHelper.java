package com.base.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bm.base.R;

import java.io.File;

/**
 * Created by 俞智威
 * on 2015-12-24.
 * 18:28
 * Procedure Explain:头像帮助
 */
public class HeadImageHelper implements View.OnClickListener {

    public static final String TAG = "HeadImageHelper";

    /**
     * 相册返回码
     */
    public static final int IMAGE_REQUEST_CODE = 20;

    /**
     * 相机返回码
     */
    public static final int CAMERA_REQUEST_CODE = 21;

    /**
     * 裁图返回码
     */
    public static final int RESIZE_REQUEST_CODE = 22;

    /**
     * 头像名
     */
    private String IMAGE_FILE_NAME = "header";


    private View popView;

    private Activity activity;

    private ImageView headView;

    private OnResultBitmap onResultBitmap;

    private Bitmap bitmap;

    public HeadImageHelper(Activity activity) {
        this.activity = activity;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setHeadView(ImageView headView) {

        if (headView == null) {
            this.headView.setOnClickListener(null);
        } else {
            headView.setOnClickListener(new ShowOnClickListener());
        }

        this.headView = headView;

    }

    public void showSelectView() {

        popView = new PopBottomBuilder()
                .setLayout(R.layout.pop_head)
                .setLayoutParams(new RelativeLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                .commit(activity);

        popView.setOnClickListener(new CancelOnClickListener(popView));
        popView.findViewById(R.id.pop_head_button1).setOnClickListener(this);
        popView.findViewById(R.id.pop_head_button2).setOnClickListener(this);
        popView.findViewById(R.id.pop_head_button3).setOnClickListener(this);


    }

    public Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    public void setOnResultBitmap(OnResultBitmap onResultBitmap) {
        this.onResultBitmap = onResultBitmap;
    }

    public void dismiss() {

        ViewGroup viewGroup = (ViewGroup) popView.getParent();
        if (viewGroup != null)
            viewGroup.removeView(popView);

    }


    public void resizeImage(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, RESIZE_REQUEST_CODE);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.pop_head_button1) {  //相册

            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
            galleryIntent.setType("image/*");
            activity.startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);

        } else if (v.getId() == R.id.pop_head_button2) {  //拍照

            Uri uri = getImageUri();
            File file = new File(uri.getPath());
            file.delete();
            Intent cameraIntent = new Intent(
                    "android.media.action.IMAGE_CAPTURE");
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            activity.startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

        } else if (v.getId() == R.id.pop_head_button3) {  //取消

            dismiss();

        }
    }


    public void OnActivityResult(int requestCode, int resultCode, Intent data) {

        if (headView == null) {
            return;
        }


        try {
            if (requestCode == HeadImageHelper.IMAGE_REQUEST_CODE) {   //相册回调
                if (data == null) return;
                resizeImage(data.getData());
            } else if (requestCode == HeadImageHelper.CAMERA_REQUEST_CODE) {   //拍照回调
                Uri uri = getImageUri();
                if (new File(uri.getPath()).exists())
                    resizeImage(uri);
            } else if (requestCode == HeadImageHelper.RESIZE_REQUEST_CODE) {   //切图回调
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    bitmap = photo;

                    if (onResultBitmap == null) {
                        headView.setImageBitmap(photo);
                    } else {
                        onResultBitmap.setBitmap(photo, headView);
                    }

                }
                dismiss();

            }
        } catch (Exception e) {

        }
    }


    class ShowOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            showSelectView();
        }
    }

    public static interface OnResultBitmap {
        public void setBitmap(Bitmap bitmap, ImageView imageView);
    }

    public class CancelOnClickListener implements View.OnClickListener {

        public static final String TAG = "CancelOnClickListener";

        private View view;

        public CancelOnClickListener(View view) {
            this.view = view;
        }

        @Override
        public void onClick(View v) {

            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);

        }
    }


}
