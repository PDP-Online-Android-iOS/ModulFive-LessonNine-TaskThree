package dev.ogabek.java.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private final boolean isNewProfile;
    private int photo;
    private final int profile;
    private final String fullName;
    private List<Photos> photos = new ArrayList<>();

    public Post(int profile, String fullName, int photo, boolean isNewProfile) {
        this.photo = photo;
        this.profile = profile;
        this.fullName = fullName;
        this.isNewProfile = isNewProfile;
    }

    public Post(int profile, String fullName, List<Photos> photos) {
        this.photos = photos;
        this.profile = profile;
        this.fullName = fullName;
        this.isNewProfile = false;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public boolean isNewProfile() {
        return isNewProfile;
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
