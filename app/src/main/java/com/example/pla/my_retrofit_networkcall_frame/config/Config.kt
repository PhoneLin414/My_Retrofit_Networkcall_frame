package com.example.pla.my_retrofit_networkcall_frame.config

import com.example.pla.my_retrofit_networkcall_frame.BuildConfig

object Config {

    val BASE_URL = if (BuildConfig.BASE_URL) {
        "https://api.themoviedb.org/3/"
    } else {
        "test:https://api.themoviedb.org/3/"
    }


    const val API_KEY = "4ea44fa5e92d393794cd1d61c2fc2919"

}