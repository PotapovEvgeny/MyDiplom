package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Data.categoryNews;
import static ru.iteco.fmhandroid.ui.utils.Data.categoryNews2;
import static ru.iteco.fmhandroid.ui.utils.Data.descriptionNews;
import static ru.iteco.fmhandroid.ui.utils.Data.descriptionNews2;
import static ru.iteco.fmhandroid.ui.utils.Data.errorFillFields;
import static ru.iteco.fmhandroid.ui.utils.Data.newsDate;
import static ru.iteco.fmhandroid.ui.utils.Data.newsDate2;
import static ru.iteco.fmhandroid.ui.utils.Data.newsTime;
import static ru.iteco.fmhandroid.ui.utils.Data.newsTime2;
import static ru.iteco.fmhandroid.ui.utils.Data.titleNews;
import static ru.iteco.fmhandroid.ui.utils.Data.titleNews2;
import static ru.iteco.fmhandroid.ui.utils.Data.titleNews3;

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
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Страница новостей")
public class NewsTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    NewsPage newsPage = new NewsPage();
    MainMenuPage mainMenuPage = new MainMenuPage();

    @Before
    public void setUp() {
        DataHelper.waitForAppReady();
        if (!DataHelper.isUserLoggedIn(InstrumentationRegistry.getInstrumentation().getTargetContext())) {
            DataHelper.performLogin();
        }

    }

    @Test
    @Story("Тест-кейс 9. Cвернуть/развернуть все новости в разделе Главная")
    @DisplayName("Сворачивание/разворачивание новостей в главном меню")
    public void shouldCollapseTheNewsFromMainTest() {
        newsPage.expandAndCollapseNewsItemInMain();
    }

    @Test
    @Story("Тест-кейс 10. Ссвернуть/развернуть выбранную новость в разделе Главная")
    @DisplayName("Сворачивание/разворачивание выбранной новости в главном меню")
    public void shouldCollapseTheSelectedNewsFromMainTest() {
        newsPage.expandAndCollapseNewsItem();
    }

    @Test
    @Story("Тест-кейс 11. Свернуть/развернуть выбранную новость в разделе Новости")
    @DisplayName("Сворачивание/разворачивание выбранной новости из раздела 'Новости'")
    public void shouldCollapseTheSelectedNewsFromNewsTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.expandAndCollapseNewsItemInNewsSection();
    }

    @Test
    @Story("Тест-кейс 17. Функции управления новостями")
    @DisplayName("Управление новостями")
    public void shouldNewsManagementTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.openEditNews();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    @Story("Тест-кейс 18. Создание новой новости")
    @DisplayName("Создание новой новости")
    public void createdNewsTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.openEditNews();
        newsPage.addNews();
        newsPage.selectCategory(categoryNews);
        newsPage.updateNewsTitle(titleNews);
        newsPage.updatePublishDate(newsDate);
        newsPage.updatePublishTime(newsTime);
        newsPage.updateDescription(descriptionNews);
        newsPage.saveNewsItem();
        newsPage.verifyNewsInList(titleNews);
    }

    @Test
    @Story("Тест-кейс 20. Создание новости с пустыми полями")
    @DisplayName("Создание новости с пустыми полями")
    public void creatingNewsWithEmptyFieldsTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.openEditNews();
        newsPage.addNews();
        newsPage.saveNewsItem();
        newsPage.checkToastMessage(errorFillFields);
    }

    @Test
    @Story("Тест-кейс 19. Редактирование новости")
    @DisplayName("Редактирование новости")
    public void editingNewsTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.openEditNews();
        newsPage.editNewsItem();
        newsPage.selectCategory(categoryNews2);
        newsPage.updateNewsTitle(titleNews2);
        newsPage.updatePublishDate(newsDate2);
        newsPage.updatePublishTime(newsTime2);
        newsPage.updateDescription(descriptionNews2);
        newsPage.saveNewsItem();
        newsPage.verifyUpdatedNewsInList(titleNews2);
    }

    @Test
    @Story("Тест-кейс 21. Удаление новости")
    @DisplayName("Удаление новости")
    public void deletedNewsTest() {
        mainMenuPage.openMenu();
        mainMenuPage.openNewsSection();
        newsPage.openEditNews();
        newsPage.editNewsItem();
        newsPage.selectCategory(categoryNews);
        newsPage.updateNewsTitle(titleNews3);
        newsPage.updatePublishDate(newsDate);
        newsPage.updatePublishTime(newsTime);
        newsPage.updateDescription(descriptionNews);
        newsPage.saveNewsItem();
        newsPage.verifyUpdatedNewsInList(titleNews3);
        newsPage.findNewsWithTittle(titleNews3);
        newsPage.deleteNewsItem(titleNews3);
        newsPage.checkNewsDeleted(newsPage.getItemCount(), titleNews3);
    }
}






