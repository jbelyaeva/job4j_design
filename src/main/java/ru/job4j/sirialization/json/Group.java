package ru.job4j.sirialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "group")
public class Group {

  @XmlAttribute
  private int id;

  @XmlAttribute
  private String code;

  @XmlAttribute
  private String title;

  public Group() {
  }

  public Group(int id, String code, String title) {
    this.id = id;
    this.code = code;
    this.title = title;
  }

  @Override
  public String toString() {
    return "Group{"
        + "id=" + id
        + ", code='" + code + '\''
        + ", title='" + title + '\''
        + '}';
  }
}
