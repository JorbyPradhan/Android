package com.example.cleanarchitecturemvvm.data.remote

import com.example.cleanarchitecturemvvm.data.model.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<MoviePage>//Call<MovieResponse>

    @GET("trending/movie/week")
    suspend fun getTrendMovie(@Query("api_key") key: String): Response<MoviePage>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<MoviePage>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<MoviePage>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<MoviePage>

    @GET("movie/latest")
    suspend fun getLatest(@Query("api_key") key: String): Response<Movie>


    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<TvShowPage>

    @GET("trending/tv/week")
    suspend fun getTrendTv(@Query("api_key") key: String): Response<TvShowPage>

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<TvShowPage>

    @GET("tv/airing_today")
    suspend fun getAiringToday(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<TvShowPage>

    @GET("tv/on_the_air")
    suspend fun getOnTv(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<TvShowPage>

    @GET("tv/latest")
    suspend fun getLatestTv(@Query("api_key") key: String): Response<TvShow>

    //Detail
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Response<MovieDetail>

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(
        @Path("tv_id") id: Int,
        @Query("api_key") key: String
    ): Response<TvShowsDetail>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Response<Reviews>

    @GET("tv/{tv_id}/reviews")
    suspend fun getShowReviews(
        @Path("tv_id") id: Int,
        @Query("api_key") key: String
    ): Response<Reviews>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredit(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Response<Credit>

    @GET("tv/{tv_id}/credits")
    suspend fun getShowCredit(
        @Path("tv_id") id: Int,
        @Query("api_key") key: String
    ): Response<Credit>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MovieApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}