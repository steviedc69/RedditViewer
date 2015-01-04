package examen.redditviewer.model;

import java.io.Serializable;

/**
 * Created by Dries on 4/01/2015.
 */
public class Post implements Serializable{
    private String title;
    private String url;
    private String author;
    private String selftext;

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return this.title + " "+this.url;
    }
}
