package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Navigation {
    private static final int mainMenu = R.id.main_menu_image_button;
    private static final int  allNewsButton = R.id.all_news_text_view;
    private static final int ourMissionButton = R.id.our_mission_image_button;
    private static final int aboutBackButton = R.id.about_back_image_button;
    private static final int controlPanelButton = R.id.edit_news_material_button;

    public void navigationFromMainPageMainMenu(String chapter) {
        Allure.step( "Переход  с главной страницы через главное меню в разделы приложения");
        onView(withId(mainMenu)).perform(click());
        onView(withText(chapter)).perform(click());
    }

    public void navigationFromMainPageFromAllNewsButton() {
        Allure.step( "Переход  с главной страницы в раздел Новости через кнопку Все новости");
        onView(allOf(withId(allNewsButton), withText("All news"))).perform(click());
    }
    public void navigationFromMainPageToOurMission() {
        Allure.step( "Переход  с главной страницы в раздел цитат");
        onView(allOf(withId(ourMissionButton), withContentDescription("Our Mission"))).perform(click());
    }

    public void navigationFromNewsPageToControlPanel() {
        Allure.step( "Переход  с из раздела Новости в раздел О приложении  через главное меню");
        onView(withId(controlPanelButton)).perform(click());
    }

    public void navigationFromAboutToMainPage() {
        Allure.step( "Переход  с из раздела О приложении на главную страницу");
        onView(withId(aboutBackButton)).perform(click());
    }
}
