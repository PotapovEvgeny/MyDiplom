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
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница Наша миссия/Цитаты")
public class QuotesTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    QuotesPage quotesPage = new QuotesPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
            DataHelper.performLogin();
        }
    }

    @Test
    @Story("Тест-кейс 7. Переход в раздел \"Тематические цитаты\" с помощью кнопки \"Наша миссия\" (бабочка) на верхней панели")
    @DisplayName("Переход в раздел 'Наша мииссия'")
    public void quotesSectionTest() {
        quotesPage.openQuotesSection();
        quotesPage.checkTextLoveIsAll();
        quotesPage.performingActionsOnElementsInRecyclerView();
    }
}
