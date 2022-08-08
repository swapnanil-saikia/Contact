package com.example.contact.repository

import androidx.lifecycle.LiveData

import com.example.contact.model.User
import com.example.contact.room.UserDao

class UserRepository(private val userDao: UserDao) {
    val allModel: LiveData<List<User>> = userDao.getAll()
//    val image=modelDao.getImage(1)

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun loadImage(id:Int?):String{
        return userDao.getImage(id)
    }

}