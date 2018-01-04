package com.example.dell.fly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by dell on 2016/4/24.
 */
public class MainGame extends View implements Runnable{
    public static int Screen_w;
    public static int Screen_h;
    public static Paint paint;
    public static int Point_x=0;
    public static int Point_y=0;
    public static boolean isdown;
    public int ide;
    public int numOfDestroy=0;
    public int ideb=1;
    public int numOfEnemy;
    public int i;
    public int j;
    public int blueBullet=0;
    public int doubleBullet=0;
    public int idOfTreasure;
    public boolean isWin=false;
    public int temp=1;
    public int id=0;
    public int background;
    Random rand;
    public boolean isLose=false;
    Plane plane;
    Boss boss;
    Thread thread;
    Treasure[] treasure;
    PBullet[] pb;
    EBullet[] eb;
    Enemy[] enemy;
    public MainGame(Context context, Display display) {//初始化
        super(context);
        Screen_w = display.getWidth();
        Screen_h = display.getHeight();
        paint = new Paint();
        ide=1;
        idOfTreasure=0;
        numOfEnemy=0;
        plane=new Plane();//新建飞机
        pb=new PBullet[50];//新建子弹
        eb=new EBullet[160];
        enemy=new Enemy[60];//新建敌机
        treasure=new Treasure[70];
        boss=new Boss();//新建Boss
        for(i=0;i<=49;i++){
            pb[i]=new PBullet();
            enemy[i]=new Enemy();
        }
        //新建宝物
        for(i=0;i<=65;i++){
            treasure[i]=new Treasure();
        }
        //新建敌机子弹
        for(int i=0;i<=149;i++) {
            eb[i] = new EBullet();
        }
        rand=new Random();
        background=0;
        this.thread = new Thread(this);
        this.thread.start();
    }
    public void run() {
        //飞机活动
        while ((!isLose)&&(!isWin)) {
            background+=10;//背景移动
            if(background>=2000){
                background=0;
            }
            //产生敌机
            if(boss.visual==0){//如果BOSS不出现
            if(enemy[ide].visual==0) {//如果不存在飞机
                //如果是第一个
                if (ide == 1) {
                    enemy[ide].visual = 1;
                    enemy[ide].y = 0;
                    ide++;
                    numOfEnemy++;
                } else if (ide > 0 && ide <= 39) {
                    if (enemy[ide - 1].y >= 300) {//如果走出了100
                        enemy[ide].visual = 1;
                        enemy[ide].y = 0;
                        ide++;
                        numOfEnemy++;
                    }
                }
            }
                if(ide==40){
                    enemy[ide].visual = 1;
                    enemy[ide].y=0;
                    ide=1;
                    enemy[ide].y=0;
                    numOfEnemy++;
                }
            }
            //敌机移动
            for(i=1;i<=40;i++) {
                    enemy[i].y+=enemy[i].v;
                if (enemy[i].visual == 1) {
                    if(enemy[i].y>=Screen_h){
                        enemy[i].visual=0;//敌机消失
                    }
                }
            }
            //敌机产生子弹
            for(i=1;i<=40;i++){
                if(enemy[i].visual==1){
                    if(enemy[i].y>=100&&enemy[i].y<=110){
                        //if(eb[ideb].visual==0) {
                            eb[ideb].visual = 1;
                            eb[ideb].y = enemy[i].y+enemy[i].height;
                            eb[ideb].x = enemy[i].x+enemy[i].width/2;
                            ideb++;
                       // }
                    }
                    if(ideb==145){
                        ideb=1;
                    }
                }
            }
            //产生BOSS
            if(numOfEnemy==60){
                boss.visual=2;
            }
            //boss移动
            if(boss.visual!=0){
                boss.y+=boss.v;
                if(boss.y>=100){
                    boss.visual=1;
                }
                if(boss.visual==1) {
                    boss.x+=boss.vx;
                    if (boss.y >= 900 || boss.y+boss.v < 100) {
                        boss.v = -boss.v;
                    }
                    if(boss.x+boss.width+boss.vx>Screen_w||boss.x<=50){
                        boss.vx=-boss.vx;
                    }
                }
            }
            //产生boss子弹
                if(boss.visual==1){
                    if(ideb>=140){
                        ideb=1;
                    }
                    for(i=0;i<=10;i++) {
                        if (boss.y >= 100+i*40 && boss.y <= 105+i*40&&boss.v>0) {
                            //if(eb[ideb].visual==0) {
                            /*eb[ideb].visual = 1;
                            eb[ideb].y = boss.y + boss.height / 2;
                            eb[ideb].x = boss.x + boss.width / 2;
                            ideb++;*/
                            eb[ideb].visual = 1;
                            eb[ideb].y = boss.y + boss.height / 2;
                            eb[ideb].x = boss.x + boss.width / 2 + 320;
                            ideb++;
                            eb[ideb].visual = 1;
                            eb[ideb].y = boss.y + boss.height / 2;
                            eb[ideb].x = boss.x + boss.width / 2 - 320;
                            ideb++;
                            // }
                        }
                        if(boss.y >= 140+i*40 && boss.y <= 145+i*40&&boss.v<0){
                            eb[ideb].visual = 1;
                            eb[ideb].y = boss.y + boss.height / 2;
                            eb[ideb].x = boss.x + boss.width / 2 + 25;
                            ideb++;
                            eb[ideb].visual = 1;
                            eb[ideb].y = boss.y + boss.height / 2;
                            eb[ideb].x = boss.x + boss.width / 2 - 25;
                            ideb++;
                        }
                    }
                }
            //敌机子弹运动
            for(i=1;i<=145;i++){
                if(eb[i].visual==1){
                    eb[i].y+=eb[i].v;
                }
                if(eb[i].y>=Screen_h){
                    eb[i].visual=0;
                }
            }
            //按下之后
            if(isdown==true) {
                temp++;
                if(temp==5){
                if(pb[id].visual==0) {
                    if(doubleBullet>=1){
                        pb[id].visual = 1;
                        pb[id].x = plane.x + plane.width / 2+20;
                        pb[id].y = plane.y;
                        pb[id].v = 40;
                        if (id <= 48) {
                            id++;
                        } else {
                            id = 0;
                        }
                        pb[id].visual = 1;
                        pb[id].x = plane.x + plane.width / 2-20;
                        pb[id].y = plane.y;
                        pb[id].v = 40;
                        if (id <= 48) {
                            id++;
                        } else {
                            id = 0;
                        }
                        if(doubleBullet>=2) {
                            pb[id].visual = 1;
                            pb[id].x = plane.x + plane.width / 2;
                            pb[id].y = plane.y;
                            pb[id].v = 40;
                            if (id <= 48) {
                                id++;
                            } else {
                                id = 0;
                            }
                        }
                    }
                    if(doubleBullet==0) {
                        pb[id].visual = 1;
                        pb[id].x = plane.x + plane.width / 2;
                        pb[id].y = plane.y;
                        pb[id].v = 40;
                        if (id <= 48) {
                            id++;
                        } else {
                            id = 0;
                        }
                    }
                        temp = 1;
                }
                }
                //触摸移动飞机
                if (Point_x >= plane.x + plane.width / 2) {
                    plane.x += (Point_x-plane.x-plane.width / 2)/4;
                } else {
                    plane.x -= (-Point_x+plane.x+plane.width / 2)/4;
                }
                if (Point_y >= plane.y + plane.height / 2) {
                    plane.y += (Point_y-plane.y-plane.height / 2)/4;
                } else {
                    plane.y -= (-Point_y+plane.y+plane.height / 2)/4;;
                }
                //边界检测
                if(plane.x<=0){
                    plane.x=0;
                }
                if(plane.x+plane.width>=Screen_w){
                    plane.x=Screen_w-plane.width;
                }
            }
            for(i=0;i<=49;i++) {//子弹移动
                if (pb[i].visual == 1) {
                    pb[i].y -= pb[i].v;
                    if(pb[i].y<=30){//如果子弹超过边界
                        pb[i].visual=0;
                    }
                }
            }
            //与BOSS相撞
            if(plane.x+plane.width-10>=boss.x&&plane.x<=boss.x+boss.width){
                if(plane.y<=boss.y+boss.height&&plane.y+plane.height>=boss.y){
                    isLose=true;

                }
            }
            //子弹打到敌机
            for(i=0;i<=49;i++) {
                for(j=1;j<=40;j++){
                    if(pb[i].visual==1&&enemy[j].visual==1){
                if(pb[i].x>=enemy[j].x&&pb[i].x<=enemy[j].x+enemy[j].width) {
                    if (pb[i].y <= enemy[j].y + enemy[j].height&&pb[i].y>=enemy[j].y) {
                        enemy[j].visual = 0;
                        pb[i].visual = 0;
                        enemy[j].boo=3;
                        numOfDestroy++;
                        //产生宝物
                        if(enemy[j].treasure<=0){
                            treasure[idOfTreasure].visual=1;
                            treasure[idOfTreasure].x=enemy[j].x;
                            treasure[idOfTreasure].y=enemy[j].y;
                            treasure[idOfTreasure].varible=1;
                            idOfTreasure++;
                        }else if(enemy[j].treasure<=2){
                            treasure[idOfTreasure].visual=1;
                            treasure[idOfTreasure].x=enemy[j].x;
                            treasure[idOfTreasure].y=enemy[j].y;
                            treasure[idOfTreasure].varible=2;
                            idOfTreasure++;
                        }
                    }
                }
                }
                }
            }
            //宝物移动
            for(i=0;i<=65;i++){
            if(treasure[i].visual==1) {
                treasure[i].y+=treasure[i].v;
                //吃到宝物
                if(treasure[i].x+treasure[i].width>=plane.x&&treasure[i].x<=plane.x+plane.width){
                    if(treasure[i].y+treasure[i].height>=plane.y&&treasure[i].y<=plane.y+plane.height){
                        treasure[i].visual=0;
                        if(treasure[i].varible==1){
                            blueBullet=1;
                        }
                        if(treasure[i].varible==2){
                            doubleBullet++;
                        }
                    }
                }
            }
            }
            //子弹打到BOSS
            if(boss.visual!=0&&(boss.life>=4)) {
                for (i = 0; i <= 49; i++) {
                    if(pb[i].visual==1){
                        if(pb[i].x>=boss.x&&pb[i].x<=boss.x+boss.width) {
                            if (pb[i].y <= boss.y + boss.height && pb[i].y >= boss.y) {
                                if(blueBullet==0) {
                                    boss.life--;
                                }else{
                                    boss.life-=2;
                                }
                                pb[i].visual = 0;
                                pb[i].boo=2;
                            }
                        }
                    }
                }
            }
            //通关条件
            if(boss.life==0){
                isWin=true;
            }
            //飞机中子弹
            for(i=1;i<=145;i++){
                if(eb[i].visual==1){
                    if(eb[i].x-5>=plane.x&&eb[i].x+5<=plane.x+plane.width){
                        if (eb[i].y-5>=plane.y&&eb[i].y+5<=plane.y+plane.height){
                            plane.life--;
                            {//死亡后重置子弹
                                blueBullet=0;
                                doubleBullet=0;
                            }
                            eb[i].visual=0;
                            if(plane.life==0){
                                isLose=true;
                            }
                        }
                    }
                }
            }

            //飞机相撞
            for(j=1;j<=40;j++){
                if(enemy[j].visual==1) {
                    if (enemy[j].x+enemy[j].width-5>= plane.x && enemy[j].x+5<= plane.x + plane.width) {
                        if (enemy[j].y+enemy[j].height>= plane.y&&enemy[j].y<=plane.y+plane.height) {
                            isLose = true;
                        }
                    }
                }
            }
            this.postInvalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    Bitmap ENEMY = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.enemy)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.enemy)).getBitmap() : null;
    Bitmap BOO = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.boo)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.boo)).getBitmap() : null;
    Bitmap BACK = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.back)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.back)).getBitmap() : null;
    Bitmap TREA1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.trea1)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.trea1)).getBitmap() : null;
    Bitmap TREA2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.trea2)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.trea2)).getBitmap() : null;
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        //画背景
        canvas.drawBitmap(BACK,null,new Rect(0,background-2000,1300,background+2000),paint);
        //显示生命值
        paint.setColor(Color.YELLOW);
        paint.setTextSize(40);
        canvas.drawText("HP:",0,Screen_h-50,paint);
        canvas.drawRect(80,Screen_h-100,80+plane.life*80,Screen_h-50,paint);
        //画己方飞机
        if (((BitmapDrawable) this.getResources().getDrawable(R.drawable.plane)) != null) {
            Bitmap HERO = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.plane)) != null ? ((BitmapDrawable) this.getResources().getDrawable(R.drawable.plane)).getBitmap() : null;
            canvas.drawBitmap(HERO,null,new Rect(plane.x,plane.y,plane.x+plane.width,plane.y+plane.height),paint);
        }
        //画己方子弹
        paint.setColor(Color.YELLOW);
        for(j=0;j<=49;j++) {
            if(pb[j].visual==1) {
                if(blueBullet==0){
                    paint.setColor(Color.YELLOW);
                }else{
                    paint.setColor(Color.rgb(0, 255, 255));
                }
                canvas.drawRect(pb[j].x, pb[j].y, pb[j].x + pb[j].width, pb[j].y + pb[j].height, paint);
            }
            if(pb[j].boo==2){
                canvas.drawBitmap(BOO,null,new Rect(pb[j].x-20,pb[j].y-20,pb[j].x + pb[j].width+20,pb[j].y + pb[j].height+20),paint);
                pb[j].boo--;
            }
            if(pb[j].boo==1){
                canvas.drawBitmap(BOO,null,new Rect(pb[j].x-40,pb[j].y-40,pb[j].x + pb[j].width+40,pb[j].y + pb[j].height+40),paint);
                pb[j].boo--;
            }
        }
        //画宝物
        for(i=0;i<=65;i++){
            if(treasure[i].visual==1){
                if(treasure[i].varible==1){
                    canvas.drawBitmap(TREA1,null,new Rect(treasure[i].x,treasure[i].y,treasure[i].x+treasure[i].width,treasure[i].y+treasure[i].height),paint);
                }else if(treasure[i].varible==2) {
                    canvas.drawBitmap(TREA2,null,new Rect(treasure[i].x,treasure[i].y,treasure[i].x+treasure[i].width,treasure[i].y+treasure[i].height),paint);
                }
            }
        }
        //画敌机
       for(j=0;j<=40;j++){
            if(enemy[j].visual==1){
                canvas.drawBitmap(ENEMY,null,new Rect(enemy[j].x,enemy[j].y,enemy[j].x+enemy[j].width,enemy[j].y+enemy[j].height),paint);
            }
           if(enemy[j].boo!=0){
               switch (enemy[j].boo){
                   case 3:
                       canvas.drawBitmap(BOO,null,new Rect(enemy[j].x+20,enemy[j].y+20,enemy[j].x+enemy[j].width-20,enemy[j].y+enemy[j].height-20),paint);
                       enemy[j].boo--;
                       break;
                   case 2:
                       canvas.drawBitmap(BOO,null,new Rect(enemy[j].x+10,enemy[j].y+10,enemy[j].x+enemy[j].width-10,enemy[j].y+enemy[j].height-10),paint);
                       enemy[j].boo--;
                       break;
                   case 1:
                       canvas.drawBitmap(BOO,null,new Rect(enemy[j].x,enemy[j].y,enemy[j].x+enemy[j].width,enemy[j].y+enemy[j].height),paint);
                       enemy[j].boo--;
                       break;

               }
           }
        }
        //画敌机子弹
        for(i=1;i<=145;i++){
            if(eb[i].visual==1){
                paint.setColor(Color.RED);
                canvas.drawCircle(eb[i].x,eb[i].y,12,paint);
            }
        }
        //画BOSS
        if(boss.visual!=0){
            if(boss.life>=4) {
                canvas.drawBitmap(ENEMY, null, new Rect(boss.x, boss.y, boss.x + boss.width, boss.y + boss.height), paint);
            }else {
                switch (boss.life) {
                    case 3: canvas.drawBitmap(BOO,null,new Rect(boss.x,boss.y,boss.x+boss.width,boss.y+boss.height),paint);
                        boss.life--;
                        break;
                    case 2: canvas.drawBitmap(BOO,null,new Rect(boss.x,boss.y,boss.x+boss.width,boss.y+boss.height),paint);
                        boss.life--;
                        break;
                    case 1: canvas.drawBitmap(BOO,null,new Rect(boss.x,boss.y,boss.x+boss.width,boss.y+boss.height),paint);
                        boss.life--;
                        break;
                }
            }
        }
        if(isLose){//如果失败
            invalidate();
            paint.setColor(Color.RED);
            paint.setTextSize(90);
            canvas.drawText("Game Over",300,800,paint);
            canvas.drawBitmap(BOO,null,new Rect(plane.x,plane.y,plane.x+plane.width,plane.y+plane.height),paint);

        }
        if(isWin){//如果胜利
            invalidate();
            paint.setColor(Color.YELLOW);
            paint.setTextSize(90);
            canvas.drawText("You Win!",300,800,paint);
            paint.setColor(Color.RED);
            paint.setTextSize(70);
            canvas.drawText("最终得分:"+numOfDestroy,300,900,paint);

        }

}
}
