package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
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
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.MainMenuPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница 'О приложении'")
public class AboutTest {

    @Rule
    public IntentsTestRule<AppActivity> intentsTestRule = new IntentsTestRule<>(AppActivity.class);

    AboutPage aboutPage = new AboutPage();
    MainMenuPage mainMenuPage = new MainMenuPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
            DataHelper.performLogin();
        }
    }

    @Test
    @Story("Тест-кейс 23.Переход по ссылке на страницу с текстом Политики конфиденциальности ")
    @DisplayName("Переход по ссылке на страницу с текстом Политики конфиденциальности")
    public void privacyPolicyTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openAboutSection();
        aboutPage.openPrivacyPolicy();
        aboutPage.checkPrivacyPolicyIntent();
        aboutPage.checkPageContent();
    }

    @Test
    @Story("Тест-кейс 24. Переход по ссылке на страницу с текстом Пользовательского соглашения")
    @DisplayName("Переход по ссылке на страницу с текстом Пользовательского соглашения")
    public void termsOfUseTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openAboutSection();
        aboutPage.openTermsOfUse();
        aboutPage.checkTermsOfUseIntent();
        aboutPage.checkPageContent();
    }
}








