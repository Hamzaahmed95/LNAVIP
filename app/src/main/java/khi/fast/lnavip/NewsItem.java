package khi.fast.lnavip;

/**
 * Created by Hamza Ahmed on 18-Aug-17.
 */

public class NewsItem {
    private String Author;
    private String mId;
    private String mUrl;
    private String title;
    private String Description;
    private String publishedAt;
    private String voice;

    public NewsItem(String author, String mId, String mUrl, String title, String description, String publishedAt, String Voice) {
        Author = author;
        this.mId = mId;
        this.mUrl = mUrl;
        this.title = title;
        Description = description;
        this.publishedAt = publishedAt;
        voice=Voice;

    }

    public NewsItem() {
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
