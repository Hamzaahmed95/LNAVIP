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

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 14-Dec-17.
 */

public class CVMakerActivity extends AppCompatActivity  {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;

    private GestureDetector gestureDetector;
    private GestureDetector gd5;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
                }
            }
        });
        setContentView(R.layout.one_handed_braille_keyboard);

        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);

        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                Intent i = new Intent(CVMakerActivity.this,CreateNotes.class);
                startActivity(i);
                return true;
            }
        }
        class MyGestureDetector1 extends GestureDetector.SimpleOnGestureListener {
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
        class MyGestureDetector2 extends GestureDetector.SimpleOnGestureListener {
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
        class MyGestureDetector3 extends GestureDetector.SimpleOnGestureListener {
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
        class MyGestureDetector4 extends GestureDetector.SimpleOnGestureListener {
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
        class MyGestureDetector5 extends GestureDetector.SimpleOnGestureListener {
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
        gd = new GestureDetector(this, new MyGestureDetector());
        gd1 = new GestureDetector(this, new MyGestureDetector1());
        gd2 = new GestureDetector(this, new MyGestureDetector2());
        gd3 = new GestureDetector(this, new MyGestureDetector3());
        gd4 = new GestureDetector(this, new MyGestureDetector4());
        gd5 = new GestureDetector(this, new MyGestureDetector5());
      //  gestureDetector = new GestureDetector(this, new GestureListener());
        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd.onTouchEvent(motionEvent);
            //    gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd1.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd2.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd3.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd4.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd5.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        layout2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        layout3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        layout4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        layout5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        layout6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
    }
    private void speak1(String word){
        if(word != null) {
            HashMap<String, String> myHashAlarm = new HashMap<String, String>();
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
            t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
        }
    }



}
