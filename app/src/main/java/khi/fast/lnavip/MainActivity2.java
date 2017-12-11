package khi.fast.lnavip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.InputStream;

/**
 * Created by Hamza Ahmed on 11-Dec-17.
 */

public class MainActivity2 extends AppCompatActivity {

    private FaceOverLayView mFaceOverlayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity2);
        mFaceOverlayView = (FaceOverLayView) findViewById( R.id.face_overlay );

        InputStream stream = getResources().openRawResource( R.raw.face);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        mFaceOverlayView.setBitmap(bitmap);
    }


    }
