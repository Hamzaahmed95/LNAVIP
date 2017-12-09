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

public class ConfirmationActivity extends AppCompatActivity {


    TextToSpeech t1;

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;

    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_braille_keyboard);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
                }
            }
        });
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);

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
                          //  onSwipeBottom();
                        } else {
                            //onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }




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


                gestureDetector.onTouchEvent(motionEvent);
                       return false;
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   System.out.println("A");



                //    System.out.println("count-> "+count11);
            }
        });
        layout6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
    }
    public void onSwipeRight() {
        Intent i =new Intent(ConfirmationActivity.this,OneHandedBrailleKeyboard.class);
        speak2("Kindly Change the name.");
        startActivity(i);
    }
    public void onSwipeLeft() {
        Toast.makeText(ConfirmationActivity.this, "left", Toast.LENGTH_SHORT).show();
        speak2("Okay!! Enter Your age!");
        //name="";
    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }


}
