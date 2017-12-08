package khi.fast.lnavip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Hamza Ahmed on 08-Dec-17.
 */

public class OneHandedBrailleKeyboard  extends AppCompatActivity {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;

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
    private GestureDetector gd;
    private GestureDetector gd1;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private GestureDetector gd4;
    private GestureDetector gd5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_braille_keyboard);

        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);
        layout5=(LinearLayout)findViewById(R.id.layout5);
        layout6=(LinearLayout)findViewById(R.id.layout6);
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
                return false;
            }
        });


      }
    public void BrailleLanguage(int count1,int count2,int count3,int count4,int count5,int count6){
        if(count1!=0 &count2==0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("A");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2!=0&count3==0&count4==0&count5==0&count6==0){
            System.out.println("C");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }
        else if(count1!=0 &count2==0&count3!=0&count4==0&count5==0&count6==0){
            System.out.println("B");
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            count5=0;
            count6=0;

        }

    }
}
