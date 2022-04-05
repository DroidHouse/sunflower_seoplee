package com.seoplee.sunflower_study_seoplee.data

import com.google.gson.annotations.SerializedName

data class UnsplashUser(
    val name: String,
    val username: String
) {
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}