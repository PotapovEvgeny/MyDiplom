package ru.iteco.fmhandroid.ui.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class FilterNewsPage {

    public void openFilterNewsPage() {
        Allure.step("Открываем страницу фильтрации новостей");
        onView(withId(R.id.filter_news_material_button))
                .perform(click());
    }

    public void verifyFilterNewsTitle() {
        Allure.step("Проверяем, что заголовок фильтрации новостей отображается");
        onView(allOf(withId(R.id.filter_news_title_text_view), withText("Filter news")))
                .check(matches(allOf(ViewMatchers.isDisplayed(), withText("Filter news"))));
    }

    public void openCategoryDropdown() {
        Allure.step("Открываем выпадающий список категорий");
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click(), replaceText("Объявление"));
    }

    public void openNewsDateStartFilter() {
        Allure.step("В поле Дата публикации выбрать дату по календарю");
        onView(withId(R.id.news_item_publish_date_start_text_input_layout))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());

    }

    public void openNewsDateEndFilter() {
        Allure.step("В поле Дата публикации выбрать дату по календарю");
        onView(withId(R.id.news_item_publish_date_end_text_input_layout))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
    }

    public void applyFilter() {
        Allure.step("Применяем фильтр");
        onView(withId(R.id.filter_button))
                .perform(click());
    }

    public void verifyFilteredNewsAreDisplayed() {
        Allure.step("Проверяем, что отфильтрованные новости отображаются");
        onView(withId(R.id.all_news_cards_block_constraint_layout))
                .check(matches(ViewMatchers.isDisplayed()));
    }
}

