package com.example.contact.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private final val BASE_URL = "https://api.themoviedb.org";

  fun getInstance():Retrofit {
      return Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  }
}