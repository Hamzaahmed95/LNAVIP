package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 09-Dec-17.
 */

public class NumberKeyboard extends AppCompatActivity {
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
    int count11=0;
    int count12=0;
    int count13=0;
    int count14=0;
    int count15=0;
    int count16=0;
    TextToSpeech t1;
    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;
    private GestureDetector gd5;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_braille_keyboard);

        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.4205f);
                }
            }
        });
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
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout1.setBackgroundDrawable( getResources().getDrawable(R.drawable.shape2) );
                else
                    layout1.setBackgroundColor(Color.parseColor("#000000"));

                return false;
            }
        });


        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd1.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout2.setBackgroundDrawable( getResources().getDrawable(R.drawable.circle3) );
                else

                    layout2.setBackgroundColor(Color.parseColor("#ffffff"));

                return false;
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd2.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout3.setBackgroundDrawable( getResources().getDrawable(R.drawable.circle3) );
                else
                    layout3.setBackgroundColor(Color.parseColor("#ffffff"));

                return false;
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd3.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout4.setBackgroundDrawable( getResources().getDrawable(R.drawable.circle3) );
                else
                    layout4.setBackgroundColor(Color.parseColor("#000000"));

                return false;
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd4.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout5.setBackgroundDrawable( getResources().getDrawable(R.drawable.circle3) );
                else
                    layout5.setBackgroundColor(Color.parseColor("#000000"));
                return false;
            }
        });
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd5.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                if(motionEvent.getAction()==0)
                    layout6.setBackgroundDrawable( getResources().getDrawable(R.drawable.circle3) );
                else
                    layout6.setBackgroundColor(Color.parseColor("#ffffff"));

                return false;
            }
        });


    }
    public void BrailleLanguage(int count1,int count2,int count3,int count4,int count5,int count6) {
        if (count1 == 0 & count2 != 0 & count3 == 0 & count4 != 0 & count5 != 0 & count6 != 0) {
            System.out.println("#");
            speak1("Hash");
            count1 = 0;
            count2 = 0;
            count3 = 0;
            count4 = 0;
            count5 = 0;
            count6 = 0;

        }
        else if (count1 == 0 & count2 != 0 & count3 != 0 & count4 != 0 & count5 == 0 & count6 == 0) {
            System.out.println("0");
            speak1("Zero");
            count1 = 0;
            count2 = 0;
            count3 = 0;
            count4 = 0;
            count5 = 0;
            count6 = 0;
        }

        else if(count1!=0 &count2==0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("1");
            speak1("One");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5==0&count6==0){
            speak1("Two");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            speak1("Three");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5==0&count6==0){
            speak1("Four");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5==0&count6==0){
            speak1("Five");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("Six");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("seven");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("eight");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("nine");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else{
            speak1("try again!");
        }
    }
    private void speak1(String word){
        if(word != null) {
            HashMap<String, String> myHashAlarm = new HashMap<String, String>();
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
            t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
            //String destFileName = "/sdcard/myAppCache/wakeUp.wav";
            //  t1.synthesizeToFile(word, myHashAlarm, destFileName);

        }
    }
    public void onSwipeTop() {
        Toast.makeText(NumberKeyboard.this, "top", Toast.LENGTH_SHORT).show();
        speak1("Clear");
        count1=0;
        count2=0;
        count3=0;
        count4=0;
        count5=0;
        count6=0;
    }
    public void onSwipeRight() {
        speak1("Braille Keyboard Now");
      //  Toast.makeText(NumberKeyboard.this, "right", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(NumberKeyboard.this,OneHandedBrailleKeyboard.class);
        startActivity(i);
    }
    public void onSwipeLeft() {
        Toast.makeText(NumberKeyboard.this, "left", Toast.LENGTH_SHORT).show();
    }
    public void onSwipeBottom() {
        Toast.makeText(NumberKeyboard.this, "bottom", Toast.LENGTH_SHORT).show();
        speak1("space");
    }
    }
