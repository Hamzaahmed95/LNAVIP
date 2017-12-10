package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    private GestureDetector gestureDetector;
    TextToSpeech t1;
    boolean check=false;
    static int count1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Handler handler = new Handler();
        System.out.println("count1 in main: "+ count1);
        if(count1==0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    speak3("Hello There! In order to access the app, here are the instructions that you need to follow! Press One tab anywhere in the screen");
                    System.out.println("count1= " + count1);


                }
            }, 1000);
        }
        else
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setEnabled(true);
                if(count1==1)
                    speak2("Now press double tab anywhere in the screen");
                else if(count1==2)
                    speak2("Now press Long tab anywhere in the screen");
                else if(count1==3)
                    speak2("Now swipe up anywhere in the screen");
                else if(count1==4)
                    speak2("Now swipe right anywhere in the screen");
                else if(count1==5)
                    speak2("Now swipe left tab anywhere in the screen");
                else
                    speak2("Now swipe down tab anywhere in the screen");

            }
        }, 1000);


        class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                System.out.println("count1= "+count1);
                if(count1==1) {
                    speak2("Beautiful work! Remember that! you press double tab only when you need to write next letter in a word. For example You want to write beautiful! you type B then press double tab then type E, then press double tab and goes on!");
                    count1++;
                    finish();
                    startActivity(getIntent());
                }

                else if (count1==0){
                    speak2("Oops! you pressed the double tap, kindly press single tab!");
                }
                else if (count1==2){
                    speak2("Oops! you pressed the double tap, kindly press long tab!");
                }
                else if (count1==3){
                    speak2("Oops! you pressed the double tap, kindly swipe up the screen!");
                }
                else if (count1==4){
                    speak2("Oops! you pressed the double tap, kindly swipe right the screen!");
                }

                else if (count1==5){
                    speak2("Oops! you pressed the double tap, kindly swipe left the screen!");
                }

                else if (count1==6){
                    speak2("Oops! you pressed the double tap, kindly swipe down the screen!");
                };
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                /*System.out.println("Single");
                count1=1;*/

                System.out.println("count1= "+count1);
                if(count1==0) {
                    speak2("Beautiful! Remember you press one tab when you write any letter's pattern in braille");

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            layout.setBackgroundColor(Color.parseColor("#006600"));
                        }
                    }, 5);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            layout.setBackgroundColor(Color.parseColor("#ffffff"));
                            count1++;
                            finish();
                            startActivity(getIntent());

                        }
                    }, 500);


                   }
                else if (count1==1){
                    speak2("Oops! you pressed the single tap, kindly press double tab!");

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            layout.setBackgroundColor(Color.parseColor("#990000"));
                        }
                    }, 5);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            layout.setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                    }, 500);

                }
                else if (count1==2){
                    speak2("Oops! you pressed the single tap, kindly press long tab!");
                }
                else if (count1==3){
                    speak2("Oops! you pressed the single tap, kindly swipe up the screen!");
                }
                else if (count1==4){
                    speak2("Oops! you pressed the single tap, kindly swipe right the screen!");
                }

                else if (count1==5){
                    speak2("Oops! you pressed the single tap, kindly swipe left the screen!");
                }

                else if (count1==6){
                    speak2("Oops! you pressed the single tap, kindly swipe down the screen!");
                }

                return true;
            }

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


                check=true;
                gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if(count1==2) {
                    speak2("Beautiful work! When theres a spelling mistake so you can delete the last letter by pressing long tab");
                    count1++;
                    finish();
                    startActivity(getIntent());

                }else if (count1==0){
                    speak2("Oops! you pressed the long tap, kindly press single tab!");
                }
                else if (count1==1){
                    speak2("Oops! you pressed the long tap, kindly press double tab!");
                }
                else if (count1==3){
                    speak2("Oops! you pressed the long tap, kindly swipe up the screen!");
                }
                else if (count1==4){
                    speak2("Oops! you pressed the long tap, kindly swipe right the screen!");
                }

                else if (count1==5){
                    speak2("Oops! you pressed the long tap, kindly swipe left the screen!");
                }

                else if (count1==6){
                    speak2("Oops! you pressed the long tap, kindly swipe down the screen!");
                }



                return false;
            }
        });

    }

    public void onSwipeTop() {
        if(count1==3) {
            System.out.println("count1 ="+count1);
            speak2("Beautiful work! When you want to put SPACE between two words,  so you can do it by swapping up");
            count1++;
            finish();
            startActivity(getIntent());

        }else if (count1==0){
            speak2("Oops! you swiped up the screen, kindly press single tab!");
        }
        else if (count1==1){
            speak2("Oops! you swiped up the screen, kindly press double tab!");
        }
        else if (count1==2){
            speak2("Oops! you swiped up the screen, kindly press long tab!");
        }
        else if (count1==4){
            speak2("Oops! you swiped up the screen, kindly swipe right the screen!");
        }

        else if (count1==5){
            speak2("Oops! you swiped up the screen, kindly swipe left the screen!");
        }

        else if (count1==6){
            speak2("Oops! you swiped up the screen, kindly swipe down the screen!");
        }
    }
    public void onSwipeRight() {

        if(count1==4) {
            speak2("Beautiful work! When you are done with writing your name and move to next option, you can do it by swapping right");
            count1++;
            finish();
            startActivity(getIntent());

        }else if (count1==0){
            speak2("Oops! you swiped right screen, kindly press single tab!");
        }
        else if (count1==1){
            speak2("Oops! you swiped right screen, kindly press double tab!");
        }
        else if (count1==2){
            speak2("Oops! you swiped right screen, kindly press long tab!");
        }
        else if (count1==3){
            speak2("Oops! you swiped right screen, kindly swipe up the screen!");
        }

        else if (count1==5){
            speak2("Oops! you swiped right screen, kindly swipe left the screen!");
        }

        else if (count1==6){
            speak2("Oops! you swiped right screen, kindly swipe down the screen!");
        }
    }
    public void onSwipeLeft() {


        if(count1==5) {
            speak2("Beautiful work! When you exchange your keyboard with number keyboard,  so you can do it by swapping to left");
            count1++;
            finish();
            startActivity(getIntent());

        }else if (count1==0){
            speak2("Oops! you swiped left screen, kindly press single tab!");
        }
        else if (count1==1){
            speak2("Oops! you swiped left screen, kindly press double tab!");
        }
        else if (count1==2){
            speak2("Oops! you swiped left screen, kindly press long tab!");
        }
        else if (count1==3){
            speak2("Oops! you swiped left screen, kindly swipe up the screen!");
        }

        else if (count1==4){
            speak2("Oops! you swiped left screen, kindly swipe right the screen!");
        }

        else if (count1==6){
            speak2("Oops! you swiped left screen, kindly swipe down the screen!");
        }
    }
    public void onSwipeBottom() {


        if(count1==6) {
            speak2("Beautiful work! When clear the whole word,  so you can clear it by swapping down. Lets begin with the Signed In Option");
            count1++;
            finish();
            Intent i = new Intent(MainActivity.this,OneHandedBrailleKeyboard.class);
            startActivity(i);

        }else if (count1==0){
            speak2("Oops! you swiped down screen, kindly press single tab!");
        }
        else if (count1==1){
            speak2("Oops! you swiped down screen, kindly press double tab!");
        }
        else if (count1==2){
            speak2("Oops! you swiped down screen, kindly press long tab!");
        }
        else if (count1==3){
            speak2("Oops! you swiped down screen, kindly swipe up the screen!");
        }

        else if (count1==4){
            speak2("Oops! you swiped screen, kindly swipe right the screen!");
        }

        else if (count1==5){
            speak2("Oops! you swiped down screen, kindly swipe down the screen!");
        }
    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        if(t1!=null)
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    private void speak3(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        if(t1!=null)
            t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
}
