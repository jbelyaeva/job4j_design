package ru.job4j.collection.task;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Analize {

  public static Info diff(Set<User> previous, Set<User> current) {
    Info info = new Info(0, 0, 0);
    if (previous.equals(current)) {
      return info;
    } else {
      Set<User> different = new HashSet();
      Set<User> temp = new HashSet();
      temp.addAll(previous);
      different.addAll(previous);
      different.addAll(current);
      temp.retainAll(current);
      different.removeAll(temp);
      List list = Arrays.asList(different.toArray());
      list.sort(Comparator.comparing(User::getId));
      for (int i = 0; i < list.size(); i++) {
        if (i < list.size() - 1) {
          if (((User) list.get(i)).getId() == ((User) list.get(i + 1)).getId()) {
            info.setChanged(1);
            i++;
            continue;
          }
        }
        if (previous.contains(list.get(i)) && !current.contains(list.get(i))) {
          info.setDeleted(1);
        }
        if (!previous.contains(list.get(i)) && current.contains(list.get(i))) {
          info.setAdded(1);
        }
      }
      return info;
    }
  }
}