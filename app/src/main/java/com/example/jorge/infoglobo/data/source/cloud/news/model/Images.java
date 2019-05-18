package com.example.jorge.infoglobo.data.source.cloud.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model for get API Images with this field
 */
public class Images implements Serializable {

    @SerializedName("autor")
    private String author;
    @SerializedName("legenda")
    private String subtitle;
    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
