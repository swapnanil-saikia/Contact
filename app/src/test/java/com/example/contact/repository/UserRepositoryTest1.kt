package com.example.contact.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.example.contact.model.User
import com.example.contact.room.UserDaoFake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class UserRepositoryTest1{

//    var rule = MockitoJUnit.rule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userRepository: UserRepository
    var userDaoFake: UserDaoFake = UserDaoFake()

    lateinit var user:User


    @Before
    fun setUp() {
        userRepository = UserRepository(userDaoFake)
//        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
     fun addUser() = runTest {
        user = User("asa", "asas", "asa", "asas", "asas")
        userRepository.addUser(user)
        assertEquals(userDaoFake.userList.size, 1)
    }

    @Test
    fun deleteUser() = runTest{
        user = User("asa", "asas", "asa", "asas", "asas")
        userRepository.addUser(user)
        userRepository.deleteUser(user)
        assertEquals(userDaoFake.userList.size, 0)
    }

    @Test
    fun loadImage() = runTest{
        user = User("asa", "asas", "Image", "asas", "asas")
        userRepository.deleteUser(user)
        userRepository.addUser(user)
        var loadImage = userRepository.loadImage(1)
        assertEquals(loadImage, "Image")
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