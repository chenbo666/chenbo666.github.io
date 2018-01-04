package com.example.dell.fly;

import java.util.Random;

/**
 * Created by dell on 2016/4/24.
 */
public class Enemy {
    public int x;
    public int y;
    public int width;
    public int height;
    public int visual;
    public int v;
    public int boo;
    public int treasure;
    public Enemy(){
        Random random=new Random();
        y=0;
        width=200;
        height=150;
        visual=0;
        v=20;
        x=random.nextInt(900);
        boo=0;
        treasure=random.nextInt(20);
    }
}
