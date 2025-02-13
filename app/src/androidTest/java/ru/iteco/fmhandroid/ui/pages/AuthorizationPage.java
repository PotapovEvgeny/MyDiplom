package ru.iteco.fmhandroid.ui.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.DataHelper;

public class AuthorizationPage {

    public void enterLogin(String login) {
        Allure.step("Ввод логина: " + login);
        onView(allOf(
                isDescendantOfA(withId(R.id.login_text_input_layout)),
                isAssignableFrom(android.widget.EditText.class)))
                .perform(replaceText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        Allure.step("Ввод пароля: " + password);
        onView(allOf(
                isDescendantOfA(withId(R.id.password_text_input_layout)),
                isAssignableFrom(android.widget.EditText.class)))
                .perform(replaceText(password), closeSoftKeyboard());
    }

    public void clickSignInButton() {
        Allure.step("Кликнуть кнопку вход");
        onView(withId(R.id.enter_button)).perform(click());
    }

    public void checkToastMessage(String message) {
        Allure.step("Проверка сообщения об ошибке" + message);
        onView(withText(message))
                .inRoot(new DataHelper.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkLoginPageDisplayed() {
        Allure.step("Проверка отображения страницы авторизации");
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
    }


    public void checkMainPageDisplayed() {
        Allure.step("Проверка отображения главной страницы");
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_main))
                .check(matches(isDisplayed()));
    }

    public void performLogout() {
        Allure.step("Выполнение выхода из аккаунта");
        onView(allOf(
                withId(R.id.authorization_image_button),
                withContentDescription("Authorization"),
                isDisplayed()
        )).perform(click());

        onView(allOf(
                withId(android.R.id.title),
                withText("Log out"),
                isDisplayed()
        )).perform(click());
    }
}


