package khi.fast.lnavip;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class PhoneActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE =123 ;
    Button btnSendSMS;
    String phoneN0;
    EditText e1;
    EditText e2;
    String msg;
    String phoneNO;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout MainLayout;
    TextView textView;
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
    int mPtrCount=0;
    String name="";
    TextToSpeech t1;
    static  int phonecount=0;
    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;
    private GestureDetector gd5;
    private GestureDetector gestureDetector;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        setContentView(R.layout.one_handed_braille_keyboard);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
        textView=(TextView)findViewById(R.id.Name);
        MainLayout=(LinearLayout)findViewById(R.id.MainLayout);
        layout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                textView.setText(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });

        layout2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });

        layout3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });

        layout4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });

        layout5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });

        layout6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                name=method(name);
                System.out.println("deleted word: "+name);
                speak2("Last word Clear");
                return false;
            }
        });
        Bundle extra=this.getIntent().getExtras();
        t1=new TextToSpeech(this    , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(1f);
                    t1.setPitch(0.905f);
                }
            }
        });
        if(extra!=null){
           msg=extra.getString("NEWS");
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {




                speak2("BRAILLE KEYBOARD NOW ON THE SCREEN! ENTER THE NUMBER NOW TO SHARE NEWS ON THE NUMBER!:");

            }
        }, 1500);


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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }


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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,GuidelinesActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }



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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,GuidelinesActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }



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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,GuidelinesActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }



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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,GuidelinesActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }


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
                int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mPtrCount++;

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mPtrCount--;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mPtrCount++;
                        break;
                    case MotionEvent.ACTION_UP:
                        mPtrCount--;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (mPtrCount==2){
                            Intent i = new Intent(PhoneActivity.this,GuidelinesActivity.class);
                            startActivity(i);
                        }
                        System.out.println("Move "+mPtrCount);
                        break;

                }


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
            speak1("0");
            count1 = 0;
            count2 = 0;
            count3 = 0;
            count4 = 0;
            count5 = 0;
            count6 = 0;
        }

        else if(count1!=0 &count2==0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("1");
            speak1("1");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5==0&count6==0){
            speak1("2");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            speak1("3");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5==0&count6==0){
            speak1("4");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5==0&count6==0){
            speak1("5");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("6");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("7");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("8");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("9");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else{
            speak2("try again!");
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











//---sends an SMS message to another device---

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    public void requestpermisson(String p,String m){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else{         //already has permission granted
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(p, null,m, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        System.out.println("Request Code: "+requestCode);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSMS(phoneN0, msg);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }



    private void sendSMS(String phoneNumber, String message)
    {
        try {

            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> msgArray = smsManager.divideMessage(message);

            smsManager.sendMultipartTextMessage(phoneNumber, null,msgArray, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    public String method(String str) {
        if (str != null && str.length() > 0 ) {
            str = str.substring(0, str.length() - 1);
        }
        textView.setText(str);
        return str;
    }
    public void onSwipeTop() {
        Toast.makeText(PhoneActivity.this, "top", Toast.LENGTH_SHORT).show();
        speak1("Clear");
        name="";
        textView.setText(name);
        count1=0;
        count2=0;
        count3=0;
        count4=0;
        count5=0;
        count6=0;
    }
    public void onSwipeRight() {

        Toast.makeText(PhoneActivity.this, "bottom", Toast.LENGTH_SHORT).show();
        name=name+" ";
        textView.setText(name);
        speak1("space");

    }
    public void onSwipeLeft() {
        Toast.makeText(PhoneActivity.this, "left", Toast.LENGTH_SHORT).show();
        if(name.equals("")){
            speak2("You haven't typed any thing yet");
        }
        else if(name.equals(" ")){
            speak2("You just typed the space, Kindly type your name" );

        }
        else {
           /* Intent i = new Intent(PhoneActivity.this,ConfirmationActivity.class);
            i.putExtra("ActivityName","PhoneActivity");
            System.out.println("name:-> "+name+"<-:name");
            i.putExtra("Name",name);
            startActivity(i);

            System.out.println("here now? "+name);
            */
            if(phonecount==1){
                requestpermisson(name,msg);


            }
            else{
                speak2("Are you sure? you want send news on "+name+"? if yes then swipe right again!");
                phonecount++;
            }


          }
        //name="";
    }
    public void onSwipeBottom() {
        Intent i =new Intent(PhoneActivity.this,LearningBrailleActivity.class);
        startActivity(i);
    }
    public void sendAppMsg(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = " message you want to share..";
        // change with required  application package

        intent.setPackage("com.whatsapp");
        if (intent != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text);//
            startActivity(Intent.createChooser(intent, text));
        } else {

            Toast.makeText(this, "App not found", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
