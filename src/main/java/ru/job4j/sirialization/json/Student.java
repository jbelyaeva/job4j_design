package ru.job4j.sirialization.json;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

  @XmlAttribute
  private int id;

  @XmlAttribute
  private String name;

  @XmlAttribute
  private String surname;

  @XmlAttribute
  private Boolean isBlock;

  private Group group;

  @XmlElementWrapper(name = "subjects")
  @XmlElement(name = "subject")
  private String[] subjects;

  public Student() {
  }

  public Student(int id, String name, String surname, Boolean isBlock, Group group, String[] subjects) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.isBlock = isBlock;
    this.group = group;
    this.subjects = subjects;
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
