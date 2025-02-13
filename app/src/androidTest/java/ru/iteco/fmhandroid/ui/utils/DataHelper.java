package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static ru.iteco.fmhandroid.ui.utils.DataHelper.CustomViewAction.waitFor;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.IBinder;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class DataHelper {

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        return prefs.getString("access", null) != null;
    }

    // Ожидаем завершение Splash Screen или другой загрузки
    public static void waitForAppReady() {
        onView(isRoot()).perform(waitFor(5000)); // ожидание завершения Splash или другого процесса
    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }

    public static ViewInteraction emptyToast(int id) {
        return onView(withText(id)).inRoot(new DataHelper.ToastMatcher());
    }

    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
            return new RecyclerViewMatcher(recyclerViewId);
        }

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(ViewGroup.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "click child view with id " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(id);
                childView.performClick();
            }
        };
    }

    public static void LogIn() {

        try {
            // Ввод логина
            onView(Matchers.allOf(
                    isDescendantOfA(withId(R.id.login_text_input_layout)),
                    isAssignableFrom(android.widget.EditText.class)))
                    .check(matches(isDisplayed()))
                    .perform(replaceText("login2"), closeSoftKeyboard());

            // Ввод пароля
            onView(Matchers.allOf(
                    isDescendantOfA(withId(R.id.password_text_input_layout)),
                    isAssignableFrom(android.widget.EditText.class)))
                    .check(matches(isDisplayed()))
                    .perform(replaceText("password2"), closeSoftKeyboard());

            // Нажатие на кнопку "Войти"
            onView(withId(R.id.enter_button))
                    .check(matches(isDisplayed()))
                    .perform(click());

        } catch (NoMatchingViewException e) {
            // Если пользователь уже вышел, ничего не делаем
        }
    }

    public static void LogOut() {
        try {
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
        } catch (NoMatchingViewException e) {
            // Пользователь уже вышел — ничего не делаем
        }
    }

    public static void performLogin() {
        onView(Matchers.allOf(
                isDescendantOfA(withId(R.id.login_text_input_layout)),
                isAssignableFrom(android.widget.EditText.class)))
                .perform(replaceText("login2"), closeSoftKeyboard());

        onView(Matchers.allOf(
                isDescendantOfA(withId(R.id.password_text_input_layout)),
                isAssignableFrom(android.widget.EditText.class)))
                .perform(replaceText("password2"), closeSoftKeyboard());

        onView(withId(R.id.enter_button)).perform(click());
        onView(isRoot()).perform(waitFor(2000)); // Ждем завершения авторизации
    }

    public static class CustomViewAction {
        public static ViewAction waitFor(final long millis) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return ViewMatchers.isRoot();
                }

                @Override
                public String getDescription() {
                    return "wait for " + millis + " milliseconds";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    uiController.loopMainThreadForAtLeast(millis);
                }
            };
        }
    }
}






