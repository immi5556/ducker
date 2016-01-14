package com.multiview.immi.mutipleview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class VulMulActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    ImageView iv1 = null;
    int swid, shgt, cnt = 0, score = 0;
    public static int x;
    public static int y;
    Random r = new Random();
    Timer timer;
    TimerTask timerTask;
    TextView tv1 = null;
    final Handler handler = new Handler();
    ImageView it1, it2, it3, it4, it5, it6;
    Rect rc1 = new Rect();
    Rect rc2 = new Rect();
    Rect rc3 = new Rect();
    Rect rc4 = new Rect(); // duck
    Rect rc5 = new Rect();
    Rect rc6 = new Rect();
    Rect rc7 = new Rect();
    Animation fadeinAnimation;
    Animation fadeoutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_vul_mul);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        swid = size.x;
        shgt = size.y;
        x = 0;
        y = 0;
        FrameLayout rl = (FrameLayout)findViewById(R.id.Board1);
        ViewGroup.MarginLayoutParams mlp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //mlp.setMargins(x, y, 200, 200);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(140, 140);
        iv1 = new ImageView(this);
        iv1.setLayoutParams(parms);
        iv1.setImageResource(R.drawable.duck);
        iv1.setX(x);
        iv1.setY(y);
        rl.addView(iv1);

        it1 = (ImageView)findViewById(R.id.it1);
        it2 = (ImageView)findViewById(R.id.it2);
        it3 = (ImageView)findViewById(R.id.it3);
        it4 = (ImageView)findViewById(R.id.it4);
        it5 = (ImageView)findViewById(R.id.it5);
        it6 = (ImageView)findViewById(R.id.it6);
        //tv1 = (TextView)findViewById(R.id.tv1);

        fadeinAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeoutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vul_mul, menu);
        tv1 = new TextView(this);
        tv1.setText(getString(R.string.scoringtv) + "  ");
        tv1.setPadding(5, 0, 5, 0);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextSize(14);
        menu.add(0, 0, 1, R.string.scoringtv).setActionView(tv1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if ((x - (int) event.values[0]) >= 0 && (x - (int) event.values[0]) < (swid-150)){
                x -= (int) event.values[0];
            }
            if ((y + (int) event.values[1]) >= 0 && (y + (int) event.values[1]) < (shgt - 280)) {
                y += (int) event.values[1];
            }
            //Log.d("Log App", "x: " + x + ", Y Val : " + y);
        }

        if (iv1 != null){
            iv1.setX(x);
            iv1.setY(y);
            //iv1.getDrawingRect(rc4);
            int[] loc = new int[2];
            iv1.getLocationInWindow(loc);
            rc4 = new Rect(loc[0], loc[1],
                    loc[0] + iv1.getWidth(), loc[1] + iv1.getHeight());
            if (Rect.intersects(rc4, rc1) && String.valueOf(it1.getTag()) == "full") {
                it1.setImageResource(R.drawable.thief_1);
                it1.setTag("crack");
                it1.startAnimation(fadeinAnimation);
                //it1.setVisibility(View.INVISIBLE);
                score = score + 10;
            }
            if (Rect.intersects(rc4, rc2) && String.valueOf(it2.getTag()) == "full") {
                it2.setImageResource(R.drawable.queen_1);
                it2.setTag("crack");
                it2.startAnimation(fadeinAnimation);
                //it2.setVisibility(View.INVISIBLE);
                score = score + 15;
            }
            if (Rect.intersects(rc4, rc3) && String.valueOf(it3.getTag()) == "full") {
                it3.setImageResource(R.drawable.king_1);
                it3.setTag("crack");
                it3.startAnimation(fadeinAnimation);
                //it3.setVisibility(View.INVISIBLE);
                score = score + 20;
            }
            if (Rect.intersects(rc4, rc5) && String.valueOf(it4.getTag()) == "full") {
                it4.setImageResource(R.drawable.prince_1);
                it4.setTag("crack");
                it4.startAnimation(fadeinAnimation);
                //it4.setVisibility(View.INVISIBLE);
                score = score + 15;
            }
            if (Rect.intersects(rc4, rc6) && String.valueOf(it5.getTag()) == "full") {
                it5.setImageResource(R.drawable.princess_1);
                it5.setTag("crack");
                it5.startAnimation(fadeinAnimation);
                //it5.setVisibility(View.INVISIBLE);
                score = score + 10;
            }
            if (Rect.intersects(rc4, rc7) && String.valueOf(it6.getTag()) == "full") {
                it6.setImageResource(R.drawable.warrior_1);
                it6.setTag("crack");
                it6.startAnimation(fadeinAnimation);
                //it6.setVisibility(View.INVISIBLE);
                score = score + 10;
            }
            if (tv1 != null){
                tv1.setText(String.valueOf(score));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000, 800);
    }
    public void stoptimertask(View v) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (cnt > 6){
                            cnt = 0;
                        }
                        cnt = cnt + 1;
                        int[] loc = new int[2];
                        //Log.d("Log App", "timeer at");
                        int x1 = 0;
                        int y1 = 0;
                        if(it1 != null && cnt == 1){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it1.setImageResource(R.drawable.thief);
                            it1.setTag("full");
                            //it1.setVisibility(View.VISIBLE);
                            it1.setX(x1);
                            it1.setY(y1);
                            it1.getLocationInWindow(loc);
                            it1.startAnimation(fadeoutAnimation);
                            rc1 = new Rect(loc[0], loc[1],
                                    loc[0] + it1.getWidth(), loc[1] + it1.getHeight());
                            //it1.getDrawingRect(rc1);
                            //Log.d("Log App", "1 - X1: " + x1 + " Y1: " + y1);
                        }
                        if(it2 != null && cnt == 2){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it2.setImageResource(R.drawable.queen);
                            it2.setTag("full");
                            //it2.setVisibility(View.VISIBLE);
                            it2.setX(x1);
                            it2.setY(y1);
                            it2.getLocationInWindow(loc);
                            it2.startAnimation(fadeoutAnimation);
                            rc2 = new Rect(loc[0], loc[1],
                                    loc[0] + it2.getWidth(), loc[1] + it2.getHeight());
                            //it2.getDrawingRect(rc2);
                            //Log.d("Log App", "2 - X1: " + x1 + " Y1: " + y1);
                        }
                        if(it3 != null && cnt == 3){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it3.setImageResource(R.drawable.king);
                            it3.setTag("full");
                            //it3.setVisibility(View.VISIBLE);
                            it3.setX(x1);
                            it3.setY(y1);
                            it3.getLocationInWindow(loc);
                            it3.startAnimation(fadeoutAnimation);
                            rc3 = new Rect(loc[0], loc[1],
                                    loc[0] + it3.getWidth(), loc[1] + it3.getHeight());
                            //it3.getDrawingRect(rc3);
                            //Log.d("Log App", "3 X1: " + x1 + " Y1: " + y1);
                        }

                        if(it4 != null && cnt == 4){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it4.setImageResource(R.drawable.prince);
                            it4.setTag("full");
                            //it4.setVisibility(View.VISIBLE);
                            it4.setX(x1);
                            it4.setY(y1);
                            it4.getLocationInWindow(loc);
                            it4.startAnimation(fadeoutAnimation);
                            rc5 = new Rect(loc[0], loc[1],
                                    loc[0] + it1.getWidth(), loc[1] + it1.getHeight());
                        }
                        if(it5 != null && cnt == 5){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it5.setTag("full");
                            it5.startAnimation(fadeoutAnimation);
                            //it5.setVisibility(View.VISIBLE);
                            it5.setX(x1);
                            it5.setY(y1);
                            it5.getLocationInWindow(loc);
                            it5.setImageResource(R.drawable.princess);
                            rc6 = new Rect(loc[0], loc[1],
                                    loc[0] + it1.getWidth(), loc[1] + it1.getHeight());
                        }
                        if(it6 != null && cnt == 6){
                            x1 = r.nextInt(swid - 150 + 1) + 3;
                            y1 = r.nextInt(shgt - 280 + 1) + 3;
                            it6.setImageResource(R.drawable.warrior);
                            it6.setTag("full");
                            //it6.setVisibility(View.VISIBLE);
                            it6.setX(x1);
                            it6.setY(y1);
                            it6.getLocationInWindow(loc);
                            it6.startAnimation(fadeoutAnimation);
                            rc7 = new Rect(loc[0], loc[1],
                                    loc[0] + it1.getWidth(), loc[1] + it1.getHeight());
                        }
                    }
                });
            }
        };
    }
}
