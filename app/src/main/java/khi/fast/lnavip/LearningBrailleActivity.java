package khi.fast.lnavip;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 13-Dec-17.
 */

public class LearningBrailleActivity extends AppCompatActivity {

    MediaPlayer player;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    int count1=0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int count6=0;
    static int check=0;
    static int level=1;
    TextToSpeech t1;

    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;
    private GestureDetector gd5;
    private GestureDetector gestureDetector;
    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_braille_keyboard2);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
       v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
                }
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2("Okay Your Braille Challenge start Now! Question 1: Press 1 ");

            }
        }, 1000);
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

        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count1=1;
                check++;
                if(level==1) {
                    if (check == 1) {
                        speak2("Great!! Now Press 2");
                        check++;
                        v.vibrate(500);
                    } else
                        speak2("Oops! Try again!");
                }
                else if (level==2){
                    if (check==1) { //check=1 count1=1
                        speak2("and?");
                        check++;
                    }
                    else{
                        speak2("Oops! Try Again!");
                    }
                }
                else{
                    if(check==1){
                        speak2("and?");
                        check++;
                    }
                    else{
                        speak2("oops!");
                    }
                }
                return true;
            }
        }
        class MyGestureDetector2 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count2=1;
                if(level==1) {
                    if (check == 2) {
                        speak2("Great!! Now Press 3");
                        v.vibrate(500);
                        check++;
                    } else
                        speak2("Oops! Try again!");
                }
                else if(level==2){
                    if (check==2) {
                        speak2("Great!! Now Press 3 and 4");
                        v.vibrate(500);
                        check++;
                    }else{
                        speak2("Oops!");
                    }

                }
                else{
                    if(check==4){
                        speak2("and?");
                        check++;
                    }
                    else{
                        speak2("oops!");
                    }
                }
                return true;
            }
        }
        class MyGestureDetector3 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count3=1;
                if(level==1) {

                    if (check == 3) {
                        speak2("Great!! Now Press 4");
                        v.vibrate(500);
                        check++;
                    } else
                        speak2("Oops! Try again!");
                }
                else if(level==2){
                    if (check==3) { //check=1 count1=1
                        speak2("and?");
                        check++;
                    }
                    else{
                        speak2("Oops! Try Again!");
                    }
                }
                else{
                    if(check==2){
                        speak2("and?");
                        check++;
                    }
                    else{
                        speak2("oops!");
                    }
                }
                return true;
            }
        }
        class MyGestureDetector4 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count4=1;
                if(level==1) {
                    if (check == 4) {
                        speak2("Great!! Now Press 5");
                        v.vibrate(500);
                        check++;
                    } else
                        speak2("Oops! Try again!");
                }
                else if(level==2){
                    if (check==4) { //check=1 count1=1
                        speak2("Great!! Now Press 5 and 6");
                        v.vibrate(500);
                        check++;
                    }
                    else{
                        speak2("Oops! Try Again!");
                    }
                }
                else{
                    if(check==5){
                        check++;
                        speak2("and?");
                    }
                    else{
                        speak2("oops!");
                    }
                }
                return true;
            }
        }
        class MyGestureDetector5 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count5=1;
                if(level==1) {
                    if (check == 5) {
                        speak2("Great!! Now Press 6");
                        v.vibrate(500);
                        check++;

                    } else
                        speak2("Oops! Try again!");
                }
                else if(level==2){
                    if(check==5){
                        speak2("and?");
                        check++;
                    }
                    else
                        speak2("Oops!");
                }
                else{
                    if(check==3){
                        speak2("Great! Now Press 2 4 and 6");
                        check++;
                        v.vibrate(500);
                    }
                    else{
                        speak2("oops!");
                    }
                }


                return true;
            }
        }
        class MyGestureDetector6 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: "+count1+" "+count2+" "+count3);
                BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                count6=1;
                if(level==1) {
                    if (check == 6) {
                        speak2("Excellent work!! You have cleared Level 1! Now Starting Braille Challenge 2! Enter 1 and 2");

                        v.vibrate(1000);
                        check = 0;
                        level++;
                    } else
                        speak2("Oops! Try again!");
                }
                else if(level==2){
                    if(check==6){
                        speak2("Excellent work!! You have cleared Level 2! Now Starting Braille Challenge 3! Enter 1 3 and 5");
                        check=0;

                        v.vibrate(1000);
                        level++;
                    }
                }
                else{
                    if(check==6){
                        speak2("Excellent work!! You have cleared all the beginner's Levels! Now Swap up to get back to sign in page");
                        v.vibrate(1000);
                    }
                    else{
                        speak2("oops!");
                    }
                }
                return true;
            }
        }


        gd = new GestureDetector(this, new MyGestureDetector());
        gd1 = new GestureDetector(this, new MyGestureDetector2());
        gd2 = new GestureDetector(this, new MyGestureDetector3());
        gd3 = new GestureDetector(this, new MyGestureDetector4());
        gd4 = new GestureDetector(this, new MyGestureDetector5());
        gd5 = new GestureDetector(this, new MyGestureDetector6());
        gestureDetector = new GestureDetector(this, new GestureListener());

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd.onTouchEvent(motionEvent);

                gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd1.onTouchEvent(motionEvent);

                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd2.onTouchEvent(motionEvent);

                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd3.onTouchEvent(motionEvent);

                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd4.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd5.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        
        
    }
    public void BrailleLanguage(int count1,int count2,int count3,int count4,int count5,int count6)
    {
        if(count1!=0 &count2==0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("A");
            //speak1("A");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5==0&count6==0){
            //speak1("C");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            //speak1("B");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5==0&count6==0){
            //speak1("D");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5==0&count6==0){
            //speak1("E");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            //speak1("F");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            //speak1("G");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            //speak1("H");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            //speak1("I");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            //speak1("J");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4==0&count5!=0&count6==0){
            //speak1("K");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5!=0&count6==0){
            //speak1("L");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5!=0&count6==0){
            //speak1("M");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5!=0&count6==0){
            //speak1("N");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5!=0&count6==0){
            //speak1("O");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5!=0&count6==0){
            //speak1("P");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5!=0&count6==0){
            //speak1("Q");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5!=0&count6==0){
            //speak1("R");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5!=0&count6==0){
            //speak1("S");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5!=0&count6==0){
            //speak1("T");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4==0&count5!=0&count6!=0){
            //speak1("U");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5!=0&count6!=0){
            //speak1("V");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5==0&count6!=0){
            //speak1("W");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5!=0&count6!=0){
            //speak1("X");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5!=0&count6!=0){
            //speak1("Y");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5!=0&count6!=0){
            //speak1("Z");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3==0&count4==0&count5!=0&count6==0){
            //speak1("apostrophe"); // '
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4==0&count5!=0&count6==0){
            //speak1("bar"); // /
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            System.out.println(":");
            //speak1("colon");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            System.out.println(",");
            //speak1("comma");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4==0&count5==0&count6!=0){
            System.out.println(".");
            //speak1("dot");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5==0&count6!=0){
            System.out.println("$");
            //speak1("dollar sign");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5!=0&count6==0){
            System.out.println("!");
            //speak1("exclamation point");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3==0&count4==0&count5!=0&count6!=0){
            System.out.println("-");
            //speak1("hyphen");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4!=0&count5!=0&count6!=0){
            System.out.println("#");
            //speak1("Number sign");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5!=0&count6!=0){
            System.out.println("?");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5!=0&count6==0){
            System.out.println(";");
            //speak1("semicolon");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else{
           // speak2("try again");
        }
    }
    public void number(Context ctx) {
        AssetManager am;
        try {
            am = ctx.getAssets();
            AssetFileDescriptor afd = am.openFd("android.resource://"+getApplicationContext().getPackageName()+"/"+R.raw.audio1);
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
                    afd.getLength());
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.release();
                }

            });
            player.setLooping(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    public void  onSwipeRight(){

    }
    public void  onSwipeLeft(){

    }
    public void  onSwipeTop(){
        Intent i = new Intent(LearningBrailleActivity.this,OneHandedBrailleKeyboard.class);
        i.putExtra("ID","LearningBrailleActivity");
        startActivity(i);

    }
    public void  onSwipeBottom(){

    }

}
