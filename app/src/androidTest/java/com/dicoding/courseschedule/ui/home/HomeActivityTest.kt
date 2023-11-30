package dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourse
import com.dicoding.courseschedule.ui.home.HomeActivity
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
    }
    @Test
    fun add_Course_Test(){
        Intents.init()
        Espresso.onView(ViewMatchers.withId(R.id.action_add)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(AddCourse::class.java.name))
        Intents.release()
    }
}