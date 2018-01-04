package com.example.dell.fly;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dell.fly.MainActivity;
import com.example.dell.fly.R;

/**
 * Created by dell on 2016/4/23.
 */
public class SecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}