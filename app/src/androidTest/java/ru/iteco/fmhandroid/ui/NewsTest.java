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
import page.News;
import utilities.WaitForLoading;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
@Epic("Раздел Новости")

public class NewsTest {
    Navigation navigation = new Navigation();
    Authorization authorization = new Authorization();
    Matches matches = new Matches();
    News news = new News();
    DataHelper data = new DataHelper();
    WaitForLoading utilities = new WaitForLoading();
    String categoryFirst = "Объявление";
    String categorySecond = "Зарплата";
    String newNews = "Создание новости";
    String newsPage = "News";
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
    @DisplayName("Тест 10 Создание новости")
    public void creationOfNewsTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews(categoryFirst, newNews, newNews);
        utilities.waitForLoading();
        navigation.navigationFromMainPageMainMenu(newsPage);
        matches.searchNewsCategory(newNews, 0);
    }

    @Test
    @DisplayName("Тест 11 Отмена операции 'Создание новости'")
    public void cancelCancelreationOfNewsTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        news.cancelNews();
        utilities.waitForLoading();
        matches.examinationValue(controlPanelPage);
    }
    @Test
    @DisplayName("Тест 12 Сортировка новостей в разделе Новости")
    public void sortNewsOnNewsTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        news.sortNewsOnControlPanel();
        utilities.waitForLoading();
        news.sortNewsOnControlPanel();
        matches.buttonClickability();
    }

    @Test
    @DisplayName("Тест 13 Фильтр новостей в разделе Новости оставив поля фильтра незаполненными")
    public void filterNewsOnNewsEmptyTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        news.filterNewsEmpty();
        matches.examinationValue(newsPage);
    }
    @Test
    @DisplayName("Тест 14 Отмена операции фильтра новостей в разделе Новости")
    public void filterNewsOnNewsCanselTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        news.filterNewsCansel();
        matches.examinationValue(newsPage);

    }
    @Test
    @DisplayName("Тест 15 Фильтр новостей в разделе Новости по параметру Категория")
    public void filterNewsOnNewsCategoryTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews(categorySecond, categorySecond, categorySecond);
        news.addNews(categoryFirst, categoryFirst, categoryFirst);
        navigation.navigationFromMainPageMainMenu(newsPage);
        news.filterNewsCategory(categorySecond);
        navigation.navigationFromMainPageMainMenu(newsPage);
        matches.searchNewsCategory(categorySecond, 0);
    }

    @Test
    @DisplayName("Тест 16 Просмотр новости в разделе 'Новости'")
    public void viewingNewsOnNewsPageTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews(categoryFirst, newNews, newNews);
        navigation.navigationFromMainPageMainMenu(newsPage);
        news.clickNews(0);
        matches.searchNewsDescription(newNews, 0);
    }
    @Test
    @DisplayName("Тест 20 Добавление новости с незаполненными полями")
    public void creationOfNewsEmptyTest() {
        navigation.navigationFromMainPageMainMenu(newsPage);
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews(categoryFirst, "", "");
//        matches.examinationValue("Creating");
        matches.fillEmptyFields();
    }


}
