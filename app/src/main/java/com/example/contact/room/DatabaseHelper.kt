package com.example.contact.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contact.model.User

@Database(entities = [User::class], version =  10)

    abstract class DatabaseHelper() : RoomDatabase(){

    abstract fun UserDao(): UserDao

    companion object {
        @Volatile
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, DatabaseHelper::class.java,
                    "modelDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
////                populateDatabase(instance!!)
//            }
        }

//        private fun populateDatabase(db: DatabaseHelper) {
//            val noteDao = db.ModelDao()
//            subscribeOnBackground {
//                delDao.add(
//                    Model("title 1",1, 1,"S"))

//                noteDao.insert(Note("title 3", "desc 3", 3))

//            }
//        }
//
//        private fun subscribeOnBackground(function: () -> Unit) {
//
//        }
    }
}

