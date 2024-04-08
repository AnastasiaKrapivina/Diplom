package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.DataHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.Matches;
import page.Navigation;
import utilities.WaitForLoading;

@LargeTest
////@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
@Epic("Навигация")

public class NavigationTest {
    Navigation navigation = new Navigation();
    Authorization authorization = new Authorization();
    Matches matches = new Matches();
    WaitForLoading utilities = new WaitForLoading();
    DataHelper data = new DataHelper();
    String newsPage = "News";
    String aboutPage = "About";
    String controlPanelPage = "Control panel";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testLogOut() {

        utilities.waitForLoading();

        try {
            authorization.authorizationIn(data.getValidLogin(), data.getValidPassword());
            utilities.waitForLoading();

        } catch (Exception es) {

        }
    }

    @Test
    @DisplayName("Тест 3 Переход с главной страницы в раздел 'Новости' через меню 'бургер'")
    public void navigationFromMainPageToNewsFromMainMenuTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        matches.examinationValue("News");
    }

    @Test
    @DisplayName("Тест 4 Переход с главной страницы в раздел 'Новости' через кнопку 'Все новости'")
    public void navigationFromMainPageToNewsFromAllNewsButtonTest() {
        navigation.navigationFromMainPageFromAllNewsButton();
        matches.examinationValue("News");
    }

    @Test
    @DisplayName("Тест 5 Переход с главной страницы в раздел 'О приложении' через меню 'бургер'")
    public void navigationFromMainPageToAboutTest() {
        navigation.navigationFromMainPageMainMenu(aboutPage);
        matches.examinationValue("Version:");
    }

    @Test
    @DisplayName("Тест 6 Переход с главной страницы в раздел тематических цитат 'Главное -жить любя'")
    public void navigationFromMainPageToOurMissionTest() {
        navigation.navigationFromMainPageToOurMission();
        matches.examinationValue("Love is all");
    }

    @Test
    @DisplayName("Тест 7 Переход из раздела 'Новости' в раздел 'О приложении' через меню 'бургер'")
    public void navigationFromNewsPageToAboutFromMainMenuTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromMainPageMainMenu(aboutPage);
        matches.examinationValue("Version:");
    }

    @Test
    @DisplayName("Тест 8 Переход из раздела 'Новости' в раздел 'Панель управления'")
    public void navigationFromNewsPageToControlPanelTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        matches.examinationValue(controlPanelPage);
    }

    @Test
    @DisplayName("Тест 9 Переход из раздела 'О приложении' на главную страницу")
    public void navigationFromAboutToMainPageTest() {
        navigation.navigationFromMainPageMainMenu(aboutPage);
        navigation.navigationFromAboutToMainPage();
        matches.examinationValue("News");
    }

}
