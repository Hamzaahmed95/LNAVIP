package khi.fast.lnavip;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Hamza Ahmed on 09-Dec-17.
 */

public class ConfirmationActivity extends AppCompatActivity {


    TextToSpeech t1;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    String name1;
    String username;
    String Username1;
    String Age1;
    String password;
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

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("UserProfile");
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
        Bundle extra=this.getIntent().getExtras();
        if(extra!=null){
            name1=extra.getString("ActivityName");
            username=extra.getString("Name");

        }

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
        if(name1.equals("OneHandedBrailleKeyboard"))
        speak2("Kindly Change the name.");
        else if(name1.equals("AgeClass"))
            speak2("Kindly Change the age");
        else
            speak2("Kindly Change the password");

        startActivity(i);


    }

    public void onSwipeLeft() {
        Toast.makeText(ConfirmationActivity.this, "left", Toast.LENGTH_SHORT).show();

        if(name1.equals("OneHandedBrailleKeyboard")){
            System.out.println("braile");
            speak2("Okay!! Enter Your age!");
            Intent i = new Intent(ConfirmationActivity.this,AgeClass.class);
            i.putExtra("Name",username);
            startActivity(i);
        }
        else if(name1.equals("AgeClass")){
            Bundle extra=this.getIntent().getExtras();
            if(extra!=null){
                Username1=extra.getString("Name1");
                Age1=extra.getString("Age");

            }
            System.out.println("number");
            speak2("you set your name as "+Username1+"!! and Age as "+Age1+"!! Set Your password now!");
         //   speak2("Okay!! Set Your password now!");
            Intent i = new Intent(ConfirmationActivity.this,PasswordClass.class);
            i.putExtra("Name",Username1);
            i.putExtra("Age",Age1);
            startActivity(i);
        }
        else{
            Bundle extra=this.getIntent().getExtras();
            if(extra!=null){
                Username1=extra.getString("Name1");
                Age1=extra.getString("Age");
                password=extra.getString("Password");
            }
            SignUpClass signUpClass = new SignUpClass(Username1,password,Integer.parseInt(Age1),true);
            mMessageDatabaseReference.push().setValue(signUpClass);
            System.out.println("check123 "+Username1+" "+ Age1+ " "+password);
            attachDatabaseReadListener();
            SharedPreferences prefs = getSharedPreferences("confirmationActivity", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Username", Username1);
            editor.commit();
            Intent i = new Intent(ConfirmationActivity.this,NewsActivity.class);
            speak2("Hello Hamza!  Right now, Your screen has divided into 3!" +
                    " There's rectangle box at the top of the screen (It's ME)! then theres another rectangle box at the bottom of screen, " +
                    "(Its your library, where you can find your saved News! or you can explore the News! or Change settings etc etc)! and Theres a big square box at the center of the screen (Thats where you find the news)! " +
                    "To know what your screen look like! just tab anywhere in the screen and I'll tell you where you tapped!! and if " +
                    "you want me to repeat again for you, then long tapped on the screen! Now Waiting for your action.");

            startActivity(i);

        }

        //name="";
    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    //  textHide.setVisibility(View.GONE);

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
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                        //  Log.d("mom ",""+mom.getPICTURE());

                    }






                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }



}
