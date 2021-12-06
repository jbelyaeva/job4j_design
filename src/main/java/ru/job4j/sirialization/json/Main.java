package ru.job4j.sirialization.json;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

  public static void main(String[] args) throws JAXBException, IOException {
    final Student student = new Student(1, "Ваня", "Ванин", false, new Group(1, "Dghj", "Описание"),
        new String[]{"Математика", "Русский"});
    JAXBContext context = JAXBContext.newInstance(Student.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    String xml = "";
    try (StringWriter writer = new StringWriter()) {
      marshaller.marshal(student, writer);
      xml = writer.getBuffer().toString();
      System.out.println(xml);
    }
    Unmarshaller unmarshaller = context.createUnmarshaller();
    try (StringReader reader = new StringReader(xml)) {
      Student result = (Student) unmarshaller.unmarshal(reader);
      System.out.println(result);
    }
  }
}
