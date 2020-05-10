package cn.edu.sdwu.android02.homework.sn170507180218;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;

public class HomeworkActivity1 extends AppCompatActivity {
    private ServiceConnection serviceConnection;
    private boolean bound;
    private MediaService mediaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework1);
        bound = false;
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                bound = true;
                MediaService.MyBinder myBinder = (MediaService.MyBinder) iBinder;
                mediaService = myBinder.getMediaService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                bound = false;
            }
        };
    }

    public void ch12_2_start(View view) {
        startServiceClick(view);
    }

    public void ch12_2_pause(View view) {
        startServiceClick(view);
    }

    public void ch12_2_stop(View view) {
        startServiceClick(view);
    }

    public void ch12_2_stopservice(View view) {
        startServiceClick(view);
    }

    public void startServiceClick(View view) {
        //使用本方法 统一处理用户的点击 (启动方式的按键)
        int id = view.getId();
        Intent intent = new Intent(this, MediaService.class);
        switch (id) {
            case R.id.ch12_2_start:
                intent.putExtra("PlayerState", "START");
                break;
            case R.id.ch12_2_pause:
                intent.putExtra("PlayerState", "PAUSE");
                break;
            case R.id.ch12_2_stop:
                intent.putExtra("PlayerState", "STOP");
                break;
            case R.id.ch12_2_stopservice:
                intent.putExtra("PlayerState", "STOPSERVICE");
                break;
        }
        startService(intent);
    }

    public void bindClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ch12_2_bind:
                Intent intent = new Intent(this, MediaService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.ch12_2_ubind:
                unbindService(serviceConnection);
                bound = false;
                break;
            case R.id.ch12_2_bindstart:
                if (bound) {
                    mediaService.start();
                }
                break;
            case R.id.ch12_2_bindpause:
                if (bound) {
                    mediaService.pause();
                }
                break;
            case R.id.ch12_2_bindstop:
                if (bound) {
                    mediaService.stop();
                }
                break;
        }
    }
}

