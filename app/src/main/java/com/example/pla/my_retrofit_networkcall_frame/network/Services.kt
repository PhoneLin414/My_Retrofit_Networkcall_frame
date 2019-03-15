package com.example.pla.my_retrofit_networkcall_frame.network

import com.example.pla.my_retrofit_networkcall_frame.obj.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Services {

    @GET("movie/now_playing")
    fun getNowPlayingMovieList(@Query("api_key") API_KEY: String, @Query("page") PAGE: Int): Observable<MovieListResponse>

}