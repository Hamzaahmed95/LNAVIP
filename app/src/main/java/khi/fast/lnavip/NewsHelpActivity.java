package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 13-Dec-17.
 */

public class NewsHelpActivity extends AppCompatActivity {

    private RelativeLayout layout;

    private GestureDetector gd5;
    private GestureDetector gestureDetector;
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
                    t1.setSpeechRate(0.85f);
                    t1.setPitch(0.505f);
                }
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2("Okay Hamza!! if you want to play or stop the news?" +
                        " Just single tap on the screen! wanna move to next news? swipe left on the screen! wanna save the news? double tap on the screen!" +
                        " If you wanna share the news to via cell no? Long tab on the screen!. Now swipe up the screen to go back! or Double tap to repeat me again!");


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
                          //      onSwipeRight();
                            } else {
                        //        onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                      //      onSwipeBottom();
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
                speak2("Okay Hamza!! if you want to play or stop the news?" +
                        " Just single tap on the screen! wanna move to next news? swipe left on the screen! wanna save the news? double tap on the screen!" +
                        " If you wanna go back to home screen? Long tab on the screen!. Now swipe up the screen to go back! or Double tap to repeat me again!");


                return true;
            }
          }

        gd5 = new GestureDetector(this, new MyGestureDetector());
        gestureDetector = new GestureDetector(this, new GestureListener());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gd5.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
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
    public void onSwipeTop() {
       Intent i =new Intent(NewsHelpActivity.this,NewsWhizActivity.class);

        startActivity(i);
        System.out.println("SwipeUp!");
        t1.stop();
    }
}
