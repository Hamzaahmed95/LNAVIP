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
    private GestureDetector gestureDetector;
    int GLOBAL_TOUCH_POSITION_X = 0;
    int GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
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

    void handleTouch(MotionEvent m){
        //Number of touches

        System.out.println("getPointerCount "+m.getPointerCount());
        System.out.println("getPointerCount "+m);
        int pointerCount = m.getPointerCount();
        if(pointerCount == 2){

            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;
            TextView tv = (TextView) findViewById(R.id.testDiffText);
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    GLOBAL_TOUCH_POSITION_X = (int) m.getX(1);
                    actionString = "DOWN"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    tv.setText(actionString);
                    break;
                case MotionEvent.ACTION_UP:
                    GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
                    actionString = "UP"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    tv.setText(actionString);
                    break;
                case MotionEvent.ACTION_MOVE:
                    GLOBAL_TOUCH_CURRENT_POSITION_X = (int) m.getX(1);
                    int diff = GLOBAL_TOUCH_POSITION_X-GLOBAL_TOUCH_CURRENT_POSITION_X;
                    actionString = "Diff "+diff+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    tv.setText(actionString);
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    GLOBAL_TOUCH_POSITION_X = (int) m.getX(1);
                    actionString = "DOWN"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    tv.setText(actionString);
                    break;
                default:
                    actionString = "";
            }

            pointerCount = 0;
        }
        else {
            GLOBAL_TOUCH_POSITION_X = 0;
            GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
        }
    }
}
