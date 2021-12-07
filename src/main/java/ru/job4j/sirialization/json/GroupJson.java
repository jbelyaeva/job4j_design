package ru.job4j.sirialization.json;

public class GroupJson {

  private int id;
  private String code;
  private String title;

  public GroupJson(int id, String code, String title) {
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
