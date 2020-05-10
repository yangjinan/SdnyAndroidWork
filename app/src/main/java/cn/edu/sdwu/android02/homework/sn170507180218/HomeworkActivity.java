package cn.edu.sdwu.android02.homework.sn170507180218;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework);

    }
    public void homework1(View view){
        Intent intent=new Intent(this,HomeworkActivity1.class);
        startActivity(intent);
    }
    public void homework2(View view){
        Intent intent=new Intent(this,HomeworkActivity2.class);
        startActivity(intent);
    }
    public void homework3(View view){
        Intent intent=new Intent(this,HomeworkActivity2.class);
        startActivity(intent);
    }
}