package ru.iteco.fmhandroid.ui.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class QuotesPage {
    public void openQuotesSection() {
        Allure.step("Нажимаем на кнопку 'Our Mission'");
        onView(allOf(
                withId(R.id.our_mission_image_button),
                withContentDescription("Our Mission"),
                isDisplayed()))
                .perform(click());
    }

    public void checkTextLoveIsAll() {

        Allure.step("Проверяем, что текст 'Love is all' отображается");
        onView(allOf(
                withId(R.id.our_mission_title_text_view),
                withText("Love is all"),
                isDisplayed()))
                .check(matches(withText("Love is all")));
    }

    public void performClickOnRecyclerViewItem(int recyclerViewId, int position) {
        Allure.step("Клик по элементу в RecyclerView на позиции {1}");
        onView(allOf(withId(recyclerViewId),
                isDisplayed()))
                .perform(actionOnItemAtPosition(position, click()));
    }

    public void performingActionsOnElementsInRecyclerView() {
        Allure.step("Выполняем действия над элементами в RecyclerView");
        performClickOnRecyclerViewItem(R.id.our_mission_item_list_recycler_view, 0);
        performClickOnRecyclerViewItem(R.id.our_mission_item_list_recycler_view, 0);
        performClickOnRecyclerViewItem(R.id.our_mission_item_list_recycler_view, 2);
        performClickOnRecyclerViewItem(R.id.our_mission_item_list_recycler_view, 2);
    }
}

