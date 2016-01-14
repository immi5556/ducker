package com.multiview.immi.mutipleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class AbsActivity extends AppCompatActivity {

    int swid, shgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        swid = size.x;
        shgt = size.y;
        Random r = new Random();
        int x1 = r.nextInt(swid - 3 + 1) + 3;
        int y1 = r.nextInt(shgt - 3 + 1) + 3;
        FrameLayout rl = (FrameLayout)findViewById(R.id.Board);
        ViewGroup.MarginLayoutParams mlp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mlp.setMargins(100, 100, 200, 200);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(140, 140);
        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(parms);
        iv1.setImageResource(R.drawable.duck);
        iv1.setX(400);
        iv1.setY(300);
        //ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)iv1.getLayoutParams();
        //mlp.setMargins(140, 140, 200, 300);//all in pixels
        //iv1.setLayoutParams(mlp);
        //iv1.setBounds(100, 300, 160, 160);
        rl.addView(iv1);
//        setContentView(sv1);
//        x1 = r.nextInt(swid - 3 + 1) + 3;
//        y1 = r.nextInt(shgt - 3 + 1) + 3;
//        StaticView sv2 = new StaticView(this, x1, y1);
//        rl.addView(sv2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class StaticView extends ImageView {
        ShapeDrawable sp;
        public StaticView(Context context, int x1, int y1) {
            super(context);
            sp = new ShapeDrawable(new OvalShape());
            sp.getPaint().setColor(0xffffAC23);
            sp.setBounds(100, 300, 160, 160);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            sp.draw(canvas);
            invalidate();
        }
    }
}
