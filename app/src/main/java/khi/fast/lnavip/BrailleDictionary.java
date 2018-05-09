package khi.fast.lnavip;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 08-Dec-17.
 */

public class BrailleDictionary  extends AppCompatActivity {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout MainLayout;
    TextView textView;

    RelativeLayout dictionary;
    int count1=0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int count6=0;
    int count11=0;
    private final int SPEECH_RECOGNITION_CODE = 1;
    private TextView txtOutput;
    private ImageButton btnMicrophone;

    int mPtrCount=0;
    String name="";
    TextToSpeech t1;
    private GestureDetector gd;
    int allow=0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.braille_dictionary);

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setPitch(.0205f);
                }
            }
        });
        dictionary=(RelativeLayout)findViewById(R.id.dictionary);
        txtOutput = (TextView) findViewById(R.id.txt_output);
        btnMicrophone = (ImageButton) findViewById(R.id.btn_mic);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dictionary.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);


                return false;
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak2("Braille Dictionary Opens! Tap anywhere on screen and find out pattern by saying the letter");

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
                                allow=1;
                            } else {
                               // onSwipeLeft();
                            }
                            result = true;
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                           // onSwipeBottom();
                        } else {
                          //  onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                if(allow==0){
                    startSpeechToText();
                }

                return result;
            }
        }
        gestureDetector = new GestureDetector(this, new GestureListener());


        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                System.out.println("layout1: " + count1 + " " + count2 + " " + count3);
             //   BrailleLanguage(count1, count2, count3, count4, count5, count6);
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count4 = 0;
                count5 = 0;
                count6 = 0;
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                System.out.println("Single");
                count1 = 1;
                return true;
            }
        }


        gd = new GestureDetector(this, new MyGestureDetector());
    }
    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak something...");
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Speech recognition is not supported in this device.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Callback for speech recognition activity
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    System.out.println("result: "+text);
                    //txtOutput.setText(text);
                    BrailleLanguage(text);

                }
                break;
            }
        }
    }
    public void BrailleLanguage(String ask){
        if(ask.equals("a")){
            System.out.println("A");
            speak2("The Pattern of A is 1");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("b")){
           // speak1("C");
            speak2( "The Pattern of B is 1 and 2");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("c")){
           // speak1("B");

            speak2( "The Pattern of C is 1 and 4");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("d")){
           // speak1("D");

            speak2( "The Pattern of D is 1 4 and 5");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("e")){

            speak2( "The Pattern of E is 1 and 2");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("f")){
            speak2( "The Pattern of F is 1 2 and 4");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("g")){
            speak2( "The Pattern of G is 1 2 4 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("h")){
            speak2( "The Pattern of H is 1 2 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("i")){
            speak2( "The Pattern of I is 2 and 4");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("j")){
            speak2( "The Pattern of J is 1 4 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("k")){
            speak2( "The Pattern of K is 1 and 3");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        } else if(ask.equals("l")){
            speak2( "The Pattern of L is 1 2 and 3");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("m")){
            speak2( "The Pattern of M is 1 3 and 4");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("n")){
            speak2( "The Pattern of N is 1 3 4 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("o")){
            speak2( "The Pattern of O is 1 3 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("p")){
            speak2( "The Pattern of P is 1 2 3 and 4");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("q")){
            speak2( "The Pattern of Q is 1 2 3 4and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("r")){
            speak2( "The Pattern of F is 1 2 3 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("s")){
            speak2( "The Pattern of S is 2 3 and 4");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("t")){
            speak2( "The Pattern of T is 2 3 4 and 5");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("u")||ask.equals("you")){
            speak2( "The Pattern of U is 1 3 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("v")){
            speak2( "The Pattern of V is 1 2 3 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("w")){
            speak2( "The Pattern of W is 2 4 5 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("x")){
            speak2( "The Pattern of X is 1 3 4 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;
        }
        else if(ask.equals("y")||ask.equals("why")){
            speak2( "The Pattern of Y is 1 3 4 5 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(ask.equals("z")){
            speak2( "The Pattern of Z 1 3 5 and 6");

            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else{

            speak2("try again");
        }
    }
    private void speak1(String word){
        if(word != null) {
            if(!word.equals("Clear") && !word.equals("space")) {
                name = name + word;
                textView.setText(name);
            }
            System.out.println("NAME: "+ name);
            HashMap<String, String> myHashAlarm = new HashMap<String, String>();
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
            t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
        }
    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    public void onSwipeRight() {

        System.out.println();
        t1.stop();
        Intent i = new Intent(BrailleDictionary.this,OneHandedBrailleKeyboard.class);
        startActivity(i);

    }
    public String method(String str) {
        if (str != null && str.length() > 0 ) {
            str = str.substring(0, str.length() - 1);
        }
        textView.setText(str);
        return str;
    }
}
