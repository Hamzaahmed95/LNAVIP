package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 18-Aug-17.
 */

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sanadhussain on 18/08/16.
 */
public class NewsApi {

    public static final String API_KEY = "03b903f9d9bb433eb25cb8369dcc138b";
    public static final String TAG ="NewsApi";


    private byte[] getUrlBytes(String urlSpecs) throws IOException {
        Log.i(TAG,"in getUrlBytes() ");
        URL url = new URL(urlSpecs);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpecs);
            }

            int bytesRead =0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return out.toByteArray();
        }   finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        Log.i(TAG,"in getUrlString() ");
        return new String(getUrlBytes(urlSpec));
    }

    public List<NewsItem> fetchItems(){

        List<NewsItem> items = new ArrayList<>();
        Log.i(TAG,"in fetchItems() ");
        try {
            String url = Uri.parse("https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=03b903f9d9bb433eb25cb8369dcc138b")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();

            Log.i(TAG, "in try block() ");
            String jsonString = getUrlString(url);
            Log.i(TAG,"Received JSON: " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
           parseItems(items,jsonObject);
        }   catch (JSONException je) {
            Log.i(TAG, "Failed to parse JSON", je);
        }
        catch (IOException ioe){
            Log.e(TAG,"Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<NewsItem> items, JSONObject jsonBody)
            throws IOException, JSONException{

        JSONObject photosJsonObject2 = jsonBody;
        JSONArray photosJsonArray = photosJsonObject2.getJSONArray("articles");
        String name="";
        StringBuilder myName = new StringBuilder();
        for (int i=0;i<photosJsonArray.length();i++){
            JSONObject photoJsonObject = photosJsonArray.getJSONObject(i);

            NewsItem item = new NewsItem();
            if(!photoJsonObject.getString("author").equals(null)) {
                item.setAuthor(photoJsonObject.getString("author"));
                System.out.println("here-> " + item.getAuthor());
            }item.setTitle(photoJsonObject.getString("title"));
            item.setDescription(photoJsonObject.getString("description"));
            item.setmUrl(photoJsonObject.getString("urlToImage"));
            item.setPublishedAt(photoJsonObject.getString("publishedAt").substring(0,10));
            item.setVoice(name+". next News is: "+item.getTitle());
            //item.setUrl(photoJsonObject.getString("url_s"));
            items.add(item);
            if(name != null && name.length() > 0 && name.charAt(name.length() - 1)=='\'')
                name = name.substring(0, name.length() - 1);
            name=name+". "+item.getTitle();
        }
     //   s2=name;
       // System.out.println("full: "+s2);
    }
}