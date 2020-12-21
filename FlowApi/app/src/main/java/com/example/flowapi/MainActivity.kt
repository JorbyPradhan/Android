package com.example.flowapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowapi.Adapter.PostAdapter
import com.example.flowapi.model.Post
import com.example.flowapi.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
   private lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postAdapter = PostAdapter(this, ArrayList())
        rec_flow.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
        viewModel.getPost()
        viewModel.responseLiveData.observe(this, Observer {
            postAdapter.setPost(it as ArrayList<Post>)
            progress.visibility = View.GONE
            rec_flow.visibility = View.VISIBLE
        })
        /*val data2 = flowOf(1,2,4,5,6,7,8,9,10).flowOn(Dispatchers.IO)

        runBlocking {
            data2.filter {value ->
                value%2 == 0
            }
            *//*data2.map {value ->
                value * value
            }*//*.collect {
                Log.i("MainActivity", "onCreate: $it ")
            }
        }

        val data1 = listOf(1,2,3,4,5).asFlow().flowOn(Dispatchers.IO)
        runBlocking {
            data1.catch {
                Log.i("MainActivity", "onCreate: $it")
            }
        }

        val data = flowOf(1,23,42,21).flowOn(Dispatchers.IO)

        runBlocking {
            data.collect {
                Log.i("MainActivity", "onCreate: $it")
            }
        }
        runBlocking {
            getData().catch { e->
                Log.i("MainActivity", "onCreate: ${e.message}")
            }
            getData().collect {data ->
                Log.i("MainActivity", "onCreate: $data")

            }
        }*/
    }


    private fun getData(): Flow<Int> = flow{
        for(i in 1..5){
            kotlinx.coroutines.delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)
}