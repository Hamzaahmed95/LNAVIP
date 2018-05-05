package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 10-Dec-17.
 */

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Hamza Ahmed on 17-Jul-17.
 */

public class SplashScreen extends Activity {

    ProgressBar mprogressBar;
    private FirebaseDatabase mFirebaseDatabase;

    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.anim_down);
        ImageView img =(ImageView)findViewById(R.id.imageView);
        img.setAnimation(anim1);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        sharedPref = getSharedPreferences("confirmationActivity",MODE_PRIVATE);
        final String Username = sharedPref.getString("Username",
                "notfound");
        System.out.println("Username: "+Username);



        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        Handler handler = new Handler();



        if(Username.equals("notfound"))
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();


                }
            }, 3000);
        }

        else{
            handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("UserProfile").orderByChild("username");

                mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // dataSnapshot is the "issue" node with all children with id 0
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"

                                if(issue.child("username").getValue().equals(Username)){
                                    if(issue.child("status").getValue().equals(true)){
                                        System.out.println("Permission granted!");
                                        Intent i = new Intent(SplashScreen.this, NewsActivity.class);
                                        i.putExtra("Username",Username);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        }, 0);

        }
    }
    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}