package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.DataHelper;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница авторизации")
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        try {
            DataHelper.LogOut();
        } catch (NoMatchingViewException e) {
            if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
                DataHelper.LogIn();
            }
        }
    }

    @Test
    @Story("Тест-кейс 1. Авторизация с тестовыми учетными данными")
    @DisplayName("Авторизация с валидными тестовыми данными")
    public void validLoginAndPasswordTest() {
        authorizationPage.enterLogin("login2");
        authorizationPage.enterPassword("password2");
        authorizationPage.clickSignInButton();
        authorizationPage.checkMainPageDisplayed();
    }

    @Test
    @Story("Тест-кейс 2.Авторизация при вводе невалидного логина")
    @DisplayName("Авторизация при вводе невалидного логина и валидного пароля")
    public void invalidLoginAndPasswordTest() {
        authorizationPage.enterLogin("login");
        authorizationPage.enterPassword("password2");
        authorizationPage.clickSignInButton();
        authorizationPage.checkToastMessage("Login and password cannot be empty");
    }

    @Test
    @Story("Тест-кейс 3. Авторизация при вводе невалиного пароля")
    @DisplayName("Авторизация при вводе валидного логина и невалидного пароля")
    public void validLoginAndInvalidPasswordTest() {
        authorizationPage.enterLogin("login2");
        authorizationPage.enterPassword("password");
        authorizationPage.clickSignInButton();
        authorizationPage.checkToastMessage("Login and password cannot be empty");
    }

    @Test
    @Story("Тест-кейс 4. Авторизация при пустой форме")
    @DisplayName("Авторизация при пустой форме")
    public void emptyLoginAndPasswordTest() {
        authorizationPage.enterLogin("");
        authorizationPage.enterPassword("");
        authorizationPage.clickSignInButton();
        authorizationPage.checkToastMessage("Login and password cannot be empty");
    }

    @Test
    @Story("Тест-кейс 25. Выход из аккаунта")
    @DisplayName("Выход из аккаунта")
    public void logOutTest() {
        authorizationPage.enterLogin("login2");
        authorizationPage.enterPassword("password2");
        authorizationPage.clickSignInButton();
        authorizationPage.checkMainPageDisplayed();
        authorizationPage.performLogout();
        authorizationPage.checkLoginPageDisplayed();
    }
}

