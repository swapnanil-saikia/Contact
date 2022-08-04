package com.example.contact.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.room.DatabaseHelper
import com.example.contact.model.User
import com.example.contact.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelViewModal(application: Application):AndroidViewModel(application) {
    var allUser:LiveData<List<User>>
    val userRepository : UserRepository

    init {
            val dao = DatabaseHelper.getInstance(application).UserDao()
            userRepository = UserRepository(dao)
            allUser = userRepository.allModel
            Log.d("Atif", "Here")
//        image = modelRepository.image

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


//    fun loadImage(id:Int) =
//        viewModelScope.launch(Dispatchers.IO){
//            image = modelRepository.loadImage(id)
//            println("sssssssssss"+image)
//
////        Toast.makeText(this,model.name, Toast.LENGTH_SHORT).show()
//        }

}