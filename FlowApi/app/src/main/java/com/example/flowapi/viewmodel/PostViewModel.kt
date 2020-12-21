package com.example.flowapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowapi.model.Post
import com.example.flowapi.repo.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostViewModel :ViewModel() {
    val responseLiveData : MutableLiveData<List<Post>>  = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            PostRepository.getPost()
                .catch { e->
                    Log.i("main", "getPost: ${e.message}")
                }
                .collect { response ->
                    responseLiveData.value = response
                }
        }
    }
}