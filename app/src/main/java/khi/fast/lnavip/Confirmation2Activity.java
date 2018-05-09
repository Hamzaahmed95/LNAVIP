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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 12-Dec-17.
 */

public class Confirmation2Activity extends AppCompatActivity {


    private GestureDetector gestureDetector;
    private RelativeLayout layout;
    private String activity;
    TextToSpeech t1;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(RelativeLayout) findViewById(R.id.activity_main);
        Bundle extra=this.getIntent().getExtras();
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(1f);
                    t1.setPitch(0.905f);
                }
            }
        });

        Handler handler = new Handler();
        if(extra!=null){
            activity=extra.getString("ID");

            System.out.println("in onCreate()"+activity);

        }
        class MyGestureDetector4 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                if(activity.equals("Assistant")){
                    System.out.println("Assistant");
                    String username1;
                    Intent i = new Intent(Confirmation2Activity.this,SettingsActivity.class);
                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        System.out.println("Nusrat "+extra.getString("Username"));
                        username1=extra.getString("Username");
                        i.putExtra("Username",username1);


                    }
                    speak1("Settings opened!");
                    startActivity(i);
                }
                else if(activity.equals("NewsAssistant")){
                    System.out.println("NewsAssistant");
                    Intent i = new Intent(Confirmation2Activity.this,LibraryActivity.class);
                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        System.out.println("Nusrat "+extra.getString("Username"));
                        username=extra.getString("Username");


                    }
                    i.putExtra("ID",username);
                    startActivity(i);
                }
                else{
                    System.out.println("News");
                    t1.stop();
                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        System.out.println("Nusrat "+extra.getString("Username"));
                        username=extra.getString("Username");


                    }
                    Intent i = new Intent(Confirmation2Activity.this,NewsWhizActivity.class);
                    i.putExtra("Username",username);
                    startActivity(i);
                }

                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                Intent i = new Intent(Confirmation2Activity.this,NewsActivity.class);
                t1.stop();
                startActivity(i);
                return true;
            }
        }

        gestureDetector = new GestureDetector(this, new MyGestureDetector4());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });



    }
    private void speak1(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }

}
