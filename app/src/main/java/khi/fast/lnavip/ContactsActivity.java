package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 08-Dec-17.
 */

public class ContactsActivity  extends AppCompatActivity {

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

    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;
    private GestureDetector gd5;
    String ID="null";
    private String Username1;
    private ChildEventListener mChildEventListener;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_braille_keyboard);
        textView=(TextView)findViewById(R.id.Name);
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
        MainLayout=(LinearLayout)findViewById(R.id.MainLayout);
        Bundle extra1 = getIntent().getExtras();
        if (extra1 != null) {
            System.out.println("Nusrat "+extra1.getString("Username"));
            Username1=extra1.getString("Username");
            System.out.println("hello there? "+ Username1);

        }


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("SaveNews");



        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            ID=extra.getString("ID");


        }

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
    public void BrailleLanguage(int count1,int count2,int count3,int count4,int count5,int count6){
        if(count1!=0 &count2==0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("A");
            speak1("A");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5==0&count6==0){
            speak1("C");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            speak1("B");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5==0&count6==0){
            speak1("D");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5==0&count6==0){
            speak1("E");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("F");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("G");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("H");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5==0&count6==0){
            speak1("I");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5==0&count6==0){
            speak1("J");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4==0&count5!=0&count6==0){
            speak1("K");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5!=0&count6==0){
            speak1("L");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5!=0&count6==0){
            speak1("M");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5!=0&count6==0){
            speak1("N");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5!=0&count6==0){
            speak1("O");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4==0&count5!=0&count6==0){
            speak1("P");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3!=0&count4!=0&count5!=0&count6==0){
            speak1("Q");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4!=0&count5!=0&count6==0){
            speak1("R");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4==0&count5!=0&count6==0){
            speak1("S");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5!=0&count6==0){
            speak1("T");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4==0&count5!=0&count6!=0){
            speak1("U");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5!=0&count6!=0){
            speak1("V");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3!=0&count4!=0&count5==0&count6!=0){
            speak1("W");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5!=0&count6!=0){
            speak1("X");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4!=0&count5!=0&count6!=0){
            speak1("Y");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3==0&count4!=0&count5!=0&count6!=0){
            speak1("Z");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3==0&count4==0&count5!=0&count6==0){
            speak1("apostrophe"); // '
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4==0&count5!=0&count6==0){
            speak1("bar"); // /
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5==0&count6==0){
            System.out.println(":");
            speak1("colon");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            System.out.println(",");
            speak1("comma");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4==0&count5==0&count6!=0){
            System.out.println(".");
            speak1("dot");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5==0&count6!=0){
            System.out.println("$");
            speak1("dollar sign");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4!=0&count5!=0&count6==0){
            System.out.println("!");
            speak1("exclamation point");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3==0&count4==0&count5!=0&count6!=0){
            System.out.println("-");
            speak1("hyphen");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2!=0&count3==0&count4!=0&count5!=0&count6!=0){
            System.out.println("#");
            speak1("Number sign");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5!=0&count6!=0){
            System.out.println("?");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1==0 &count2==0&count3!=0&count4==0&count5!=0&count6==0){
            System.out.println(";");
            speak1("semicolon");
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
    public void onSwipeTop() {
        Toast.makeText(ContactsActivity.this, "top", Toast.LENGTH_SHORT).show();
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

        Toast.makeText(ContactsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
        name=name+" ";
        textView.setText(name);
        speak1("space");

    }
    public void onSwipeLeft() {
        System.out.println("name123: "+name);
        Toast.makeText(ContactsActivity.this, "left", Toast.LENGTH_SHORT).show();
        if(name.equals("")){
            speak2("You haven't typed any thing yet");
            System.out.println("this");
        }
        else if(name.equals(" ")){
            System.out.println("this1");
            speak2("You just typed the space, Kindly type your name" );

        }
        else {
            Intent i = new Intent(ContactsActivity.this,ConfirmationActivity3.class);
            System.out.println("name:-> "+name+"<-:name");
            i.putExtra("Name",name);
            i.putExtra("Username",Username1);
            i.putExtra("ActivityName","ContactsActivity");
            System.out.println("this2");


            startActivity(i);

            System.out.println("here now? "+name);
            speak1("You typed " + name + ". To continue, swipe left or to change the name, swipe right.");
        }
        //name="";
    }
    public void onSwipeBottom() {
        Intent i =new Intent(ContactsActivity.this,MainGuidanceActivity.class);
        startActivity(i);
    }
    public String method(String str) {
        if (str != null && str.length() > 0 ) {
            str = str.substring(0, str.length() - 1);
        }
        textView.setText(str);
        return str;
    }

    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }




}
