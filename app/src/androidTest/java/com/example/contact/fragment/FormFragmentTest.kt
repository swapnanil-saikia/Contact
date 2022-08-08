package com.example.contact.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import com.example.contact.R
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
 class FormFragmentTest {
    private lateinit var scenario: FragmentScenario<FormFragment>

    @Before
    fun setUp() {
        scenario = launchFragment(themeResId = R.layout.fragment_form)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

//    @After
//    fun tearDown() {
//    }


    @Test
    fun onCreateView() {
        with(launchFragment<FormFragment>()){
            onFragment{Fragment->
                assertEquals(Fragment.button.text.toString(),"")
            }
        }
    }

    @Test
    fun onActivityResult() {
    }

    @Test
    fun onPause() {
    }

    @Test
    fun check() {
    }
}