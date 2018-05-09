package khi.fast.lnavip;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 15-Dec-17.
 */

public class MainGuidanceActivity extends AppCompatActivity {

    RelativeLayout layout;

    private GestureDetector gestureDetector;
    private GestureDetector gd;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(RelativeLayout)findViewById(R.id.activity_main);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
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
                              //  onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                          //  onSwipeBottom();
                        } else {
                           // onSwipeTop();
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
                Intent i =new Intent(MainGuidanceActivity.this,BrailleDictionary.class);
                startActivity(i);
                t1.stop();
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                Intent i =new Intent(MainGuidanceActivity.this,BraillePatternGuidance.class);
                startActivity(i);
                t1.stop();
                System.out.println("Single");
                return true;
            }
        }
        gestureDetector = new GestureDetector(this, new GestureListener());


        gd = new GestureDetector(this, new MyGestureDetector());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2("Okay! Single tap to check the braille patterns! " +
                        "or Double tap to open Braille Dictionary!  or swipe right to back again. Now waiting for your response.");

            }
        }, 1000);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });


    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    @Override
    public void onBackPressed() {
        t1.stop();
        Intent i = new Intent(MainGuidanceActivity.this,OneHandedBrailleKeyboard.class);
        startActivity(i);
        finish();
    }

    public void onSwipeRight(){
        System.out.println();
        t1.stop();
        Intent i = new Intent(MainGuidanceActivity.this,OneHandedBrailleKeyboard.class);
        startActivity(i);
        finish();
    }
}
