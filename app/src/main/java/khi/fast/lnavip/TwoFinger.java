package khi.fast.lnavip;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 10-Dec-17.
 */

public class TwoFinger extends AppCompatActivity {

    LinearLayout layout;
    int mPtrCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_finger);
        layout=(LinearLayout)findViewById(R.id.LinearLayout);
        System.out.println("here");
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = (event.getAction() & MotionEvent.ACTION_MASK);
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
                        System.out.println("Move "+mPtrCount);
                        break;

                }

                return true;
            }
        });
}


}
