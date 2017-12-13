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
        Assistant=(LinearLayout)view.findViewById(R.id.Assistant);
        Assistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak1("Hey It's me! Double tap to open me up! or single tab to back again!");
                Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                i.putExtra("ID","Assistant");
                startActivity(i);
            }
        });
        NewsAssistant=(LinearLayout)view.findViewById(R.id.NewsAssistant);
        NewsAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak1("Hey I am your library! Double tap to open me up! or single tab to back again!");
                Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                i.putExtra("ID","NewsAssistant");
                startActivity(i);
            }
        });
        mNewsRecyclerView=(RecyclerView) view.findViewById(R.id.fragment_news_recycle_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               speak1("Hello Hamza!  Right now, Your screen has divided into 3!" +
                       " There's rectangle box at the top of the screen (It's ME)! then theres another rectangle box at the bottom of screen, " +
                       "(Its your library, where you can find your saved News! or you can explore the News! or Change settings etc etc)! and Theres a big square box at the center of the screen (Thats where you find the news)! " +
                       "To know what your screen look like! just tab anywhere in the screen and I'll tell you where you tapped!! and if " +
                       "you want me to repeat again for you, then long tapped on the screen! Now Waiting for your action.");

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
                    speak1("Hey You tapped on the news! Double tap to check the news! or Single tab to back again");
                    Intent i = new Intent(getActivity(),Confirmation2Activity.class);
                    i.putExtra("ID","News");
                    startActivity(i);
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

}

