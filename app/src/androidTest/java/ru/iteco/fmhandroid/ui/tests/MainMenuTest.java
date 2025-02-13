package ru.iteco.fmhandroid.ui.tests;

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
import ru.iteco.fmhandroid.ui.pages.MainMenuPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница главного меню")
public class MainMenuTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    MainMenuPage mainMenuPage = new MainMenuPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
            DataHelper.performLogin();
        }
    }

    @Test
    @Story("Тест-кейс 5. Переход в раздел 'О приложении'")
    @DisplayName("Переход в раздел 'О приложении'")
    public void openAboutSectionTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openAboutSection();
    }

    @Test
    @Story("Тест-кейс 5. Переход в раздел 'Новости'")
    @DisplayName("Переход в раздел 'Новости'")
    public void openNewsSectionTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        mainMenuPage.checkNewsBlockIsDisplayed();
    }

    @Test
    @Story("Тест-кейс 6. Переход в раздел \"News\" из раздела \"Главная\" через кнопку-ссылку \"All news\"")
    @DisplayName("Переход в раздел 'Все новости'")
    public void openAllNewsSectionTest() {
        mainMenuPage.openAllNewsSection();
        mainMenuPage.checkNewsBlockIsDisplayed();
    }
}





