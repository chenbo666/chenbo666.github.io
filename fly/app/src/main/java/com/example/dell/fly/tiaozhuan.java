
package com.example.dell.fly;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bobo on 2018/1/4.
 */

public class tiaozhuan extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.sp);
        run();
    }
    void run(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(tiaozhuan.this,MainActivity.class);
                startActivity(intent);
                tiaozhuan.this.finish();
            }
        },5000);
    }
}
