package com.example.contact.room

import android.content.Context
//import android.icu.util.TimeUnit
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.contact.model.User
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import java.util.concurrent.TimeUnit
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeoutException
import javax.xml.datatype.DatatypeConstants.SECONDS
import kotlin.time.DurationUnit
//import kotlin.time.DurationUnit.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest : TestCase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var db:DatabaseHelper
    lateinit var dao:UserDao
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db= Room.inMemoryDatabaseBuilder(context,DatabaseHelper::class.java).allowMainThreadQueries().build()
        dao= db.UserDao()
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun addUser() = runBlocking{
        val user = User("aaa","p1","1","dss","3222")
        dao.addUser(user)
        val user1 = dao.getUserByName("aaa")
        assertEquals(user1.name,"aaa");
    }

    @Test
    fun delUser() = runBlocking{
        val user = User("aaa","p1","1","dss","3222")
        dao.addUser(user)
        dao.deleteUser(user)
        val user1 = dao.getAll().getOrAwaitValue()
        assertEquals(user1.size,1)
    }

    @Test
    fun updateUser() = runBlocking{
        val user = User("aaa","p1","1","dss","3222")
        dao.addUser(user)
        user.name="DDD"
        dao.updateUser(user)
        val user1 = dao.getAll().getOrAwaitValue()
        assertEquals(user1[0].name,"DDD");
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