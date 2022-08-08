package com.example.contact.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.contact.model.User
import com.example.contact.retrofit.Api
import com.example.contact.retrofit.AppExecutors
import com.example.contact.retrofit.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepoApi(private val api: Api) {

//    val a = LiveDatalistOf<User>()
    val allModel: LiveData<List<User>> =  api.getAllUser()

}

