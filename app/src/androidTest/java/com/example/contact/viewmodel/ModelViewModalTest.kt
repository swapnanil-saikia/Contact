package com.example.contact.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.contact.model.User
import com.example.contact.repository.UserRepository
import com.example.contact.room.getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.withSettings
import org.mockito.plugins.MockMaker
import org.mockito.invocation.MockHandler
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class ModelViewModalTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Mock
//    lateinit var userRepository :UserRepository

//    var application=Mockito.mock(Application::class.java)
 lateinit var user:User
    val application = ApplicationProvider.getApplicationContext<Context>()
    private val modelViewModal = ModelViewModal(application as Application)

    //    = Mockito.mock(ModelViewModal(application)::class.java)
//
    @Before
    fun setUp(){
     user = User("aaa","aaa","aaa","aaa","aaa")
//    val application = ApplicationProvider.getApplicationContext<Context>()
//        modelViewModal = ModelViewModal(application as Application)

    }

    @After
    fun tearDown(){
        modelViewModal.delUser(user)
    }

    @Test
    fun addUser() = runBlocking{
        // Context of the app under test.
        val user1 = modelViewModal.allUser.getOrAwaitValue()

        val f :Boolean = modelViewModal.addUser(user).isCompleted

        if(f) {
             val user2 = modelViewModal.allUser.getOrAwaitValue()
            assertEquals(user2.size- user1.size, 1)
        }

    }
    @Test
    fun deleteUser(){
        // Context of the app under test.
        modelViewModal.addUser(user)
        val user1 = modelViewModal.allUser.getOrAwaitValue()
        user.id = user1[user1.size-1].id
        modelViewModal.delUser(user).invokeOnCompletion {
            val user2 = modelViewModal.allUser.getOrAwaitValue()
            print(user1[0].name)
            assertEquals(user2.size - user1.size, 1)
        }
    }
    @Test
    fun updateUser(){
        // Context of the app under test.
        modelViewModal.addUser(user)
        val user1 = modelViewModal.allUser.getOrAwaitValue()
        user.id = user1[user1.size-1].id
        user.name="ddd"
        modelViewModal.updateUser(user).invokeOnCompletion {
            val user2 = modelViewModal.allUser.getOrAwaitValue()
            print(user1[0].name)
            assertEquals(user2[user2.size-1].name, "ddd")
        }
    }
    @Test
    fun loadImage(){
        modelViewModal.addUser(user)
        val user1 = modelViewModal.allUser.getOrAwaitValue()
        modelViewModal.loadImage(user1[user1.size-1].id).invokeOnCompletion {
            assertEquals(user1[user1.size-1].img,"aaa")
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}