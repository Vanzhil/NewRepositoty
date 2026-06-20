import java.util.NoSuchElementException;

public class Queue<T> {

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public Queue() {
    head = null;
    tail = null;
    size = 0;
  }

  public void enqueue(T item) {
    Node<T> newNode = new Node<>(item);

    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Очередь пуста");
    }

    T result = head.data;
    head = head.next;
    size--;

    if (head == null) {
      tail = null; // очередь стала пустой
    }

    return result;
  }

  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Очередь пуста");
    }
    return head.data;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public static void main(String[] args) {
    Queue<String> queue = new Queue<>();

    System.out.println("=== Демонстрация работы очереди (FIFO) ===\n");

    System.out.println("Добавляем: 'Первый', 'Второй', 'Третий'");
    queue.enqueue("Первый");
    queue.enqueue("Второй");
    queue.enqueue("Третий");

    System.out.println("Размер очереди: " + queue.size()); // 3
    System.out.println("Первый элемент (peek): " + queue.peek()); // Первый

    System.out.println("\nИзвлекаем элементы (dequeue):");
    while (!queue.isEmpty()) {
      System.out.println("  " + queue.dequeue());
    }

    System.out.println("\nОчередь пуста: " + queue.isEmpty()); // true
    System.out.println("Размер после извлечения: " + queue.size()); // 0

    System.out.println("\nПопытка dequeue из пустой очереди:");
    try {
      queue.dequeue();
    } catch (NoSuchElementException e) {
      System.out.println("  Ошибка перехвачена: " + e.getMessage());
    }
  }
}