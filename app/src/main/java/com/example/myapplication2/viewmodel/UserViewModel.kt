package com.example.myapplication2.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.model.LoadingState
import com.example.myapplication2.Users
import com.example.myapplication2.repo.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repo: UserRepository) : ViewModel(), Callback<List<Users>> {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<List<Users>>()
    val data: LiveData<List<Users>>
        get() = _data
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast
    init {
        fetchData()
    }
    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        repo.getAllUsers().enqueue(this)
    }
    override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
            //Toast.makeText(this, "Data is successfully passed", Toast.LENGTH_LONG).show()
            _toast.postValue("Data is successfully passed")
        } else {
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
            //Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            _toast.postValue(LoadingState.error(response.errorBody().toString()).toString())


        }
    }

    override fun onFailure(call: Call<List<Users>>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }
}