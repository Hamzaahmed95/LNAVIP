package khi.fast.lnavip;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class NewsWhiz extends Fragment {
    private RecyclerView mNewsRecyclerView;
    private static final String TAG ="NewsFragment";
    private List<NewsItem> mItems = new ArrayList<>();
    TextToSpeech t1;
    TextToSpeech t2;
    private GestureDetector gestureDetector;
    private String newsHeadlines="";
    private String newsDetails="";
    private GestureDetector gd;
    public static NewsWhiz newInstance(){
        return new NewsWhiz();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        Log.i(TAG,"in onCreate() ");

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new FetchItemsTask().execute();
        t1=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(0.85f);
                    t1.setPitch(0.505f);
                }
            }
        });
        t2=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t2.setLanguage(Locale.US);
                    t2.setSpeechRate(1f);
                    t2.setPitch(0.505f);
                }
            }
        });




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s){

        Log.i(TAG,"in onCreateView() ");
        View view = inflater.inflate(R.layout.news_whiz,container,false);
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
        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                System.out.println("Double");
                speak1(newsDetails);
                return true;
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e){
                t1.stop();
                speak1(newsHeadlines);

                return true;
            }
        }
        gestureDetector = new GestureDetector(getContext(), new GestureListener());

        gd = new GestureDetector(getContext(), new MyGestureDetector());

        mNewsRecyclerView=(RecyclerView) view.findViewById(R.id.fragment_news_recycle_view);
        mNewsRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mNewsRecyclerView);
        setupAdapter();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {




                speak2("Domestic News on the Screen now! Just single tap on the screen to play the news!" +
                        "If you want more help? swipe down the screen!. Now waiting for your action! ");

            }
        }, 1500);



        return view;
    }









    private void speak1(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t1.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }
    private void speak2(String word){

        HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Hello");
        t2.speak(word, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

    }



    private void setupAdapter(){
        if(isAdded()) {
            mNewsRecyclerView.setAdapter(new NewsAdapter(mItems));
        }
    }

    private class NewsHolder extends RecyclerView.ViewHolder{
        private TextView Author;
        private LinearLayout l1;
        private LinearLayout l2;
        private TextView Title;
        private TextView Description;
        private ImageView URL;
        private TextView publishedAt;
        private LinearLayout fragment_news_recycle_view;

        public NewsHolder(View itemView) {
            super(itemView);

            Author = (TextView) itemView.findViewById(R.id.author);
            Title = (TextView) itemView.findViewById(R.id.title);
            l1 = (LinearLayout)itemView.findViewById(R.id.hide);
            l2 = (LinearLayout)itemView.findViewById(R.id.hide2);
            Description = (TextView) itemView.findViewById(R.id.Desc);
            publishedAt = (TextView) itemView.findViewById(R.id.pub);
            URL = (ImageView)itemView.findViewById(R.id.image1);



            fragment_news_recycle_view=(LinearLayout)itemView.findViewById(R.id.fragment_news_recycle_view);

        }


        public void bindGalleryItem(final NewsItem item,final int pos) {
            fragment_news_recycle_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  /*  speak1("Hey You tapped on the news! Double tap to check the news! or Single tab to back again");
                    Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                    i.putExtra("ID","News");
                    startActivity(i);*/

                    System.out.println("hamza->"+pos);
                    System.out.println("TITLE: "+item.getTitle());
                }
            });
            fragment_news_recycle_view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);

                    newsHeadlines=item.getTitle();
                    newsDetails=item.getDescription();
                    gd.onTouchEvent(motionEvent);
                    return false;
                }
            });
            fragment_news_recycle_view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                   Intent i = new Intent(getActivity(),NewsActivity.class);
                    startActivity(i);
                    return false;
                }
            });

            System.out.println("TITLE"+item.getTitle());
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
            System.out.println("voice2: "+item.getVoice());
        }


    }

    public void onSwipeBottom(){
        Intent i = new Intent(getActivity(),NewsHelpActivity.class);
        startActivity(i);

    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
        private List<NewsItem> mGalleryItems;
        public NewsAdapter(List<NewsItem> galleryItems){
            mGalleryItems = galleryItems;
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup view ,int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_news,view,false);
            System.out.println("onCreateView()");
            return new NewsHolder(v);
        }


        @Override
        public void onBindViewHolder(NewsHolder photoHolder, int pos){
            NewsItem galleryItem = mGalleryItems.get(pos);
            System.out.println("position: "+galleryItem.getTitle());
            photoHolder.bindGalleryItem(galleryItem,pos);
        }

        @Override
        public int getItemCount(){
            System.out.println("size: "+mGalleryItems.size());
            System.out.println("getItemCount");
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

}

