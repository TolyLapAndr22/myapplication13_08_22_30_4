package com.example.myapplication13_08_22_30_4

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    //Вы уже знаете, для чего создаются Rule, но в первом примере вы видели устаревшую версию создания
    //В этом примере рекомендуемый на сегодняшний момент способ создания через ActivityScenarioRule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)
    //Создаем метод, который возвращает объект ViewAction
    private fun clickItemWithId(id: Int): ViewAction {
        //Создаем анонимный класс, реализующий интерфейс ViewAction
        return object : ViewAction {
            //Здесь мы можем установить какие-либо ограничения для проверки с помощью Matcher
            //например, чтобы View был виден на экране
            override fun getConstraints(): Matcher<View>? {
                return any(View::class.java)
            }
            //В этом методе мы возвращаем описание действия, оно должно быть лаконичным
            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }
            //В этом методе совершаем действие над View
            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }
    //Сам тест
    @Test
    fun test() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(19, clickItemWithId(R.id.check_box)))
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(18, clickItemWithId(R.id.check_box)))
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(17, clickItemWithId(R.id.check_box)))


    }
}