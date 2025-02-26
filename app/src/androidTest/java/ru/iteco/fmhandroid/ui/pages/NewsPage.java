package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.clickChildViewWithId;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.findNewsPosition;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.getRecyclerViewItemCount;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.getTextFromNews;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.waitElement;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.CoreMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.DataHelper;


public class NewsPage {
    public int newsListId = R.id.news_list_recycler_view;

    public int getItemCount() {      //Получаем количество элементов в списке новостей
        int itemCount = getRecyclerViewItemCount(newsListId);
        return itemCount;
    }

    public void scrollNews(int i) {
        onView(withId(newsListId))
                .perform(scrollToPosition(i))
                .perform(actionOnItemAtPosition(i, scrollTo()))
                .check(matches(isDisplayed()));
    }

    public void openEditNews() {
        Allure.step("Открываем редактирование новостей");
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void deleteNewsItem(String title) {
        Allure.step("Удаляем выбранную новость - " + title);
        findNewsWithTittle(title);
        onView(CoreMatchers.allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title))))
                .perform(clickChildViewWithId(R.id.delete_news_item_image_view));
        onView(withText("OK"))
                .perform(click());
    }

    public void expandAndCollapseNewsItemInMain() {
        Allure.step("Сворачиваем/разворачиваем новости в главном меню");
        onView(withId(R.id.expand_material_button))
                .check(matches(isDisplayed()))
                .perform(click()).perform(click());
    }

    public void expandAndCollapseNewsItem() {
        Allure.step("Разворачиваем и сворачиваем новость");
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(click()).perform(click());
    }

    public void expandAndCollapseNewsItemInNewsSection() {
        Allure.step("Разворачиваем и сворачиваем новость в разделе 'Новости'");
        onView(withId(R.id.all_news_cards_block_constraint_layout))
                .check(matches(isDisplayed()))
                .perform(click()).perform(click());
    }

    public void checkNewsListIsDisplayed() {
        Allure.step("Проверяем, что список новостей отображается");
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    public void addNews() {
        Allure.step("Нажать на кнопку Добавить новость");
        onView(withId(R.id.add_news_image_view))
                .perform(click());
    }

    public void checkToastMessage(String message) {
        Allure.step("Проверка сообщения об ошибке" + message);
        onView(withText(message))
                .inRoot(new DataHelper.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void editNewsItem() {
        Allure.step("Редавтировать выбранную новость");
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0,
                        clickChildViewWithId(R.id.edit_news_item_image_view)));
    }

    public void selectCategory(String category) {
        Allure.step("Выбрать категорию новости");
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click(), replaceText(category), closeSoftKeyboard());
    }

    public void updateNewsTitle(String title) {
        Allure.step("Изменить заголовок новости");
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(clearText(), replaceText(title), closeSoftKeyboard());
    }

    public void updatePublishDate(String date) {
        Allure.step("Изменить дату публикации");
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
    }

    public void updatePublishTime(String time) {
        Allure.step("Изменить время публикации");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
    }

    public void updateDescription(String description) {
        Allure.step("Изменить описание новости");
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(clearText(), replaceText(description), closeSoftKeyboard());
    }

    public void saveNewsItem() {
        Allure.step("Сохранить изменения в новости");
        onView(withId(R.id.save_button))
                .perform(scrollTo(), click());
    }

    public void verifyUpdatedNewsInList(String title) {
        Allure.step("Проверка, что новость обновилась и отображается в списке");
        try {
            onView(withId(R.id.news_list_recycler_view))
                    .perform(actionOnItem(hasDescendant(withText(title)), scrollTo())) // Скроллим к нужному элементу
                    .check(matches(hasDescendant(withText(title)))); // Проверяем, что он действительно есть
        } catch (NoMatchingViewException e) {
            throw new AssertionError("Ошибка: список новостей не найден или новость с заголовком '"
                    + title + "' отсутствует", e);
        }
    }

    public void verifyNewsInList(String title) {
        Allure.step("Проверяем наличие новости '" + title);
        int position = findNewsPosition(title); // Определяем позицию новости
        if (position == -1) {
            fail("Новость с заголовком '" + title + "' не найдена в списке.");
        } else {
            onView(withId(R.id.news_list_recycler_view))
                    .perform(scrollToPosition(position));
            onView(DataHelper.RecyclerViewMatcher.withRecyclerView(R.id.news_list_recycler_view)
                    .atPositionOnView(position, R.id.news_item_title_text_view))
                    .check(matches(withText(title)));
        }
    }

    public void checkNewsDeleted(int itemCount, String tittle) {
        Allure.step("Проверяем, что новость удалена");
        for (int i = 0; i < itemCount; i++) {
            scrollNews(i);
            String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(tittle, actualTittle);
        }
    }

    public void findNewsWithTittle(String title) {
        Allure.step("Ищем новость в списке по заголовку " + title);
        waitElement(newsListId);
        onView(withId(newsListId))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(CoreMatchers.allOf(withText(title)))));
        onView(withId(newsListId))
                .check(matches(isDisplayed()));
    }
}






    





