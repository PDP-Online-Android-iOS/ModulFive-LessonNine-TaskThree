package dev.ogabek.kotlin.model

import java.util.ArrayList

class Post {
    val isNewProfile: Boolean
    var photo = 0
    val profile: Int
    val fullName: String
    var photos: List<Photos> = ArrayList<Photos>()

    constructor(profile: Int, fullName: String, photo: Int, isNewProfile: Boolean) {
        this.photo = photo
        this.profile = profile
        this.fullName = fullName
        this.isNewProfile = isNewProfile
    }

    constructor(profile: Int, fullName: String, photos: List<Photos>) {
        this.photos = photos
        this.profile = profile
        this.fullName = fullName
        isNewProfile = false
    }
}