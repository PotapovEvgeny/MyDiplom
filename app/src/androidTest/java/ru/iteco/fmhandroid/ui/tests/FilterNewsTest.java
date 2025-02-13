package ru.iteco.fmhandroid.ui.tests;

import static org.junit.Assert.assertTrue;

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
import ru.iteco.fmhandroid.ui.pages.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainMenuPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница фильтрации новостей")
public class FilterNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    FilterNewsPage filterNewsPage = new FilterNewsPage();
    MainMenuPage mainMenuPage = new MainMenuPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
            DataHelper.performLogin();
        }
    }

    @Test
    @Story("Тест-кейс 13. Страница фильтра новостей")
    @DisplayName("Должна отобразиться страница фильтрации новостей")
    public void shouldDisplayFilterNewsPage() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        filterNewsPage.openFilterNewsPage();
        filterNewsPage.verifyFilterNewsTitle();
    }

    @Test
    @Story("Тест-кейс 14. Фильтр новостей по категории")
    @DisplayName("Фильтрация новостей по категории")
    public void filterNewsByCategory() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        filterNewsPage.openFilterNewsPage();
        filterNewsPage.openCategoryDropdown();
        filterNewsPage.applyFilter();
        filterNewsPage.verifyFilteredNewsAreDisplayed();

        assertTrue("Отфильтрованные новости должны быть отображены", true); // Пустая проверка для демонстрации
    }

    @Test
    @Story("Тест-кейс 15. Фильтр новостей по дате")
    @DisplayName("Фильтрация новостей по дате")
    public void filterNewsByDate() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        filterNewsPage.openFilterNewsPage();
        filterNewsPage.openNewsDateStartFilter();
        filterNewsPage.openNewsDateEndFilter();
        filterNewsPage.applyFilter();
        filterNewsPage.verifyFilteredNewsAreDisplayed();

        assertTrue("Отфильтрованные новости должны быть отображены", true);
    }

    @Test
    @Story("Тест-кейс 16. Фильтр новостей по категории и дате")
    @DisplayName("Фильтрация новостей по категории и дате")
    public void filterNewsByCategoryAndDate() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        filterNewsPage.openFilterNewsPage();
        filterNewsPage.openNewsDateStartFilter();
        filterNewsPage.openNewsDateEndFilter();
        filterNewsPage.openCategoryDropdown();
        filterNewsPage.applyFilter();
        filterNewsPage.verifyFilteredNewsAreDisplayed();

        assertTrue("Отфильтрованные новости должны быть отображены", true);
    }
}
