package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 09-May-18.
 */

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Locale;


import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Locale;

public class BraillePatternGuidance extends AppCompatActivity {



    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;



    LinearLayout box111;
    LinearLayout box112;
    LinearLayout box113;
    LinearLayout box121;
    LinearLayout box122;
    LinearLayout box123;
    LinearLayout box131;
    LinearLayout box132;
    LinearLayout box133;

    private GestureDetector gd5;

    LinearLayout box211;
    LinearLayout box212;
    LinearLayout box213;
    LinearLayout box221;
    LinearLayout box222;
    LinearLayout box223;
    LinearLayout box231;
    LinearLayout box232;
    LinearLayout box233;


    LinearLayout box311;
    LinearLayout box312;
    LinearLayout box313;
    LinearLayout box321;
    LinearLayout box322;
    LinearLayout box323;
    LinearLayout box331;
    LinearLayout box332;
    LinearLayout box333;


    LinearLayout box411;
    LinearLayout box412;
    LinearLayout box413;
    LinearLayout box421;
    LinearLayout box422;
    LinearLayout box423;
    LinearLayout box431;
    LinearLayout box432;
    LinearLayout box433;


    LinearLayout box511;
    LinearLayout box512;
    LinearLayout box513;
    LinearLayout box521;
    LinearLayout box522;
    LinearLayout box523;
    LinearLayout box531;
    LinearLayout box532;
    LinearLayout box533;


    LinearLayout box611;
    LinearLayout box612;
    LinearLayout box613;
    LinearLayout box621;
    LinearLayout box622;
    LinearLayout box623;
    LinearLayout box631;
    LinearLayout box632;
    LinearLayout box633;

    private GestureDetector gestureDetector;





