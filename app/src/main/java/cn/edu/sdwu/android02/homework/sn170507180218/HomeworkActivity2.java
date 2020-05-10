package cn.edu.sdwu.android02.homework.sn170507180218;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class HomeworkActivity2 extends AppCompatActivity {
    private MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework2);
        myOpenHelper=new MyOpenHelper(this);
        //以可写方式打开数据库（还有只读方式）,如果数据库不存在，自动创建数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        sqLiteDatabase.close();//关闭数据库
    }

    public void insert(View view){
        //以可写方式打开数据库（还有只读方式）,如果数据库不存在，自动创建数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        EditText name1=(EditText)findViewById(R.id.editText1);
        EditText age1=(EditText)findViewById(R.id.editText2);
        EditText height1=(EditText)findViewById(R.id.editText3);
        String name=name1.getText().toString();
        String age=age1.getText().toString();
        String height=height1.getText().toString();
        try{
            //将插入的数据放在ContentValues中
            //事物处理
            sqLiteDatabase.beginTransaction();//开启事物
            ContentValues contentValues=new ContentValues();
            contentValues.put("name", String.valueOf(name));
            contentValues.put("age", String.valueOf(age));
            contentValues.put("height", String.valueOf(height));
            sqLiteDatabase.insert("student1",null,contentValues);
            sqLiteDatabase.setTransactionSuccessful();///所有操作结束后调用本方法，将数据保存在数据库

        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事物
            sqLiteDatabase.close();//关闭数据库
        }

    }

    public void show(View view){
        //以只读方式打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();
        TextView textView=(TextView)findViewById(R.id.ch14_1_tv);

        try{
            //游标类型
            Cursor cursor=sqLiteDatabase.rawQuery("select * from student1",new String[]{});
            //利用循环遍历游标
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String age=cursor.getString(cursor.getColumnIndex("age"));
                String height=cursor.getString(cursor.getColumnIndex("height"));
                textView.append("id:"+id+",name:"+name+",age:"+age+",height:"+height+"\n");
                //textView.setText("id:"+id+",stuname:"+name+",age:"+age+",height:"+height);
                Log.i(HomeworkActivity2.class.toString(),"id:"+id+",name:"+name+",age:"+age+",height:"+height);
            }
            cursor.close();//游标关闭
        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {

            sqLiteDatabase.close();//关闭数据库
        }

    }
    public void clear(View view){
        TextView textView=(TextView)findViewById(R.id.ch14_1_tv);
        textView.setText("");

    }

    public void delete_all(View view){
        //以可写方式打开数据库（还有只读方式）,如果数据库不存在，自动创建数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try{
            //将插入的数据放在ContentValues中
            //事物处理
            sqLiteDatabase.beginTransaction();//开启事物
            sqLiteDatabase.delete("student1","id>?",new String[]{"0"});

            sqLiteDatabase.setTransactionSuccessful();///所有操作结束后调用本方法，将数据保存在数据库

        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事物
            sqLiteDatabase.close();//关闭数据库
        }
    }

    public void select(View view){
        //以只读方式打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();
        TextView textView=(TextView)findViewById(R.id.ch14_1_tv);
        EditText id1=(EditText)findViewById(R.id.editText4);
        String id2=id1.getText().toString();
        try{
            //游标类型
            Cursor cursor=sqLiteDatabase.rawQuery("select * from student1 where id=?",new String[]{id2});
            //利用循环遍历游标
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String age=cursor.getString(cursor.getColumnIndex("age"));
                String height=cursor.getString(cursor.getColumnIndex("height"));
                textView.setText("id:"+id+",name:"+name+",age:"+age+",height:"+height+"\n");
                //textView.setText("id:"+id+",stuname:"+name+",age:"+age+",height:"+height);
                Log.i(HomeworkActivity2.class.toString(),"id:"+id+",name:"+name+",age:"+age+",height:"+height);
            }
            cursor.close();//游标关闭
        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {

            sqLiteDatabase.close();//关闭数据库
        }

    }


    public void modify(View view){
        //以可写方式打开数据库（还有只读方式）,如果数据库不存在，自动创建数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        EditText id1=(EditText)findViewById(R.id.editText4);
        String id2=id1.getText().toString();
        try{
            //将插入的数据放在ContentValues中
            //事物处理
            sqLiteDatabase.beginTransaction();//开启事物

            ContentValues contentValues=new ContentValues();
            contentValues.put("age","20");

            sqLiteDatabase.update("student1",contentValues,"id=?",new String[]{id2});
            sqLiteDatabase.setTransactionSuccessful();///所有操作结束后调用本方法，将数据保存在数据库

        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事物
            sqLiteDatabase.close();//关闭数据库
        }
    }

    public void delete(View view){
        //以可写方式打开数据库（还有只读方式）,如果数据库不存在，自动创建数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        EditText id1=(EditText)findViewById(R.id.editText4);
        String id2=id1.getText().toString();
        try{
            //将插入的数据放在ContentValues中
            //事物处理
            sqLiteDatabase.beginTransaction();//开启事物
            sqLiteDatabase.delete("student1","id=?",new String[]{id2});
            sqLiteDatabase.setTransactionSuccessful();///所有操作结束后调用本方法，将数据保存在数据库

        }catch (Exception e){
            Log.e(HomeworkActivity2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事物
            sqLiteDatabase.close();//关闭数据库
        }
    }

}
