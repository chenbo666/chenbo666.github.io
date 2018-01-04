package com.example.dell.fly;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Init();
    }

    public void Init() {
        WindowManager wm = this.getWindowManager();
        Display display = wm.getDefaultDisplay();
        MainGame gameView = new MainGame(this, display);
        gameView.setOnTouchListener(new touch());
        gameView.setBackgroundColor(Color.BLACK);
        this.setContentView(gameView);
    }
}
class touch implements View.OnTouchListener{//触摸监听事件
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            MainGame.isdown=true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            MainGame.isdown=false;
        }
        MainGame.Point_x = (int) event.getRawX();
        MainGame.Point_y = (int) event.getRawY();
        return true;
    }
}