package com.example.jorge.infoglobo.data.source.cloud.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * Model for get API Contents with this field
 */
public class Contents implements Serializable {

    @SerializedName("conteudos")
    private List<News> contents;

    public List<News> getContents() {
        return contents;
    }

    public void setContents(List<News> contents) {
        this.contents = contents;
    }
}
