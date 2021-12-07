package ru.job4j.sirialization.json;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainJson {

  public static void main(String[] args) throws JAXBException, IOException {
    StudentJson student = new StudentJson(1, "Ваня", "Ванин", false, new GroupJson(1, "Dghj", "Описание"),
        new String[]{"Математика", "Русский"});
    JSONObject jsonGroup = new JSONObject("{\n"
        + "     \"id\": 1,\n"
        + "     \"code\": \"K78js\",\n"
        + "     \"description\": \"какая-то информация\"\n"
        + "  }");

    List<String> list = new ArrayList<>();
    list.add("Математика");
    list.add("Русский");
    JSONArray jsonSubjects = new JSONArray(list);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", student.getId());
    jsonObject.put("name", student.getName());
    jsonObject.put("surname", student.getSurname());
    jsonObject.put("isBlock", student.getBlock());
    jsonObject.put("group", jsonGroup);
    jsonObject.put("subjects", jsonSubjects);
    System.out.println(jsonObject.toString());
    /* Преобразуем объект person в json-строку */
    System.out.println(new JSONObject(student).toString());
  }
}
