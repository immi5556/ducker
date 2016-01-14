package com.multiview.immi.mutipleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MulviewActivity extends AppCompatActivity {

    public FrameLayout board;

    int dropZone1_X, dropZone2_X, dropZone3_X, dropZone1_Y, dropZone2_Y,
            dropZone3_Y, movingCoordinateLeft = 0, movingCoordinateTop = 0;

    int windowHeight, windowWidth, defaultMargin = 150;
    ImageView answerOption1, answerOption2, answerOption3, dropZone1,
            dropZone2, dropZone3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new FrameLayout(this);
        setContentView(R.layout.activity_mulview);
        setIds();
        setOnTouchListener();
        getWindowDimensions();
    }

    private void setOnTouchListener() {
        // TODO Auto-generated method stub
        answerOption1.setOnTouchListener(dragt);
        answerOption2.setOnTouchListener(dragt);
        answerOption3.setOnTouchListener(dragt);

    }

    private void setIds() {
        // TODO Auto-generated method stub

        board = (FrameLayout) findViewById(R.id.Board);
        // ids for answer options
        answerOption1 = (ImageView) findViewById(R.id.answer_option_1);
        answerOption2 = (ImageView) findViewById(R.id.answer_option_2);
        answerOption3 = (ImageView) findViewById(R.id.answer_option_3);

        // ids for drop zones
        dropZone1 = (ImageView) findViewById(R.id.frame1);
        dropZone2 = (ImageView) findViewById(R.id.frame2);
        dropZone3 = (ImageView) findViewById(R.id.frame3);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            System.out.println("Method--onWindowFocusChanged");

            System.out.println("\n\nFirst drop zone dimensions");
            System.out.println("left margin-->" + dropZone1.getLeft());
            System.out.println("top margin-->" + dropZone1.getTop());

            System.out.println("\n\nSecond drop zone dimensions");
            System.out.println("left margin-->" + dropZone2.getLeft());
            System.out.println("top margin-->" + dropZone2.getTop());

            System.out.println("\n\nThird drop zone dimensions");
            System.out.println("left margin-->" + dropZone3.getLeft());
            System.out.println("top margin-->" + dropZone3.getTop());

        }
    }

    private void getWindowDimensions() {
        // TODO Auto-generated method stub
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        windowHeight = metrics.heightPixels;
        System.out.println("window height" + windowHeight);
        windowWidth = metrics.widthPixels;
        System.out.println("window width" + windowWidth);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mulview, menu);
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

    View.OnTouchListener dragt = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FrameLayout.LayoutParams par = (FrameLayout.LayoutParams) v.getLayoutParams();
            switch (v.getId()) {// What is being touched
                /***
                 *
                 * Answer option 1
                 *
                 * ***/
                case R.id.answer_option_1: {
                    // Which action is being taken
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE: {
                            par.topMargin = (int) event.getRawY()
                                    - (v.getHeight() + 22);
                            par.leftMargin = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 150);

                            movingCoordinateLeft = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 0);
                            movingCoordinateTop = par.topMargin;

                            System.out.println("Answer 1 --- left"
                                    + movingCoordinateLeft + "---top"
                                    + movingCoordinateTop);

                            v.setLayoutParams(par);

                            break;
                        }// inner case MOVE
                        case MotionEvent.ACTION_UP: {
                            par.height = 40;
                            par.width = 40;
                    /*
                     * par.topMargin = (int) event.getRawY() - (v.getHeight() +
                     * 15); par.leftMargin = (int) event.getRawX() -
                     * (v.getWidth() / 2 + 90);
                     */

                            if (windowHeight < 460) {
                                par.topMargin = 109;
                                par.leftMargin = 0;
                                par.height = 22;
                                par.width = 105;

                            } else {
                                par.topMargin = defaultMargin;
                                par.leftMargin = 0;
                            }

                            // check if co-ordinates matched and drop answer in drop
                            // zone
                            if ((movingCoordinateLeft > 10 && movingCoordinateLeft < 80)
                                    && (movingCoordinateTop > 10 && movingCoordinateTop < 100)) {

                                System.out.println("left " + movingCoordinateLeft
                                        + "top  " + movingCoordinateTop);

                                dropZone1.setImageDrawable(getResources().getDrawable(
                                        R.drawable.duck));
                                answerOption1.setVisibility(View.INVISIBLE);

                            }

                            v.setLayoutParams(par);
                            break;
                        }// inner case UP
                        case MotionEvent.ACTION_DOWN: {

                            System.out.println("left" + event.getRawX());
                            System.out.println("top" + event.getRawY());

                            if (windowHeight < 460) {

                                par.height = 40;
                                par.width = 40;

                            } else {
                                par.height = 40;
                                par.width = 40;
                            }

                            v.setLayoutParams(par);
                            break;
                        }// inner case UP
                    }// inner switch
                    break;
                }// case pawn

                /***
                 *
                 * Answer option 2
                 *
                 * ***/

                case R.id.answer_option_2: {// Which action is being taken
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE: {

                            par.topMargin = (int) event.getRawY()
                                    - (v.getHeight() + 22);
                            par.leftMargin = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 150);

                            movingCoordinateLeft = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 0);
                            movingCoordinateTop = par.topMargin;

                            v.setLayoutParams(par);

                            break;
                        }// inner case MOVE
                        case MotionEvent.ACTION_UP: {
                            par.height = 40;
                            par.width = 40;
                    /*
                     * par.topMargin = (int) event.getRawY() - (v.getHeight() +
                     * 15); par.leftMargin = (int) event.getRawX() -
                     * (v.getWidth() / 2 + 90);
                     */

                            if (windowHeight < 460) {
                                par.topMargin = 150;
                                par.leftMargin = 0;
                                par.height = 40;
                                par.width = 40;

                            } else {
                                par.topMargin = 200;
                                par.leftMargin = 0;
                            }

                            // check if co-ordinates matched and drop answer in drop
                            // zone
                            if ((movingCoordinateLeft > 120 && movingCoordinateLeft < 200)
                                    && (movingCoordinateTop > 10 && movingCoordinateTop < 100)) {

                                System.out.println("left " + movingCoordinateLeft
                                        + "top  " + movingCoordinateTop);

                                dropZone2.setImageDrawable(getResources().getDrawable(
                                        R.drawable.hen));
                                answerOption2.setVisibility(View.INVISIBLE);
                            }

                            v.setLayoutParams(par);

                            break;
                        }// inner case UP
                        case MotionEvent.ACTION_DOWN: {

                            if (windowHeight < 460) {

                                par.height = 40;
                                par.width = 40;

                            } else {
                                par.height = 40;
                                par.width = 40;
                            }

                            v.setLayoutParams(par);
                            break;
                        }// inner case UP
                    }// inner switch
                    break;
                }// case pawn2

                /***
                 *
                 * Answer option 3
                 *
                 * ***/

                case R.id.answer_option_3: {// Which action is being taken
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE: {

                            par.topMargin = (int) event.getRawY()
                                    - (v.getHeight() + 22);
                            par.leftMargin = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 150);

                            movingCoordinateLeft = (int) event.getRawX()
                                    - (v.getWidth() / 2 + 0);
                            movingCoordinateTop = par.topMargin;

                            v.setLayoutParams(par);

                            break;
                        }// inner case MOVE
                        case MotionEvent.ACTION_UP: {
                            par.height = 40;
                            par.width = 40;
                    /*
                     * par.topMargin = (int) event.getRawY() - (v.getHeight() +
                     * 15); par.leftMargin = (int) event.getRawX() -
                     * (v.getWidth() / 2 + 90);
                     */

                            if (windowHeight < 460) {
                                par.topMargin = 191;
                                par.leftMargin = 0;
                                par.height = 40;
                                par.width = 40;

                            } else {
                                par.topMargin = 250;
                                par.leftMargin = 0;
                            }

                            // check if co-ordinates matched and drop answer in drop
                            // zone
                            if ((movingCoordinateLeft > 220 && movingCoordinateLeft < 310)
                                    && (movingCoordinateTop > 10 && movingCoordinateTop < 100)) {

                                System.out.println("left " + movingCoordinateLeft
                                        + "top  " + movingCoordinateTop);

                                dropZone3.setImageDrawable(getResources().getDrawable(
                                        R.drawable.queen));
                                answerOption3.setVisibility(View.INVISIBLE);
                            }
                            v.setLayoutParams(par);

                            break;
                        }// inner case UP
                        case MotionEvent.ACTION_DOWN: {
                            System.out.println("down");
                            if (windowHeight < 460) {

                                par.height = 40;
                                par.width = 40;

                            } else {
                                par.height = 40;
                                par.width = 40;
                            }
                            v.setLayoutParams(par);
                            break;
                        }// inner case UP
                    }// inner switch
                    break;
                }// case pawn2

            }// switch
            return true;
        }// onTouch

    };// dragt

}
