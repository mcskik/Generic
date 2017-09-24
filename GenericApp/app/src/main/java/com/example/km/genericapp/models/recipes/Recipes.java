package com.example.km.genericapp.models.recipes;

import java.util.List;

/**
 * Recipes class.
 */
public class Recipes {
    private String title;
    private String version;
    private String href;
    private List<Results> results;

    public String getTitle() {
        return title;
    }

    public void setTitle(String input) {
        this.title = input;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String input) {
        this.version = input;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String input) {
        this.href = input;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> input) {
        this.results = input;
    }
}