    LinearLayout MainLayout;
    Vibrator v;
    int count=0;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.braille_language);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
                }
            }
        });
        layout1 =(LinearLayout)findViewById(R.id.layout1);
        layout2 =(LinearLayout)findViewById(R.id.layout2);
        layout3 =(LinearLayout)findViewById(R.id.layout3);
        layout4 =(LinearLayout)findViewById(R.id.layout4);
        layout5 =(LinearLayout)findViewById(R.id.layout5);
        layout6 =(LinearLayout)findViewById(R.id.layout6);

        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);
        layout6.setVisibility(View.INVISIBLE);


        MainLayout=(LinearLayout)findViewById(R.id.MainLayout);
        class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {

                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }




        class MyGestureDetector6 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                return true;
            }
        }

        gd5 = new GestureDetector(this, new MyGestureDetector6());

        box111=(LinearLayout)findViewById(R.id.box111);
        box112=(LinearLayout)findViewById(R.id.box112);
        box113=(LinearLayout)findViewById(R.id.box113);
        box121=(LinearLayout)findViewById(R.id.box121);
        box122=(LinearLayout)findViewById(R.id.box122);
        box123=(LinearLayout)findViewById(R.id.box123);
        box131=(LinearLayout)findViewById(R.id.box131);
        box132=(LinearLayout)findViewById(R.id.box132);
        box133=(LinearLayout)findViewById(R.id.box133);

        box211=(LinearLayout)findViewById(R.id.box211);
        box212=(LinearLayout)findViewById(R.id.box212);
        box213=(LinearLayout)findViewById(R.id.box213);
        box221=(LinearLayout)findViewById(R.id.box221);
        box222=(LinearLayout)findViewById(R.id.box222);
        box223=(LinearLayout)findViewById(R.id.box223);
        box231=(LinearLayout)findViewById(R.id.box231);
        box232=(LinearLayout)findViewById(R.id.box232);
        box233=(LinearLayout)findViewById(R.id.box233);

        box311=(LinearLayout)findViewById(R.id.box311);
        box312=(LinearLayout)findViewById(R.id.box312);
        box313=(LinearLayout)findViewById(R.id.box313);
        box321=(LinearLayout)findViewById(R.id.box321);
        box322=(LinearLayout)findViewById(R.id.box322);
        box323=(LinearLayout)findViewById(R.id.box323);
        box331=(LinearLayout)findViewById(R.id.box331);
        box332=(LinearLayout)findViewById(R.id.box332);
        box333=(LinearLayout)findViewById(R.id.box333);




        gestureDetector = new GestureDetector(this, new GestureListener());

        MainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                System.out.println("hello123");

                gestureDetector.onTouchEvent(motionEvent);
                // gd5.onTouchEvent(motionEvent);


                return false;
            }
        });




        box411=(LinearLayout)findViewById(R.id.box411);
        box412=(LinearLayout)findViewById(R.id.box412);
        box413=(LinearLayout)findViewById(R.id.box413);
        box421=(LinearLayout)findViewById(R.id.box421);
        box422=(LinearLayout)findViewById(R.id.box422);
        box423=(LinearLayout)findViewById(R.id.box423);
        box431=(LinearLayout)findViewById(R.id.box431);
        box432=(LinearLayout)findViewById(R.id.box432);
        box433=(LinearLayout)findViewById(R.id.box433);


        box511=(LinearLayout)findViewById(R.id.box511);
        box512=(LinearLayout)findViewById(R.id.box512);
        box513=(LinearLayout)findViewById(R.id.box513);
        box521=(LinearLayout)findViewById(R.id.box521);
        box522=(LinearLayout)findViewById(R.id.box522);
        box523=(LinearLayout)findViewById(R.id.box523);
        box531=(LinearLayout)findViewById(R.id.box531);
        box532=(LinearLayout)findViewById(R.id.box532);
        box533=(LinearLayout)findViewById(R.id.box533);


        box611=(LinearLayout)findViewById(R.id.box611);
        box612=(LinearLayout)findViewById(R.id.box612);
        box613=(LinearLayout)findViewById(R.id.box613);
        box621=(LinearLayout)findViewById(R.id.box621);
        box622=(LinearLayout)findViewById(R.id.box622);
        box623=(LinearLayout)findViewById(R.id.box623);
        box631=(LinearLayout)findViewById(R.id.box631);
        box632=(LinearLayout)findViewById(R.id.box632);
        box633=(LinearLayout)findViewById(R.id.box633);


        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        box111.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box112.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box113.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box121.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box122.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box123.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box131.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box132.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box133.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("you have done with first box! single tap to move to next box double tap to repeat it again!");
                    //isboxDone=true;
                    count=0;

                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.INVISIBLE);
                    layout5.setVisibility(View.INVISIBLE);
                    layout6.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        box211.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box212.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box213.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box221.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box222.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box223.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box231.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box232.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box233.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("done!");
                    count=0;
                }

                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        box311.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box312.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box313.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box321.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box322.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box323.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box331.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box332.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box333.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("done!");
                    count=0;
                }

                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.VISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        box411.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box412.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box413.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box421.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box422.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box423.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box431.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box432.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box433.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("done!");
                    count=0;

                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.INVISIBLE);
                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.INVISIBLE);
                    layout5.setVisibility(View.VISIBLE);
                    layout6.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        box511.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box512.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box513.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box521.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box522.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box523.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box531.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box532.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box533.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("done!");
                    count=0;

                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.INVISIBLE);
                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.INVISIBLE);
                    layout5.setVisibility(View.INVISIBLE);
                    layout6.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


        box611.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==0) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;
                }
                return false;
            }
        });
        box612.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==1) {
                    v.vibrate(500);
                    speak2("Move your finger to right again");
                    count++;
                }
                return false;
            }
        });
        box613.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==2) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box621.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // MotionEven;
                // System.out.println(m);
                if(count==5) {
                    v.vibrate(500);
                    speak2("Move your finger to down");
                    count++;
                }
                return false;
            }
        });
        box622.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==4) {
                    v.vibrate(500);
                    speak2("Move your finger to left again");
                    count++;
                }
                return false;
            }
        });
        box623.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==3) {
                    v.vibrate(500);
                    speak2("Move your finger to left");
                    count++;
                }
                return false;
            }
        });
        box631.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count==6) {
                    v.vibrate(500);
                    speak2("Move your finger to right");
                    count++;

                }     return false;
            }
        });
        box632.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==7) {
                    speak2("Move your finger to right again");
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        box633.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(count==8) {
                    v.vibrate(500);

                    speak2("done!");
                    count=0;
                }
                return false;
            }
        });




    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        if(t1!=null)
            t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    public void onSwipeRight(){
        System.out.println("onSwipeRight");
    }

    public void onSwipeLeft(){
        System.out.println("onSwipeLeft");
    }

    public void onSwipeTop(){
        System.out.println("onSwipeTop");
    }
    public void onSwipeBottom(){
        System.out.println("onSwipeBottom");
    }


}
