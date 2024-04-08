package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static page.News.sortButton;
import static utilities.WithIndex.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Matches {
    public static final int descriptionText = R.id.news_item_description_text_view;
    public static final int titleText = R.id.news_item_title_text_view;

    public void examinationValue(String value) {
        Allure.step( "Прорверить переход на страницу");
        onView(withText(value)).check(matches(isDisplayed()));
    }
    public void examinationContentDescription(String value) {
        Allure.step( "Прорверить всплывающее окно при входе с ошибочными данными");
        onView(allOf(withContentDescription(value), isDisplayed()));
    }

    public void checkToastMessageText() {
        Allure.step( "Прорверить всплывающее окно");
        ViewInteraction imageView = onView(
                allOf(withContentDescription("app background image"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    public void searchNewsCategory(String heading, int position) {
        Allure.step( "Прорверить появления новости по заголовку");
        onView(
                allOf(withIndex(withId(titleText), position),
                        isDisplayed()))
                .check(matches(withText(heading)));
    }
    public void searchNewsDescription(String description, int position) {
        Allure.step( "Прорверить содержание новости");
        onView(
                allOf(withIndex(withId(descriptionText), position),
                        isDisplayed()))
                .check(matches(withText(description)));
    }
    public void buttonClickability() {
        Allure.step( "Проверить кликабельность кнопки");
        onView(withId(sortButton)).check(matches(isClickable()));
    }

    public void fillEmptyFields() {
        Allure.step( "Проверить сообщение о вводе пустых строк");
        onView(
                allOf(withIndex(withId(R.id.nav_host_fragment),0),
                        isDisplayed()))
                .check(matches((isDisplayed())));
    }
}
