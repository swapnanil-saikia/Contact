package com.example.contact.room

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contact.model.User
import org.junit.Rule

class UserDaoFake :UserDao{

    var user=User("name","","Image","","")
    var userList = mutableListOf<User>()
   var observableList = MutableLiveData<List<User>>()

//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

    override fun getAll() :LiveData<List<User>>{
        return observableList
    }

    override fun getImage(id: Int?): String {
        if(id==1){
            println("ididididiidiidid"+id)
            return userList.get(0).img.toString()
        }
        return ""
    }

    override fun getUserByName(name:String):User{
        return user
    }

    override suspend fun addUser(user: User){
        userList.add(user)
//         observableList.postValue(userList)

    }

     override suspend fun deleteUser(user: User){
        userList.remove(user)
//         observableList.postValue(userList)
    }

    override suspend fun updateUser(user: User) {
       this.user=user
    }

}