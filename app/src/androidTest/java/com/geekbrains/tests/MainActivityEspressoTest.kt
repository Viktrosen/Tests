package com.geekbrains.tests

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    //Контекст нам понадобится для запуска нужных экранов и получения packageName
    private val context = ApplicationProvider.getApplicationContext<Context>()

    //Путь к классам нашего приложения, которые мы будем тестировать
    private val packageName = context.packageName
    companion object {
        private const val TIMEOUT = 5000L
    }

    @Before
    fun setup(){
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun activity_notNull(){
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_isResumed(){
        TestCase.assertEquals(Lifecycle.State.RESUMED,scenario.state)
    }

    @Test
    fun activityProgressBar_isGone(){
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun activityEditText_isDisplayed(){
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activityEditText_HasHintText(){
        scenario.onActivity {
            val editTextView = it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertEquals("Enter keyword e.g. android", editTextView.hint)
        }
    }

    @Test
    fun activityButton_isDisplayed(){
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun activityButton_HasText(){
        scenario.onActivity {
            val detailsActivityButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertEquals("to details",detailsActivityButton.text)
        }
    }

    @Test
    fun activityTextView_isNotDisplayed(){
        onView(withId(R.id.totalCountTextView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }


    /*@Test
    fun activitySearch_isWorking(){
        uiDevice.findObject(By.res(packageName,"searchEditText")).click()
        uiDevice.findObject(By.res(packageName,"searchButton")).click()

        if (BuildConfig.TYPE == MainActivity.FAKE) {
            TestCase.assertEquals("Number of results: 42",uiDevice.findObject(By.res(packageName,"totalCountTextView")).text,)

        } else {
            val changedText =
                uiDevice.wait(
                    Until.findObject(By.res(packageName, "totalCountTextView")),
                    TIMEOUT
                )
            //Убеждаемся, что сервер вернул корректный результат. Обратите внимание, что количество
            //результатов может варьироваться во времени, потому что количество репозиториев постоянно меняется.
            TestCase.assertEquals(changedText.text.toString(), "Number of results: 668")
        }
    }*/
    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())

        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    @Test
    fun toDetailsScreen_test(){
        uiDevice.findObject(By.res(packageName,"searchEditText")).click()
        uiDevice.findObject(By.res(packageName,"searchButton")).click()

        uiDevice.findObject(By.res(packageName,"toDetailsActivityButton")).click()
        TestCase.assertEquals("Number of results: 668",uiDevice.findObject(By.res("totalCountTextView")).text)
    }



    @After
    fun close(){
        scenario.close()
    }

}