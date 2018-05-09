package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 05-May-18.
 */

/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Locale;


public class ShowContactsActivity extends AppCompatActivity {

    private static final String TAG = "ShowContactsActivity";

    public static final String ANONYMOUS = "anonymous";
    int messageCount=0;
    private LinearLayout mainScreen;
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN =1;

    private GestureDetector gd;
    TextToSpeech t2;
    private ListView mMessageListView;
    private ListView mUserListView;

    private TextView mTextView;
    private TextView name;

    private MessageAdapter mMessageAdapter;

    private ImageView dp2;
    private String url2;
    private ProgressBar mProgressBar;

    private ImageButton mPhotoPickerButton;


    private android.widget.Button mSendButton;
    private ImageView profile;
    public String NAME;
    public String status[];

    private static final int RC_PHOTO_PICKER =  2;

    private String mUsername;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private int count1=0;
    private FirebaseStorage firebaseStorage;
    private String username;
    private StorageReference mChatPhotoStorageReference;

    @Override
    protected void onCreate( final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_main_activity);
        mTextView = (TextView) findViewById(R.id.messageTextView);
        status=new String[10];
        mainScreen=(LinearLayout)findViewById(R.id.mainScreen);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            // System.out.println("Nusrat "+extra.getString("Username"));
            username=extra.getString("Username");


        }
        t2=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t2.setLanguage(Locale.US);
                    t2.setSpeechRate(1f);
                    t2.setPitch(0.505f);
                }
            }
        });
        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();

                return true;

            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                System.out.println("hello");

                speak2(status[count1]);
                count1++;


                return true;
            }
        }
        gd = new GestureDetector(this, new MyGestureDetector());


        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Contacts");
        Log.d("oncreate ", mMessageDatabaseReference.getDatabase().toString());


        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message

        mMessageListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd.onTouchEvent(motionEvent);
                System.out.println("hello there?");
                return false;
            }
        });


        // Enable Send button when there's text to send




        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("Contacts");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(username+"Auro"+issue.child("username").getValue().toString());
                        //  System.out.println("lala : "+issue.child("text").getValue());
                        if(username.equals(issue.child("username").getValue().toString())) {
                            status[messageCount] = issue.child("name").getValue().toString();
                            System.out.println("chala?");
                            messageCount++;
                        }



                    }
                    speak2("There are total "+messageCount+" contacts! single tab to check it!");


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        attachDatabaseReadListener();
    }

    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t2.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }



    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    String splited = friendlyMessage.getName();

                    mMessageAdapter.add(friendlyMessage);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    Log.d("ooo = ",f.getName());
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

