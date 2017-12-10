package khi.fast.lnavip;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hamza Ahmed on 10-Dec-17.
 */

public class NormalKeyBoard extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    GestureLibrary mLibrary;


    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        setContentView(R.layout.normal_keyboard);
        super.onCreate(savedInstanceState);
        textView1=(TextView)findViewById(R.id.textView1);
        mLibrary = GestureLibraries.fromRawResource(NormalKeyBoard.this, R.raw.gesture);
        if (!mLibrary.load()) {
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        System.out.println(predictions.get(0).score);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {

            String letter = predictions.get(0).name;

            Toast.makeText(this, letter, Toast.LENGTH_SHORT).show();

            if(letter.contains("A"))  //when matches i print it to edittext
                textView1.setText("A");

        }
    }
}
