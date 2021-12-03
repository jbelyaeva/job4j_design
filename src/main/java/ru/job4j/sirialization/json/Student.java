package ru.job4j.sirialization.json;

import java.util.Arrays;

public class Student {
  private int id;
  private String name;
  private String surname;
  private Boolean isBlock;
  private Group group;
  private String[] subjects;

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
