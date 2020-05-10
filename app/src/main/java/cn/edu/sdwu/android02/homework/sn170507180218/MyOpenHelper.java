package cn.edu.sdwu.android02.homework.sn170507180218;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class MyOpenHelper extends SQLiteOpenHelper {
    //private String STUDENT_TB_SQL="create table student(id integer primary key autoincrement,stuname text,stutel text)";
    private String STUDENT1_TB_SQL="create table student1(id integer primary key autoincrement,name text,age text,height text)";
    public MyOpenHelper(Context context){
        //Context context上下文 ，String name数据库名字,
        // CursorFactory factory传入Null, int version数据库的版本
        super(context,"stud1.db",null,1);//创建数据库
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //当打开数据库时，发现数据库并不存在，此时会自动创建数据库，然后调用o nCreate 方法
        //在本方法中完成数据库对象（表，视图，索引等）的创建
        //通过SQLiteDatabase对象，完成SQL的执行
        sqLiteDatabase.execSQL(STUDENT1_TB_SQL);
        Log.i(MyOpenHelper.class.toString(),"onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

