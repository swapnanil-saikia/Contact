package com.example.contact.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contact.model.User

@Dao
 interface UserDao {

    @Query("Select * from user")
    fun getAll():LiveData<List<User>>

    @Query("Select img from user where user.id=:id")
    fun getImage(id:Int?):String

   @Query("Select * from user where user.name=:name")
   fun getUserByName(name:String):User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

}