package com.example.contact.viewmodel

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.contact.model.User
import com.example.contact.repository.UserRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.withSettings
import org.mockito.plugins.MockMaker
import org.mockito.invocation.MockHandler

//@RunWith(AndroidJUnit4::class)
class ModelViewModalTest{

//    @Mock
//    lateinit var userRepository :UserRepository
// var modelViewModal: ModelViewModal
//    = Mockito.mock(ModelViewModal::class.java, withSettings().serializable(User()))
//
    var application=Mockito.mock(Application::class.java)
    @Before
    fun setUp(){
//        modelViewModal = ModelViewModal(application)

    }

    @Test
    fun addUser(){
        // Context of the app under test.
        var user = User("aaa","aaa","aaa","aaa","aaa")
//        modelViewModal.addUser(user)

//        val model = Mock()
//        model.addUser(User("s","s","s","",""));
    }
}