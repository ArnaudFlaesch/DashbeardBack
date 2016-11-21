package com.esgi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Arnaud on 01/11/2016.
 */
@Entity
@Table(name = "postit")
public class PostIt {

    @Id
    @GeneratedValue
    @Column(name = "idpostit")
    private Long idPostIt;
    @Column(name = "content")
    private String content;
    @NotNull
    @Column(name = "sizex")
    private float sizeX;
    @NotNull
    @Column(name = "sizey")
    private float sizeY;
    @NotNull
    @Column(name = "positionx")
    private float positionX;
    @NotNull
    @Column(name = "positiony")
    private float positionY;

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

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
}
