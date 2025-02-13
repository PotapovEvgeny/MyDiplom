package ru.iteco.fmhandroid.ui.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainMenuPage {
    public void openMenu() {
        Allure.step("Открываем главное меню");
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openAboutSection() {
        Allure.step("Переход в раздел 'О приложении'");
        onView(allOf(withId(android.R.id.title), withText("About")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openNewsSection() {
        Allure.step("Переход в раздел 'Новости'");
        onView(allOf(withId(android.R.id.title), withText("News")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openAllNewsSection() {
        Allure.step("Переход в раздел 'Все новости')");
        onView(allOf(withId(R.id.all_news_text_view), withText("All news"),
                isDisplayed()))
                .perform(click());
    }

    public void checkNewsBlockIsDisplayed() {
        Allure.step("Проверяем, что блок новостей отображается");
        onView(withId(R.id.all_news_cards_block_constraint_layout))
                .check(matches(isDisplayed()));
    }
}