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
import utilities.WaitForLoading;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
@Epic("Авторизация пользователя")

public class AuthorizationTest {
    Authorization authorization = new Authorization();
    Matches matches = new Matches();
    DataHelper data = new DataHelper();
    WaitForLoading utilities = new WaitForLoading();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testLogOut() {

        utilities.waitForLoading();

        try {
            authorization.authorizationExit();
         } catch (Exception es) {

        }
    }

    @Test
    @DisplayName("Тест 1 Ввод допустимых значений в поля формы авторизации")
    public void authorizationValidTest() {
        utilities.waitForLoading();
        authorization.authorizationIn(data.getValidLogin(), data.getValidPassword());
        utilities.waitForLoading();
        matches.examinationValue("News");
    }
    @Test
    @DisplayName("Тест 2 Выход из приложения")
    public void authorizationExitTest() {
        utilities.waitForLoading();
        authorization.authorizationIn( data.getValidLogin(), data.getValidPassword());
        utilities.waitForLoading();
        authorization.authorizationExit();
        matches.examinationValue("Authorization");
    }

    @Test
    @DisplayName("Тест 17 Отправка незаполненных полей формы авторизации")
    public void authorizationInvalid() {
        utilities.waitForLoading();
        authorization.authorizationIn(" ", " ");
        utilities.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Тест 18 Ввод недопустимого значения в поле 'Логин'")
    public void authorizationInvalidLogin() {
        utilities.waitForLoading();
        authorization.authorizationIn(data.getInvalidLogin(),  data.getValidPassword());
        utilities.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Something went wrong. Try again later");

    }
    @Test
    @DisplayName("Тест 19 Ввод недопустимого значения в поле 'Пароль'")
    public void authorizationInvalidPassword() {
        utilities.waitForLoading();
        authorization.authorizationIn( data.getValidLogin(), data.getInvalidPassword());
        utilities.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Something went wrong. Try again later");
    }


}
