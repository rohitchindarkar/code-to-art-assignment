/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart.MovieList;

public class MovieList {

    public String name;
    public String releaseDate;
    public String category;
    public String posterImage;
    public String movieId;

    public MovieList(String name, String releaseDate,String category,String posterImage,String movieId) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.category = category;
        this.posterImage = posterImage;
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
