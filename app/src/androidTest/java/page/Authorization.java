package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static utilities.ChildAtPosition.childAtPosition;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Authorization {
    private static final int loginId = R.id.login_text_input_layout;
    private static final int passwordId = R.id.password_text_input_layout;
    private static final int signButton = R.id.enter_button;

    private static final int buttonLogOut = R.id.authorization_image_button;
    private static final int logOut = android.R.id.title;


    public void authorizationIn(String login, String password) {
        Allure.step("Ввод данных для авторизации");
        onView((withId(loginId))).perform(click());
        onView(allOf(childAtPosition(childAtPosition(withId(loginId), 0), 0),
                isDisplayed()))
                .perform(replaceText(login), closeSoftKeyboard());
        onView((withId(passwordId))).perform(click());
        onView(allOf(childAtPosition(childAtPosition(withId(passwordId), 0), 0),
                isDisplayed()))
                .perform(replaceText(password), closeSoftKeyboard());
        onView((withId(signButton))).perform(click());
    }

    public void authorizationExit() {
        Allure.step("Выход из авторизации");
        onView((withId(buttonLogOut))).perform(click());
        onView((withId(logOut))).perform(click());
    }

}
