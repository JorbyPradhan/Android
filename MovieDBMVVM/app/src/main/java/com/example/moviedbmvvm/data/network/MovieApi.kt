package com.example.moviedbmvvm.data.network

import com.example.moviedbmvvm.data.db.entity.Testing
import com.example.moviedbmvvm.data.network.responses.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MovieApi {

    @GET("movie/popular?api_key=7142a40c54b2c690a1f53e697a1d51aa")
    suspend fun getPopularMovie(): Response<MovieResponse>//Call<MovieResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor:NetworkConnectionInterceptor
        ) : MovieApi{
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