package khi.fast.lnavip;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Hamza Ahmed on 18-Aug-17.
 */

public class NewsFragment extends Fragment {
    private Button b1;
    private RecyclerView mNewsRecyclerView;
    private static final String TAG ="NewsFragment";
    private List<NewsItem> mItems = new ArrayList<>();
    private Button b2;
    private String s1;
    private int count=0;
    TextToSpeech t1;
    private LinearLayout Assistant;
    private LinearLayout NewsAssistant;
    public static String s2;
    private GestureDetector gestureDetector;
    private GestureDetector gd;
    private GestureDetector gd2;
    private GestureDetector gd3;
    private RelativeLayout Relative;
    private String Name;
    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        Log.i(TAG,"in onCreate() ");

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        s1="null";
        new FetchItemsTask().execute();
        t1=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(1f);
                    t1.setPitch(0.905f);
                }
            }
        });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s){

        Log.i(TAG,"in onCreateView() ");
        View view = inflater.inflate(R.layout.fragment_news,container,false);

        Bundle extra = getActivity().getIntent().getExtras();
        if (extra != null) {
            System.out.println("Nusrat "+extra.getString("Username"));
            Name=extra.getString("Username");


        }
        Relative = (RelativeLayout)view.findViewById(R.id.Relative);


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
                                // onSwipeRight();
                            } else {
                                //   onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
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
        gestureDetector = new GestureDetector(getContext(), new GestureListener());
        Relative.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);

                return false;
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
                speak1("Hey It's me Settings! Double tap to open me up! or single tab to back again!");
                Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                i.putExtra("ID","Assistant");
                i.putExtra("Username",Name);
                startActivity(i);
                return true;
            }
        }
        class MyGestureDetector2 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();

                return true;

            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                speak1("Hey I am your library! Double tap to open me up! or single tab to back again!");
                Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                i.putExtra("ID","NewsAssistant");
                i.putExtra("Username",Name);
                startActivity(i);

                return true;
            }
        }
        class MyGestureDetector3 extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();

                return true;

            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                i.putExtra("ID","News");
                i.putExtra("Username",Name);

                speak1("Hey You tapped on the news! Double tap to check the news! or Single tab to back again");



                startActivity(i);
                return true;
            }
        }
        gestureDetector = new GestureDetector(getContext(), new GestureListener());

        gd = new GestureDetector(getContext(), new MyGestureDetector());
        gd2 = new GestureDetector(getContext(), new MyGestureDetector2());
        gd3 = new GestureDetector(getContext(), new MyGestureDetector3());


        Assistant=(LinearLayout)view.findViewById(R.id.Assistant);
        Assistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Assistant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });
        NewsAssistant=(LinearLayout)view.findViewById(R.id.NewsAssistant);
        NewsAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        NewsAssistant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gd2.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
        mNewsRecyclerView=(RecyclerView) view.findViewById(R.id.fragment_news_recycle_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               speak1("GoodMorning"+Name+" I hope you have a good day :)");

            }
        }, 1000);



        return view;
    }
    private void speak1(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }



    private void setupAdapter(){
        if(isAdded()) {
            mNewsRecyclerView.setAdapter(new NewsAdapter(mItems));
        }
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
        private TextView Author;
        private LinearLayout l1;
        private LinearLayout l2;
        private TextView Title;
        private TextView Description;
        private ImageView URL;
        private TextView publishedAt;

        private LinearLayout fragment_weather_recycle_view;

        public NewsHolder(View itemView) {
            super(itemView);

            Author = (TextView) itemView.findViewById(R.id.author);
            Title = (TextView) itemView.findViewById(R.id.title);
            l1 = (LinearLayout)itemView.findViewById(R.id.hide);
            l2 = (LinearLayout)itemView.findViewById(R.id.hide2);
            Description = (TextView) itemView.findViewById(R.id.Desc);
            publishedAt = (TextView) itemView.findViewById(R.id.pub);
            URL = (ImageView)itemView.findViewById(R.id.image1);
            fragment_weather_recycle_view=(LinearLayout)itemView.findViewById(R.id.fragment_news_recycle_view);

        }

        public void bindGalleryItem(NewsItem item) {
            fragment_weather_recycle_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            fragment_weather_recycle_view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gd3.onTouchEvent(motionEvent);
                    gestureDetector.onTouchEvent(motionEvent);

                    return false;
                }
            });
            Author.setText(item.getAuthor());
            l2.setVisibility(View.GONE);
            Title.setText(item.getTitle());
            l1.setVisibility(View.GONE);
            Description.setText(item.getDescription());
            boolean isPhoto = item.getmUrl() != null;
            if (isPhoto) {
                Glide.with(URL.getContext())
                        .load(item.getmUrl())
                        .into(URL);
            }
            publishedAt.setText(item.getPublishedAt());

            System.out.println("voice2: "+item.getVoice());
        }
    }
    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder>{
        private List<NewsItem> mGalleryItems;

        public NewsAdapter(List<NewsItem> galleryItems){
            mGalleryItems = galleryItems;
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup view ,int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_news,view,false);
            return new NewsHolder(v);
        }

        @Override
        public void onBindViewHolder(NewsHolder photoHolder, int pos){
            NewsItem galleryItem = mGalleryItems.get(pos);
            photoHolder.bindGalleryItem(galleryItem);
        }

        @Override
        public int getItemCount(){
            return mGalleryItems.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<NewsItem>> {
        @Override
        protected List<NewsItem>  doInBackground(Void... params){
            Log.i(TAG,"in FetchItemsTask() ");


            return new NewsApi().fetchItems();

        }

        @Override
        protected void onPostExecute(List<NewsItem> items){
            mItems = items;
            setupAdapter();

        }
    }
    public void onSwipeBottom(){
        Intent i = new Intent(getActivity(),NewsFragmentGuidance.class);
        startActivity(i);
    }
}

