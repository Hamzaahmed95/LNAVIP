package khi.fast.lnavip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
    public static String s2;
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

    }



    TextToSpeech t1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s){

        Log.i(TAG,"in onCreateView() ");
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        b1 = (Button)view.findViewById(R.id.button);
        b2 = (Button)view.findViewById(R.id.button2);
        mNewsRecyclerView=(RecyclerView) view.findViewById(R.id.fragment_news_recycle_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();


        return view;
    }
    private void setupAdapter(){
        if(isAdded()) {
            mNewsRecyclerView.setAdapter(new NewsAdapter(mItems));
        }
        System.out.println("s2-> "+s2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  t1.speak(s2, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsRecyclerView.smoothScrollToPosition(count++);
            }
        });
    }
    private class NewsHolder extends RecyclerView.ViewHolder {
        private TextView Author;
        private LinearLayout l1;
        private LinearLayout l2;
        private TextView Title;
        private TextView Description;
        private ImageView URL;
        private TextView publishedAt;

        public NewsHolder(View itemView) {
            super(itemView);

            Author = (TextView) itemView.findViewById(R.id.author);
            Title = (TextView) itemView.findViewById(R.id.title);
            l1 = (LinearLayout)itemView.findViewById(R.id.hide);
            l2 = (LinearLayout)itemView.findViewById(R.id.hide2);
            Description = (TextView) itemView.findViewById(R.id.Desc);
            publishedAt = (TextView) itemView.findViewById(R.id.pub);
            URL = (ImageView)itemView.findViewById(R.id.image1);
        }

        public void bindGalleryItem(NewsItem item) {
            Author.setText(item.getAuthor());
            l2.setVisibility(View.GONE);
         //   System.out.println("here2->"+item.getAuthor());
            Title.setText(item.getTitle());
            l1.setVisibility(View.GONE);
            Description.setText(item.getDescription());
            boolean isPhoto = item.getmUrl() != null;
       //     System.out.print("hamza: " +item.getmUrl());
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
         //   String score = s2;
           // String[] arr = score.split("-");
            //Map<String, String> details = new HashMap<>();
            //for(int i=0;i<arr.length;i++){
             //   details.put("person" + i, arr[i]);
            //}
            //System.out.println(details.entrySet());
            /*if(!s2.equals(null)) {

                String news1 = arr[0];
                String news2 = arr[1];
                String news3 = arr[2];
                String news4 = arr[3];
                String news5 = arr[4];
                String news6 = arr[5];
                String news7 = arr[6];
                String news8 = arr[7];
                String news9 = arr[8];
                System.out.println(news1);
                System.out.println(news2);
                System.out.println(news3);
                System.out.println(news4);
                System.out.println(news5);
                System.out.println(news6);
                System.out.println(news7);
                System.out.println(news8);
                System.out.println(news9);
            }*/
        }
    }


}
