package com.example.pla.my_retrofit_networkcall_frame.obj

data class MovieListResponse(val page: Int,val total_results: Int,val total_pages: Int,val results: List<MovieDataResult>) {
}