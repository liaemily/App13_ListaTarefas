package br.edu.ifsp.dmos5.app13_listatarefas.model.entities;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private String title;
    private String description;
    private String date;
    private boolean favorite;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.favorite = false;
        Date now = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(now);
        this.date = date;

    }

    public Task(String title, String description, String date, boolean favorite) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.favorite = favorite;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getDate() {
        return date;
    }

}
