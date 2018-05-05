package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 05-May-18.
 */

public class SaveNews {

    private String NewsTitle;
    private String Username;
    private String Date;


    public SaveNews() {
    }

    public SaveNews( String username,String newsTitle, String date) {


        Username = username;
        NewsTitle = newsTitle;
        Date = date;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
