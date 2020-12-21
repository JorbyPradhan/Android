package com.example.moviedbmvvm.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbmvvm.R
import com.example.moviedbmvvm.data.db.entity.Movie
import com.example.moviedbmvvm.data.db.entity.Testing
import com.example.moviedbmvvm.util.ApiException
import com.example.moviedbmvvm.util.Coroutines
import com.example.moviedbmvvm.util.NoInternetException
import kotlinx.android.synthetic.main.home_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment() , KodeinAware {
    override val kodein by kodein()

   var pageListLiveData1 : LiveData<PagedList<Movie>>?= null
    private lateinit var viewModel: HomeViewModel
    private lateinit var movieAdapter : MovieAdapter
    private val factory: HomeViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        home_rec.layoutManager = GridLayoutManager(context,3)
        home_rec.setHasFixedSize(true)
        movieAdapter = MovieAdapter(context?.applicationContext!!)
        bindUi()

    }

    private fun bindUi() = Coroutines.main {
        try {
        val pageList = viewModel.pageListLiveData.await()

        pageListLiveData1 = LivePagedListBuilder<Int,Movie>(
            pageList,5
        ).build()
        if (pageListLiveData1!=null){
        pageListLiveData1!!.observe(viewLifecycleOwner, Observer {
            movieAdapter.submitList(it as PagedList<Movie>?)
            home_rec.adapter = movieAdapter
        })
        }
        }catch (e: ApiException){
            e.printStackTrace()
        }catch (e: NoInternetException){
            Toast.makeText(context,"Open Internet",Toast.LENGTH_LONG).show()
        }
    }


}