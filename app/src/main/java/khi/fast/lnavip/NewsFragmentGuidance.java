package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 09-May-18.
 */


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Hamza Ahmed on 14-Mar-18.
 */

public class NewsFragmentGuidance extends AppCompatActivity {

    LinearLayout l1;
    LinearLayout NewsAssistant;
    LinearLayout fragment_news_recycle_view;
    Vibrator v;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_fragment_guideline);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        l1 = (LinearLayout)findViewById(R.id.Assistant);
        NewsAssistant = (LinearLayout)findViewById(R.id.NewsAssistant);
        NewsAssistant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count>5 && count<9) {
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });
        fragment_news_recycle_view=(LinearLayout)findViewById(R.id.fragment_news_recycle_view);
        fragment_news_recycle_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count>2 && count<6) {
                    v.vibrate(500);
                    count++;
                }
                System.out.println("HI");
                return false;
            }
        });
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(count<3) {
                    v.vibrate(500);
                    count++;
                }
                return false;
            }
        });


    }
}

