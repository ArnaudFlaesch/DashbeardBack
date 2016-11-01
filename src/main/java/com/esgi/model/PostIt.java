package com.esgi.model;

/**
 * Created by Arnaud on 01/11/2016.
 */
public class PostIt {

    private Long idPostIt;
    private String content;
    private float sizeX;
    private float sizeY;

    public Long getIdPostIt() {
        return idPostIt;
    }

    public void setIdPostIt(Long idPostIt) {
        this.idPostIt = idPostIt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }
}
