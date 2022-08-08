package com.example.contact.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.room.DatabaseHelper
import com.example.contact.model.User
import com.example.contact.repository.UserRepoApi
import com.example.contact.repository.UserRepository
import com.example.contact.retrofit.Api
import com.example.contact.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

class ModelViewModal(application: Application):AndroidViewModel(application) {
    var allUser:LiveData<List<User>>
    val userRepository : UserRepository
    lateinit var image:String
    init {
    println("sddddd  ff")
            val dao = DatabaseHelper.getInstance(application).UserDao()
            userRepository = UserRepository(dao)
            allUser = userRepository.allModel
//        val a = RetrofitService.getInstance().create(Api::class.java)
//        userRepoApi  = UserRepoApi(a)/
        println("sddddd  ff")

    ///        image = modelRepository.image

    }


    fun addUser(user: User)  =
    viewModelScope.launch(Dispatchers.IO){
        println("from test")
        userRepository.addUser(user)
//        Toast.makeText(this,model.name, Toast.LENGTH_SHORT).show()
    }

    fun delUser(user: User)  =
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteUser(user)
        }

    fun updateUser(user: User)  =
        viewModelScope.launch(Dispatchers.IO){
            userRepository.updateUser(user)
        }


    fun loadImage(id:Int?) :Job =
        viewModelScope.launch(Dispatchers.IO){
             image = userRepository.loadImage(id)
            println("sssssssssss"+image)


//        Toast.makeText(this,model.name, Toast.LENGTH_SHORT).show()
        }

}