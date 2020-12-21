package com.example.moviedbmvvm.data.repositories

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviedbmvvm.data.db.AppDatabase
import com.example.moviedbmvvm.data.db.entity.Movie
import com.example.moviedbmvvm.data.db.entity.Testing
import com.example.moviedbmvvm.data.network.MovieApi
import com.example.moviedbmvvm.data.network.NetworkConnectionInterceptor
import com.example.moviedbmvvm.data.network.SafeApiRequest
import com.example.moviedbmvvm.data.network.responses.MovieResponse
import com.example.moviedbmvvm.data.preferences.PreferenceProvider
import com.example.moviedbmvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

private const val MINIMUNInterval = 6

@SuppressLint("ConstantLocale")
private val dateFormat = SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
)

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MoviesRepository(
    private val appDatabase: AppDatabase,
    private val api: MovieApi,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {


    private val movies = MutableLiveData<List<Movie>>()

    init {
        movies.observeForever {
            saveMovie(it)
        }
    }

    private fun saveMovie(movieList: List<Movie>) {
        Coroutines.io {
            getDateTime().let { prefs.saveLastSavedAt(it) }
            appDatabase.getMovieDao().deleteAll()
            appDatabase.getMovieDao().saveAllMovie(movieList)
        }

    }

     suspend fun getMovies(): DataSource.Factory<Int, Movie> {////LiveData<List<Movie>>
         return withContext(Dispatchers.IO){
             fetchMovie()
             appDatabase.getMovieDao().getAllMovie()
         }
     }
    suspend fun fetchMovie() { //: MutableLiveData<List<Movie>>?
      //  val testList: MutableLiveData<List<Movie>> = MutableLiveData()
       // val response = apiRequest { api.getPopularMovie() }
        // val response = api.getPopularMovie()
      //  Log.i("Mutable1", response.results.toString())
       // testList.postValue(response.results)
        /*response.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.i("Mutable",t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.i("Mutable1",response.body()?.results.toString())
                testList.postValue(response.body()?.results)
            }
        })*/
       // return testList
         val lastSavedAt = prefs.getLastSavedAt()
         if(lastSavedAt == null || isFetchNeeded(dateFormat.parse(lastSavedAt))){
             val response = apiRequest{api.getPopularMovie()}
             //response.results[0].page = response.page+1
             //Log.i("MyPage", "Page ${response.results[0].page} Hi ${response.results}")
             movies.postValue(response.results)
         }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun isFetchNeeded(savedAt: Date): Boolean {
        val date = getDateTime()
        val diff: Long = dateFormat.parse(date).getTime() - savedAt.getTime()
        val days: Long = diff / (24 * 60 * 60)
        val rest: Long = diff - (days * 24 * 60 * 60)
        val hrs: Long = rest / (60 * 60)
        val rest1: Long = rest - (hrs * 60 * 60)
        val min: Long = rest1 / 60
        val diffMinutes = diff / (60 * 1000)
        //Log.i("DateHour", diff.toString())
//val diffHours = diff / (60 * 60 * 1000) % 24
//val diffHours = diff / (60 * 1000) % 24
        Log.i("Minutes", diffMinutes.toString())
        return diffMinutes > MINIMUNInterval

    }

    private fun getDateTime(): String {
        val date = Date()
        return dateFormat.format(date)
    }

}