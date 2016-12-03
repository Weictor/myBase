package com.base.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create by 俞智威
 * on 2016-11-08
 * 16:10
 * Procedure Explain: 运用系统的数据库操作，想要添加的数据表只需要继承该类，
 *     子类重写注释掉的代码并给TABLE_NAME与CREATE_TABLE赋值即可
 */

public class BaseDBHelper extends SQLiteOpenHelper {

//    public final static int VERSION = 1;
//    public final static String DB_NAME = "phones.db";
//    public final static String TABLE_NAME = "phone";
//    public final static String CREATE_TABLE = "create table phone(_id integer primary key autoincrement, name text, sex text, number text, desc text)";
    public SQLiteDatabase db;
    public String TABLE_NAME = "";
    public String CREATE_TABLE = "";

//    /**
//     * 数据库的构造函数，传递三个参数的
//     *
//     * @param context
//     * @param name
//     * @param version
//     */
//    public BaseDBHelper(Context context, String name, int version) {
//        this(context, name, null, version);
//    }
//
//    /**
//     * 数据库的构造函数，传递一个参数的， 数据库名字和版本号都写死了
//     *
//     * @param context
//     */
//    public BaseDBHelper(Context context) {
//        this(context, DB_NAME, null, VERSION);
//    }

    /**
     * SQLiteOpenHelper子类必须要的一个构造函数
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public BaseDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //必须通过super 调用父类的构造函数
        super(context, name, factory, version);
    }

    /**
     * 回调函数，第一次创建时才会调用此函数，创建一个数据库
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        System.out.println("Create Database");
        if (!"".equals(CREATE_TABLE)) {
            db.execSQL(CREATE_TABLE);
        }

    }

    /**
     * 回调函数，当你构造DBHelper的传递的Version与之前的Version调用此函数
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("update Database");
    }

    /**
     * 插入方法
     *
     * @param values
     */
    public void insert(ContentValues values) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入数据库中
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * 查询方法
     *
     * @return
     */
    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();
        //获取Cursor
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        return c;
    }

    /**
     * 根据唯一标识_id  来删除数据
     *
     * @param id
     */
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
    }

    /**
     * 更新数据库的内容
     *
     * @param values 修改的内容
     * @param id 根据id修改
     */
    public void update(ContentValues values, int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(id)});
    }

    /**
     * 更新数据库的内容
     * @param values 修改的内容
     * @param whereClause 查询条件 ，为表中的内容的名字
     * @param whereArgs 条件值
     */
    public void update(ContentValues values, String whereClause, String[]whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (db != null) {
            db.close();
        }
    }

}