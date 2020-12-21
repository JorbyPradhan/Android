package com.example.cleanarchitecturemvvm.presentation.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.data.model.DownloadLink
import com.example.cleanarchitecturemvvm.data.remote.MovieApi
import com.example.cleanarchitecturemvvm.data.remote.NetworkConnectionInterceptor
import com.example.cleanarchitecturemvvm.data.repository.MovieRepository
import com.example.cleanarchitecturemvvm.data.repository.ReadVideoRepo
import com.example.cleanarchitecturemvvm.data.repository.TvRepository
import com.example.cleanarchitecturemvvm.domain.CallBackMovieImpl
import com.example.cleanarchitecturemvvm.domain.CallBackTvShowImpl
import com.example.cleanarchitecturemvvm.domain.CallBackVideoImpl
import com.example.cleanarchitecturemvvm.util.Coroutines
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.add_link.view.*
import kotlinx.android.synthetic.main.detail_fragment.*


@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class DetailFragment : Fragment() {

    private lateinit var title: String
    private lateinit var poster: String
    private var rating: Double = 0.0
    private lateinit var overView: String
    private lateinit var release: String
    private var adult: Boolean = false
    private var mId: Int = 0
    private lateinit var type: String
    private var genreIdSize: Int = 0
    private lateinit var poster1:String
    private lateinit var link : String
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString("Title", null).toString()
        poster = arguments?.getString("Poster", null).toString()
        poster1 = arguments?.getString("Poster1", null).toString()
        rating = arguments?.getDouble("rating", 0.0)!!
        overView = arguments?.getString("overView", null).toString()
        release = arguments?.getString("release", null).toString()
        mId = arguments?.getInt("id", 0)!!
        adult = arguments?.getBoolean("adult", false)!!
        type = arguments?.getString("Type", null).toString()
        genreIdSize = arguments?.getInt("genreId", 0)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @Suppress("PLUGIN_WARNING")
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(detail_bottomSheet)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
        Toast.makeText(context, "Hi ${mAuth.currentUser?.email}", Toast.LENGTH_LONG).show()
        if (mAuth.currentUser?.email != null){
            btn_add_link.visibility = View.VISIBLE
        }else {
            btn_add_link.visibility = View.GONE
        }
       // (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
       // Objects.requireNonNull(requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = DetailFactory(
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
            ),
            CallBackVideoImpl(
                ReadVideoRepo(
                )
            )
        )
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        setData()
        viewModel.id = mId.toString()
        if (type == "Tv") {
            bindTvShow()
        } else {
            bindMovie()
        }
       bindTest()

        poster_trailer.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(link)
            startActivity(i)
        }
        poster_trailer_1.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(link)
            startActivity(i)
        }
        tabOverview.setOnClickListener {
            visibleOverView()
            txt_title.text = getString(R.string.overview)
            detail_overView.setBodyText(overView)
        }
        tabCast.setOnClickListener {
            visibleCast()
            bindCast()
            txt_title.text = getString(R.string.credits)
        }
        tabReview.setOnClickListener {
            visibleReview()
            bindReview()
            txt_title.text = getString(R.string.reviews)
        }
        watch_now.setOnClickListener {
           /* if(mAuth.currentUser?.uid != null){

            }
            else{

            }*/
            Toast.makeText(context, "Hello $link", Toast.LENGTH_LONG).show()
        }
        download_now.setOnClickListener {
            if (appBarLayout.top < 0)
                appBarLayout.setExpanded(true)
            else
                appBarLayout.setExpanded(false)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bindDownloadLink()
        }
        btn_add_link.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.add_link, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("Add Movie Link")
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.upload_link.setOnClickListener {
                //dismiss dialog
                //get text from EditTexts of custom layout
                val trailer = mDialogView.edt_trailer_link.text.toString()
                val link = mDialogView.edt_add_link.text.toString()
                val link1 = mDialogView.edt_add_link1.text.toString()
                val link2 = mDialogView.edt_add_link2.text.toString()
                if(link.isEmpty() && link1.isEmpty() && link2.isEmpty()){
                    Toast.makeText(context,"Link is Empty",Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.addLink(trailer,link,link1, link2)
                    Toast.makeText(context,"Upload complete within 5 second ",Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss()
                }
                //set the input text in TextView
            }
            //cancel button click of custom layout
        }
    }

    private fun bindDownloadLink()= Coroutines.main {
        val download = viewModel.downloadLink.await()
        download.observe(viewLifecycleOwner, Observer {
            initDownloadRecycler(it.toDownloadItem())
        })
    }

    private fun initDownloadRecycler(toDownloadItem: List<DownloadLinkDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toDownloadItem)
        }

        rec_download_link.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val downloadLink = item as DownloadLinkDisplay
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(downloadLink.download.link)
            startActivity(i)
        }
    }
    private fun bindTest()= Coroutines.main {
        val trailerList = viewModel.videoLink.await()
        trailerList.observe(viewLifecycleOwner, Observer {
            link = it
            Toast.makeText(context, "Hello$it", Toast.LENGTH_LONG).show()
        })
    }

    private fun bindCast()= Coroutines.main {
        var cast = viewModel.movieCredit.await()
        if (type == "Tv") {
            cast = viewModel.showCredit.await()
        }
        cast.observe(viewLifecycleOwner, Observer {
            for (i in it.cast.indices) {
                detail_cast_list.append("   ${it.cast[i].name},")
            }
            for (j in it.crew.indices) {
                if (it.crew[j].job == "Director")
                    detail_director_list.append("   ${it.crew[j].name},")
                if (it.crew[j].job == "Producer")
                    detail_producer_list.append("   ${it.crew[j].name},")
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindReview() = Coroutines.main {
        var review = viewModel.movieReviews.await()
        if(type == "Tv"){
            review = viewModel.showReviews.await()
        }
        review.observe(viewLifecycleOwner, Observer {
            if (it.results.isNotEmpty()) {
                detail_content_list.text = "    ${it.results[0].content}"
                detail_author.text = "- ${it.results[0].author}"
            }
        })
    }

    private fun visibleCast() {
        detail_content.visibility = View.GONE
        detail_content_list.visibility = View.GONE
        detail_author.visibility = View.GONE
        detail_director.visibility = View.VISIBLE
        detail_director_list.visibility = View.VISIBLE
        detail_cast.visibility = View.VISIBLE
        detail_cast_list.visibility = View.VISIBLE
        detail_producer.visibility = View.VISIBLE
        detail_producer_list.visibility = View.VISIBLE
        detail_overView.visibility = View.GONE
    }

    private fun visibleOverView() {
        detail_content.visibility = View.GONE
        detail_content_list.visibility = View.GONE
        detail_author.visibility = View.GONE
        detail_director.visibility = View.GONE
        detail_director_list.visibility = View.GONE
        detail_cast.visibility = View.GONE
        detail_cast_list.visibility = View.GONE
        detail_producer.visibility = View.GONE
        detail_producer_list.visibility = View.GONE
        detail_overView.visibility = View.VISIBLE
    }

    private fun visibleReview() {
        detail_content.visibility = View.VISIBLE
        detail_content_list.visibility = View.VISIBLE
        detail_author.visibility = View.VISIBLE
        detail_director.visibility = View.GONE
        detail_director_list.visibility = View.GONE
        detail_cast.visibility = View.GONE
        detail_cast_list.visibility = View.GONE
        detail_producer.visibility = View.GONE
        detail_producer_list.visibility = View.GONE
        detail_overView.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun bindTvShow() = Coroutines.main {
        val detail = viewModel.showDetail.await()
        detail.observe(viewLifecycleOwner, Observer {
            detail_runtime.text = "${it.episodeRunTime} min"
            detail_status.text = "Status : ${it.status}"
            detail_tagLine.text = it.name
            if (genreIdSize == 0) {
                detail_genre.text = ""
            } else {
                for (i in 0 until genreIdSize) {
                    detail_genre.append("${it.genres[i].name} |")
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindMovie() = Coroutines.main {
        val detail = viewModel.movieDetail.await()
        detail.observe(viewLifecycleOwner, Observer {
            detail_runtime.text = "${it.runtime} min"
            detail_status.text = "Status : ${it.status}"
            detail_tagLine.text = "\"${it.tagline}\""
            if (genreIdSize == 0) {
                detail_genre.text = ""
            } else {
                for (i in 0 until genreIdSize) {
                    detail_genre.append("${it.genres[i].name} |")
                }
            }
        })
    }

    private fun setData() {
        Glide.with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500/${poster}")
            .error(R.mipmap.ironman)
            .into(img_detail)
        Glide.with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500/${poster1}")
            .error(R.mipmap.ironman)
            .into(poster_trailer)
        detail_title.text = title
        detail_release.text = release
        detail_rating.text = "$rating"
        detail_overView.setBodyText(overView)

        if (adult) {
            detail_adult.visibility = View.VISIBLE
        } else {
            detail_adult.visibility = View.GONE
        }

    }
    private fun List<DownloadLink>.toDownloadItem(): List<DownloadLinkDisplay> {
        return this.map {
            DownloadLinkDisplay(it)
        }
    }


}