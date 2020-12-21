package com.example.cleanarchitecturemvvm.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.example.cleanarchitecturemvvm.data.model.TvShow
import com.example.cleanarchitecturemvvm.data.remote.MovieApi
import com.example.cleanarchitecturemvvm.data.remote.NetworkConnectionInterceptor
import com.example.cleanarchitecturemvvm.data.repository.MovieRepository
import com.example.cleanarchitecturemvvm.data.repository.TvRepository
import com.example.cleanarchitecturemvvm.databinding.FragmentHomeBinding
import com.example.cleanarchitecturemvvm.domain.CallBackMovieImpl
import com.example.cleanarchitecturemvvm.domain.CallBackTvShowImpl
import com.example.cleanarchitecturemvvm.presentation.presenter.MoviePresenter
import com.example.cleanarchitecturemvvm.util.Coroutines
import com.google.android.material.navigation.NavigationView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import iammert.com.view.scalinglib.ScalingLayoutListener
import iammert.com.view.scalinglib.State
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var factory: HomeViewModelFactory
    private lateinit var homeBinding: FragmentHomeBinding
    private var listSize: Int = 0
    private var page: Int = 1
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        homeBinding.lifecycleOwner = this
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  (requireActivity() as AppCompatActivity).setSupportActionBar(tool_bar)
        Objects.requireNonNull(requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navController = Navigation.findNavController(view)
        radio_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            movie_type.text = "${radio.text}"
            scalingLayout.collapse()
        }
        scalingLayout.setListener(object : ScalingLayoutListener {
            override fun onProgress(progress: Float) {
                if (progress > 0) {
                    fabIcon.visibility = View.INVISIBLE
                }

                if (progress < 1) {
                    radio_group.visibility = View.INVISIBLE
                }
            }

            override fun onExpanded() {
                ViewCompat.animate(fabIcon).alpha(0F).setDuration(200).start()
                ViewCompat.animate(radio_group).alpha(1.0F).setDuration(200).setListener(object :
                    ViewPropertyAnimatorListener {
                    override fun onAnimationEnd(view: View?) {
                        fabIcon.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(view: View?) {

                    }

                    override fun onAnimationStart(view: View?) {
                        radio_group.visibility = View.VISIBLE
                    }

                }).start()
            }

            override fun onCollapsed() {
                ViewCompat.animate(fabIcon).alpha(1.0F).setDuration(150).start()
                ViewCompat.animate(radio_group).alpha(0F).setDuration(150).setListener(object :
                    ViewPropertyAnimatorListener {
                    override fun onAnimationEnd(view: View?) {
                        radio_group.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(view: View?) {

                    }

                    override fun onAnimationStart(view: View?) {
                        fabIcon.visibility = View.VISIBLE
                    }

                }).start()
            }

        })
        scalingLayout.setOnClickListener {
            if (scalingLayout.state == State.COLLAPSED) {
                scalingLayout.expand()
            } else {
                scalingLayout.collapse()
            }
        }
        /* rootLayout.setOnClickListener {
             Toast.makeText(context," On checked change :"+
                     " ${scalingLayout.state}",
                 Toast.LENGTH_SHORT).show()
             if (scalingLayout.state == State.EXPANDED) {
                 scalingLayout.collapse()
             }
         }*/
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        factory = HomeViewModelFactory(
            CallBackMovieImpl(
                MovieRepository(
                    MovieApi(
                        NetworkConnectionInterceptor(requireContext())
                    )
                )
            ),
            CallBackTvShowImpl(
                TvRepository(
                    MovieApi(
                        NetworkConnectionInterceptor(requireContext())
                    )
                )
            )
        )
        homeViewModel =
            ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        //bindui()
        tabPreviousPage.isEnabled = page >= 2
        movie_page.text = "Page-${page}"

        bindMovieUI()
        homeBinding.presenter = object : MoviePresenter {
            override fun onClick() {
                // movie_type.text =
                if ((requireActivity() as AppCompatActivity).supportActionBar?.title == "TVShows") {
                    lifecycleScope.launch {
                        homeViewModel.changeTvShowType(movie_type.text.toString().trim())
                    }
                } else {
                    lifecycleScope.launch {
                        homeViewModel.changeType(movie_type.text.toString().trim())
                    }
                }
                Toast.makeText(
                    context, " On checked change :" +
                            " ${movie_type.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        //scroll()
        tabNextPage.setOnClickListener {
            homeViewModel.nextPage( (requireActivity() as AppCompatActivity).supportActionBar?.title!!,movie_type.text.toString().trim())
            page += 1
            tabPreviousPage.isEnabled = page > 1
            movie_page.text = "Page-${page}"
        }
        tabPreviousPage.setOnClickListener {
            if (page < 2) {
                tabPreviousPage.isEnabled = false
            } else {
                homeViewModel.previousPage( (requireActivity() as AppCompatActivity).supportActionBar?.title!!,movie_type.text.toString().trim())
                page -= 1
                movie_page.text = "Page-${page}"
            }
        }
        tabTvShow.setOnClickListener {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "TVShows"
            radioNow.text = getString(R.string.airing_today)
            radioUp.text = getString(R.string.on_tv)
            page = 1
            movie_page.text = "Page-${page}"
            radioPop.isChecked = true
            bindTvUI()
        }
        tabMovie.setOnClickListener {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Movies"
            bindMovieUI()
            page = 1
            movie_page.text = "Page-${page}"
            radioPop.isChecked = true
            radioNow.text = getString(R.string.now_playing)
            radioUp.text = getString(R.string.upcoming)
        }
        /*tabMovie.setOnClickListener { viewModel.toggleBookmark() }
        tabTvShow.setOnClickListener { viewModel.toggleInLibrary() }
        tabNextPage.setOnClickListener { viewModel.nextPage() }
        tabPreviousPage.setOnClickListener { viewModel.previousPage() }*/


    }

    private fun bindTvUI() = Coroutines.main {
        homeViewModel.page = 1
        val trendTvList = homeViewModel.mutableLiveTrendTvData.await()
        trendTvList.observe(viewLifecycleOwner, Observer {
            listSize = it.size
            initTrendTvRecyclerView(it.toTvShowItem())
        })
        val popularShowList = homeViewModel.mutableLiveTvData.await()
        popularShowList?.observe(viewLifecycleOwner, Observer {
            initTvShowRecyclerView(it.toTvShowItem())
        })
        val latest = homeViewModel.mutableLiveDataLatestTv.await()
        latest.observe(viewLifecycleOwner, Observer {
            Log.i("latest","Lasted${it.posterPath}")
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .error(R.mipmap.ironman)
                .into(latest_image)
        })
    }

    private fun initTrendTvRecyclerView(toTvShowItem: List<HomeTvShow>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toTvShowItem)
        }

        rec_trend.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val trendShowDetail = item as HomeTvShow
           // scroll()
            val bundle = Bundle()
            bundle.putString("Type","Tv")
            bundle.putString("Title",trendShowDetail.tvShow.name)
            bundle.putString("Poster",trendShowDetail.tvShow.backdropPath)
            bundle.putString("Poster1",trendShowDetail.tvShow.posterPath)
            bundle.putDouble("rating",trendShowDetail.tvShow.voteAverage)
            bundle.putString("overView",trendShowDetail.tvShow.overview)
            bundle.putString("release",trendShowDetail.tvShow.firstAirDate)
            bundle.putInt("id",trendShowDetail.tvShow.id)
            bundle.putInt("genreId",trendShowDetail.tvShow.genreIds.size)
            navController.navigate(R.id.action_nav_home_to_detailFragment, bundle)
        }
    }

    private fun initTvShowRecyclerView(toTvShowItem: List<HomeTvShow>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>()
            .apply {
                addAll(toTvShowItem)
            }
        rec_movie.apply {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val tvShowDetail = item as HomeTvShow
            val bundle = Bundle()
            bundle.putString("Type","Tv")
            bundle.putString("Title",tvShowDetail.tvShow.name)
            bundle.putString("Poster",tvShowDetail.tvShow.backdropPath)
            bundle.putString("Poster1",tvShowDetail.tvShow.posterPath)
            bundle.putDouble("rating",tvShowDetail.tvShow.voteAverage)
            bundle.putString("overView",tvShowDetail.tvShow.overview)
            bundle.putString("release",tvShowDetail.tvShow.firstAirDate)
            bundle.putInt("id",tvShowDetail.tvShow.id)
            bundle.putInt("genreId",tvShowDetail.tvShow.genreIds.size)
            navController.navigate(R.id.action_nav_home_to_detailFragment, bundle)
        }
    }

    private fun bindMovieUI() = Coroutines.main {
        homeViewModel.page = 1
        val latest = homeViewModel.mutableLiveLatest.await()
        latest.observe(viewLifecycleOwner, Observer {
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .error(R.mipmap.ironman)
                .into(latest_image)

        })
        val trendMovieList = homeViewModel.mutableLiveTrendData.await()
        trendMovieList.observe(viewLifecycleOwner, Observer {
            listSize = it.size
            initTrendRecyclerView(it.toMovieItem())
        })
        val popularMovieList = homeViewModel.mutableLiveMovieData.await()
        popularMovieList?.observe(viewLifecycleOwner, Observer {
            initMovieRecyclerView(it.toMovieItem())
        })
    }

    private fun initMovieRecyclerView(toMovieItem: List<HomeMovie>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>()
            .apply {
                addAll(toMovieItem)
            }
        rec_movie.apply {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val movieDetail = item as HomeMovie
            val bundle = Bundle()
            bundle.putString("Type","Movie")
            bundle.putString("Title",movieDetail.movie.title)
            bundle.putString("Poster",movieDetail.movie.backdropPath)
            bundle.putString("Poster1",movieDetail.movie.posterPath)
            bundle.putDouble("rating",movieDetail.movie.voteAverage)
            bundle.putString("overView",movieDetail.movie.overview)
            bundle.putString("release",movieDetail.movie.releaseDate)
            bundle.putBoolean("adult",movieDetail.movie.adult)
            bundle.putInt("id",movieDetail.movie.id)
            bundle.putInt("genreId",movieDetail.movie.genreIds.size)
            navController.navigate(R.id.action_nav_home_to_detailFragment, bundle)
        }
    }

    private fun initTrendRecyclerView(toMovieItem: List<HomeMovie>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toMovieItem)
        }

        rec_trend.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val trendDetail = item as HomeMovie
            val bundle = Bundle()
            //scroll()
            bundle.putString("Type","Movie")
            bundle.putString("Title",trendDetail.movie.title)
            bundle.putString("Poster",trendDetail.movie.backdropPath)
            bundle.putString("Poster1",trendDetail.movie.posterPath)
            bundle.putDouble("rating",trendDetail.movie.voteAverage)
            bundle.putString("overView",trendDetail.movie.overview)
            bundle.putString("release",trendDetail.movie.releaseDate)
            bundle.putBoolean("adult",trendDetail.movie.adult)
            bundle.putInt("id",trendDetail.movie.id)
            bundle.putInt("genreId",trendDetail.movie.genreIds.size)
            navController.navigate(R.id.action_nav_home_to_detailFragment, bundle)
        }
    }

    private fun List<Movie>.toMovieItem(): List<HomeMovie> {
        return this.map {
            HomeMovie(requireContext(), it)
        }
    }

    private fun List<TvShow>.toTvShowItem(): List<HomeTvShow> {
        return this.map {
            HomeTvShow(requireContext(), it)
        }
    }

    private fun scroll() {
        val job = lifecycleScope.launch(Dispatchers.Main, start = CoroutineStart.LAZY) {

            var position = 1
            repeat(300000) {
                delay(2000)
                rec_trend.smoothScrollToPosition(position)
                when {
                    position + 1 == listSize -> position = 0
                    position == 0 -> position = 1
                    else -> position++
                }
            }
        }
        job.start()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_fav)
        if (item != null)
            item.isVisible = false
    }

}
