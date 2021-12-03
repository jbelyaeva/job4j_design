package ru.job4j.sirialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

  public static void main(String[] args) {
    final Student student = new Student(1, "Ваня", "Ванин", false, new Group(1, "Dghj", "Описание"),
        new String[]{"Математика", "Русский"});
    final Gson gson = new GsonBuilder().create();
    System.out.println(gson.toJson(student));
    final String personJson =
        "{"
            + "\"id\":1,"
            + "\"name\":\"Ваня\","
            + "\"surname\":\"Ванин\","
            + "\"isBlock\":false,"
            + "\"group\":{"
            + "\"id\":1,"
            + "\"code\":\"Dghj\","
            + "\"title\":\"Описание\""
            + "},"
            + "\"subjects\":[\"Математика\",\"Русский\"]"
            + "}";
    final Student studentMod = gson.fromJson(personJson, Student.class);
    System.out.println(studentMod);
  }
}
