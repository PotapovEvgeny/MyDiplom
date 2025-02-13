package ru.iteco.fmhandroid.ui.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.fail;

import android.content.Intent;
import android.net.Uri;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {
    public void openPrivacyPolicy() {
        Allure.step("Открытие политики конфиденциальности");
        onView(withId(R.id.about_privacy_policy_value_text_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openTermsOfUse() {
        Allure.step("Открытие условий использования");
        onView(withId(R.id.about_terms_of_use_value_text_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void checkPrivacyPolicyIntent() {
        Allure.step("Проверка отправленного Intent (политика конфиденциальности)");
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://vhospice.org/#/privacy-policy/"))
        ));
    }

    public void checkTermsOfUseIntent() {
        Allure.step("Проверка отправленного Intent (условия использования)");
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://vhospice.org/#/terms-of-use"))
        ));
    }

    public void checkPageContent() {
        Allure.step("Проверка загрузки страницы");
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Ждем, пока откроется браузер
        device.wait(Until.hasObject(By.pkg("com.android.chrome").depth(0)), 5000);

        // Проверяем наличие ожидаемого контента на странице
        UiObject pageContent = device.findObject(new UiSelector().textContains("Expected Content"));

        if (!pageContent.exists()) {
            fail("Expected content not found on the page.");
        }
    }
}
