package com.example.contact.retrofit

import androidx.lifecycle.LiveData
import com.example.contact.model.User
import ir.logicbase.mockfit.Mock
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//import org.mockito.Mock
interface Api {

        @Mock("user_list.json")
        @GET("list")
        fun getAllUser(): Call<List<User>>
}