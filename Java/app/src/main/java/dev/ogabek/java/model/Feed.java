package dev.ogabek.java.model;

import java.util.ArrayList;

public class Feed {

    private boolean isHeader = false;
    private Post post = null;
    private ArrayList<Story> stories = new ArrayList<>();

    public Feed() {
        this.isHeader = true;
    }

    public Feed(Post post) {
        this.post = post;
        isHeader = false;
    }

    public Feed(ArrayList<Story> stories) {
        this.stories = stories;
        this.isHeader = false;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public Post getPost() {
        return post;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }
}
