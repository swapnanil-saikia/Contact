//package com.example.contact.viewmodel
//
//import android.app.Application
//import android.content.Context
//import android.os.Build
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.contact.model.User
//import com.example.contact.repository.UserRepository
//import junit.framework.TestCase
//import org.junit.After
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.withSettings
//import org.mockito.plugins.MockMaker
//import org.mockito.invocation.MockHandler
//import org.robolectric.Robolectric
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import org.robolectric.manifest.AndroidManifest
//
//@RunWith(RobolectricTestRunner::class)
////@Config(manifest = Config.NONE)
//@Config(sdk =[28], manifest = Config.NONE)
//class ModelViewModalTest{
//
////    @Mock
////    lateinit var userRepository :UserRepository
//
////    var application=Mockito.mock(Application::class.java)
// lateinit var modelViewModal: ModelViewModal
// lateinit var user: User
//
//    //    = Mockito.mock(ModelViewModal(application)::class.java)
////
//    @Before
//    fun setUp(){
//    var user = User("aaa","aaa","aaa","aaa","aaa")
//    val application = ApplicationProvider.getApplicationContext<Context>()
//        modelViewModal = ModelViewModal(application as Application)
//    }
//
//    @After
//    fun tearDown(){
////        modelViewModal.delUser(user)
//    }
//
//    @Test
//    fun addUser(){
//        // Context of the app under test.
////        modelViewModal.addUser(user)
//        assert(true)
//
//    }
//}