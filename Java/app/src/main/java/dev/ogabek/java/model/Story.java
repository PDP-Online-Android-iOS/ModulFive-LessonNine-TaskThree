package dev.ogabek.java.model;

public class Story {

    private final boolean createStory;
    private int photo;
    private int profile;
    private String fullName;

    public Story() {
        createStory = true;
    }

    public Story(int profile, String fullName, int photo) {
        this.photo = photo;
        this.profile = profile;
        this.fullName = fullName;
        createStory = false;
    }

    public boolean isCreateStory() {
        return createStory;
    }

    public int getPhoto() {
        return photo;
    }

    public int getProfile() {
        return profile;
    }

    public String getFullName() {
        return fullName;
    }

}
