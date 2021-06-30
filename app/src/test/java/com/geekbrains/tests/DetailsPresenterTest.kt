package com.geekbrains.tests

import androidx.test.core.app.ActivityScenario
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.atLeastOnce
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    private lateinit var context:Context
    private lateinit var presenter:DetailsPresenter

    @Mock
    private lateinit var viewContract:ViewDetailsContract


    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter(viewContract,0)
    }

    @Test
    fun setCounter_test(){
        presenter.setCounter(3)
        assertEquals(3,presenter.getCount())
    }

    @Test
    fun onIncrement_test1(){
        presenter.onIncrement()
        Mockito.verify(viewContract, atLeastOnce()).setCount(1)
    }

    @Test
    fun onIncrement_test2(){
        presenter.onIncrement()
        assertEquals(1,presenter.getCount())
    }

    @Test
    fun onDecrement_test1(){
        presenter.onDecrement()
        Mockito.verify(viewContract, atLeastOnce()).setCount(-1)
    }

    @Test
    fun onDecrement_test2(){
        presenter.onDecrement()
        assertEquals(-1,presenter.getCount())
    }
}