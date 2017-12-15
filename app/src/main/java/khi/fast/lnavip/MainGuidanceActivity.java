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
        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                Intent i =new Intent(MainGuidanceActivity.this,LearningBrailleActivity.class);
                startActivity(i);
                    return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                Intent i =new Intent(MainGuidanceActivity.this,GuidelinesActivity.class);
                startActivity(i);

                System.out.println("Single");
                return true;
            }
        }

        gd = new GestureDetector(this, new MyGestureDetector());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2("Okay! Single tap to open Guidelines of using Braille keyboard!" +
                        "Double tap to open a small 3 level challenge of using Braille keyboard!" +
                        "or long tap on the screen to open Braille Dictionary! Now waiting for your response. ");

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
                return false;
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent i =new Intent(MainGuidanceActivity.this,BrailleDictionary.class);
                startActivity(i);

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


}
