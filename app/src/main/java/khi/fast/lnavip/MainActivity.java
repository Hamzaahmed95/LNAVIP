package khi.fast.lnavip;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1;
    RelativeLayout layout;

    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(RelativeLayout)findViewById(R.id.activity_main);

        Handler handler = new Handler();
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(0.005f);
                }
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak("Hello And Welcome to the Newz Whiz! You Need to first Sign in, " +
                        "and for that! How would you Interact with the app?" +
                        " single tap for Braille keyboard! and double tap for normal keyboard?");

            }
        }, 1000);
        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();

                System.out.println("Double");
                speak("Normal Keyboard selected! Enter your Name:");
                Intent i = new Intent(MainActivity.this,NormalKeyBoard.class);
                startActivity(i);
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                Intent i = new Intent(MainActivity.this,OneHandedBrailleKeyboard.class);
                startActivity(i);
                speak("Braille Keyboard selected! Before using Braille! I would recommend you to check the guidance, by swapping down the screen! so that you can easily use it! if you don't want to, then Enter your Name:");
                System.out.println("Single");
                return true;
            }
        }

        gd = new GestureDetector(this, new MyGestureDetector());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    System.out.println("A");


            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd.onTouchEvent(motionEvent);
                return false;
            }
        });



    }
    private void speak(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
}
