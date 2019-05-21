package com.example.jorge.infoglobo.data.source.cloud.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

    @SerializedName("autores")
    private List<String> actors;
    @SerializedName("titulo")
    private String title;
    @SerializedName("subTitulo")
    private String subTitle;
    @SerializedName("publicadoEm")
    private String publishedIn;
    private String author;
    @SerializedName("imagens")
    private List<Images> image;
    @SerializedName("texto")
    private String text;
    @SerializedName("secao")
    private Editoria editoria;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPublishedIn() {
        return publishedIn;
    }

    public void setPublishedIn(String publishedIn) {
        this.publishedIn = publishedIn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Images> getImage() {
        return image;
    }

    public void setImage(List<Images> image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Editoria getEditoria() {
        return editoria;
    }

    public void setEditoria(Editoria editoria) {
        this.editoria = editoria;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
