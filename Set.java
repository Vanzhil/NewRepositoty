import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Set<T extends Comparable<T>> implements Iterable<T> {
  private List<T> items;

  public Set() {
    items = new ArrayList<>();
  }

  public Set(Iterable<T> initialItems) {
    this();
    addRange(initialItems);
  }

  public void add(T item) {
    if (contains(item)) {
      throw new IllegalStateException("Элемент уже присутствует в множестве");
    }
    items.add(item);
  }

  public void addRange(Iterable<T> itemsToAdd) {
    for (T item : itemsToAdd) {
      add(item);
    }
  }

  public boolean remove(T item) {
    return items.remove(item);
  }

  public boolean contains(T item) {
    return items.contains(item);
  }

  public int count() {
    return items.size();
  }

  public Set<T> union(Set<T> other) {
    Set<T> result = new Set<>(this);
    for (T item : other.items) {
      if (!result.contains(item)) {
        result.add(item);
      }
    }
    return result;
  }

  public Set<T> intersection(Set<T> other) {
    Set<T> result = new Set<>();
    for (T item : this.items) {
      if (other.contains(item)) {
        result.add(item);
      }
    }
    return result;
  }

  public Set<T> difference(Set<T> other) {
    Set<T> result = new Set<>(this);
    for (T item : other.items) {
      result.remove(item);
    }
    return result;
  }

  public Set<T> symmetricDifference(Set<T> other) {
    Set<T> union = this.union(other);
    Set<T> intersection = this.intersection(other);
    return union.difference(intersection);
  }

  @Override
  public Iterator<T> iterator() {
    return items.iterator();
  }

  @Override
  public String toString() {
    return items.toString();
  }

  public static void main(String[] args) {
    System.out.println("=== Демонстрация работы множества ===\n");

    Set<Integer> setA = new Set<>();
    setA.add(1);
    setA.add(2);
    setA.add(3);
    setA.add(4);
    System.out.println("Множество A: " + setA);
    System.out.println("Количество элементов в A: " + setA.count());

    Set<Integer> setB = new Set<>();
    setB.add(3);
    setB.add(4);
    setB.add(5);
    setB.add(6);
    System.out.println("Множество B: " + setB);

    System.out.println("\n--- Операции над множествами ---");
    System.out.println("Объединение A ∪ B: " + setA.union(setB));
    System.out.println("Пересечение A ∩ B: " + setA.intersection(setB));
    System.out.println("Разность A \\ B: " + setA.difference(setB));
    System.out.println("Симметрическая разность A △ B: " + setA.symmetricDifference(setB));

    System.out.println("\nПроверка принадлежности:");
    System.out.println("Содержит ли A число 2? " + setA.contains(2));
    System.out.println("Содержит ли A число 5? " + setA.contains(5));

    System.out.println("\nУдаление элемента из A:");
    setA.remove(2);
    System.out.println("A после удаления 2: " + setA);
    System.out.println("Количество элементов: " + setA.count());

    System.out.println("\nПопытка добавить дубликат:");
    try {
      setA.add(3);
    } catch (IllegalStateException e) {
      System.out.println("Ошибка перехвачена: " + e.getMessage());
    }

    System.out.println("\nИтерация по множеству A:");
    for (Integer val : setA) {
      System.out.print(val + " ");
    }
    System.out.println();
  }
}