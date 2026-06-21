public class LinkedList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        count++;
    }

    public boolean remove(T item) {
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null) {
            if (current.value.equals(item)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null) {
                        tail = previous;
                    }
                } else {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                }
                count--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void copyTo(T[] array, int arrayIndex) {
        Node<T> current = head;
        int index = arrayIndex;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[count];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            result[index++] = current.value;
            current = current.next;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Односвязный список ===\n");

        LinkedList<String> list = new LinkedList<>();

        System.out.println("Добавляем элементы: 'Первый', 'Второй', 'Третий'");
        list.add("Первый");
        list.add("Второй");
        list.add("Третий");
        System.out.println("Список: " + list);
        System.out.println("Количество элементов: " + list.count());

        System.out.println("\nДобавляем 'Четвертый'");
        list.add("Четвертый");
        System.out.println("Список: " + list);

        System.out.println("\nСодержит 'Второй'? " + list.contains("Второй"));
        System.out.println("Содержит 'Пятый'? " + list.contains("Пятый"));

        System.out.println("\nУдаляем 'Второй': " + list.remove("Второй"));
        System.out.println("Список после удаления: " + list);

        System.out.println("\nУдаляем 'Первый': " + list.remove("Первый"));
        System.out.println("Список после удаления: " + list);

        System.out.println("\nУдаляем 'Четвертый': " + list.remove("Четвертый"));
        System.out.println("Список после удаления: " + list);

        System.out.println("\nПопытка удалить 'Пятый': " + list.remove("Пятый"));

        System.out.println("\nДобавляем элементы для проверки:");
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("Список: " + list);

        System.out.println("\nКопируем в массив:");
        String[] array = new String[list.count()];
        list.copyTo(array, 0);
        System.out.print("Массив: ");
        for (String s : array) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("\nОчищаем список...");
        list.clear();
        System.out.println("Список пуст? " + list.isEmpty());
        System.out.println("Количество элементов: " + list.count());
    }
}