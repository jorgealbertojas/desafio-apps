package com.example.jorge.infoglobo.data.source.cloud.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model for get API Editoria with this field
 */
public class Editoria implements Serializable {

    @SerializedName("nome")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
