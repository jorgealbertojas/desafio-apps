package com.example.jorge.infoglobo.data.source.cloud.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Model for get API News with this field
 */
public class News<T> implements Serializable  {

    /**
     * Returns List Movies
     */
    @SerializedName("conteudos")
    public List<T> results;


}
