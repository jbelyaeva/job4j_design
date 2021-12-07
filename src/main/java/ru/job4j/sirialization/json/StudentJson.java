package ru.job4j.sirialization.json;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

public class StudentJson {
  private int id;
  private String name;
  private String surname;
  private Boolean isBlock;
  private GroupJson group;
  private String[] subjects;

  public StudentJson(int id, String name, String surname, Boolean isBlock, GroupJson group, String[] subjects) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.isBlock = isBlock;
    this.group = group;
    this.subjects = subjects;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public Boolean getBlock() {
    return isBlock;
  }

  @Override
  public String toString() {
    return "Student{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", surname='" + surname + '\''
        + ", isBlock='" + isBlock + '\''
        + ", group=" + group
        + ", subjects=" + Arrays.toString(subjects)
        + '}';
  }
}
