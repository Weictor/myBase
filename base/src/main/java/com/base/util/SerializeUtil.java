package com.base.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by 俞智威
 * on 2015-11-09.
 * 09:56
 * Procedure Explain:序列化本地保存工具类（内部存储）使用前自行判断内部存储空间大小问题
 */
public class SerializeUtil {

    public static final String TAG = "SerializeUtil";


    static Object readObjectBase(Context context, String name) throws IOException, ClassNotFoundException {

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {

            fis = context.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();

        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    LogCat.e(TAG, "关闭流", e);
                }

            if (ois != null)
                try {
                    ois.close();
                } catch (IOException e) {
                    LogCat.e(TAG, "关闭流", e);
                }
        }
    }


    /**
     * 读取序列化对象
     *
     * @param context 上下文环境
     * @param name    存储名字
     * @return 返回对象   为null则没有这个序列化保存对象
     */
    public static Object readObject(Context context, String name) {

        Object object = null;

        try {
            object = readObjectBase(context, name);
        } catch (FileNotFoundException e) {
            LogCat.e(TAG, "文件没有找到", e);
        } catch (StreamCorruptedException e) {
            LogCat.e(TAG, "IO流异常", e);
        } catch (IOException e) {
            LogCat.e(TAG, "IO流异常", e);
        } catch (ClassNotFoundException e) {
            LogCat.e(TAG, "没有这个对象", e);
        }

        return object;

    }

    /**
     * 储存序列化对象
     *
     * @param context 上下文环境
     * @param name    存储名字
     * @param object  要保存序列化对象
     */
    public static void writeObject(Context context, String name, Object object) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void writeObject(Context context, Object object) {
        writeObject(context, object.getClass().getName(), object);
    }


}
