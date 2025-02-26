package ru.iteco.fmhandroid.ui.utils;

import java.util.UUID;


public class Data {
    public static final String validLogin = "login2";
    public static final String validPassword = "password2";
    public static final String invalidLogin = "login";
    public static final String invalidPassword = "password";
    public static final String emptyLogin = "";
    public static final String emptyPassword = "";
    public static final String categoryNews = "Объявление";
    public static final String categoryNews2 = "День рождения";
    public static final String titleNews = "Важная новость " + UUID.randomUUID().toString().substring(0, 2);
    public static final String titleNews2 = "Будет день рождения " + UUID.randomUUID().toString().substring(0, 2);
    public static final String titleNews3 = "Hовость" + UUID.randomUUID().toString().substring(0, 2);;
    public static final String newsDate = "09.04.2025";
    public static final String newsDate2 = "16.07.2025";
    public static final String newsTime = "15.00";
    public static final String newsTime2 = "18.00";
    public static final String descriptionNews = "Годовщина";
    public static final String descriptionNews2 = "В кафе";
    public static final String errorEmptyFields = "Login and password cannot be empty";
    public static final String errorWrongCredentials = "Wrong login or password";
    public static final String errorFillFields = "Fill empty fields";
}
