package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Hamza Ahmed on 12-Dec-17.
 */

public class Confirmation2Activity extends AppCompatActivity {


    private GestureDetector gestureDetector;
    private RelativeLayout layout;
    private String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(RelativeLayout) findViewById(R.id.activity_main);
        Bundle extra=this.getIntent().getExtras();

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
                }
                else if(activity.equals("NewsAssistant")){
                    System.out.println("NewsAssistant");
                }
                else{
                    System.out.println("News");
                    Intent i = new Intent(Confirmation2Activity.this,NewsWhizActivity.class);
                    startActivity(i);
                }

                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){

                System.out.println("Single");
                Intent i = new Intent(Confirmation2Activity.this,NewsActivity.class);
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

}
