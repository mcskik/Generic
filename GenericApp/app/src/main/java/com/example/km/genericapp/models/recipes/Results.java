package com.example.km.genericapp.models.recipes;

/**
 * Recipe results class.
 */
public class Results {
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String input) {
        this.title = input;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String input) {
        this.href = input;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String input) {
        this.ingredients = input;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String input) {
        this.thumbnail = input;
    }
}
