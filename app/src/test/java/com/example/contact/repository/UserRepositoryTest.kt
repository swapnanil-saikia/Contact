package com.example.contact.repository

import androidx.lifecycle.MutableLiveData
import com.example.contact.model.User

class UserRepositoryTest {
    private val users = mutableListOf<User>()
    val observableuser = MutableLiveData<List<User>>()

    private fun refreshData(){
        observableuser.postValue(users)
    }
    suspend fun addUser(user: User){
        users.add(user)
        refreshData()
    }

    suspend fun deleteUser(user: User){
        users.remove(user)
        refreshData()
    }

//    suspend fun loadImage(id:Int):String{
////        return userDao.getImage(id)
//    }
}