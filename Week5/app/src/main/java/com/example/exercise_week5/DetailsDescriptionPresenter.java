package com.example.exercise_week5;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;

import com.example.exercise_week5.model.Movie;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(ViewHolder vh, Object item) {

        Movie movie = (Movie) item;
        if (movie != null) {
            vh.getTitle().setText(movie.getTitle());
            vh.getSubtitle().setText(movie.getStudio());
            vh.getBody().setText(movie.getDescription());
        }
    }
}
