import java.util.Random;

public class SortComparison {

  public static <T extends Comparable<T>> void quickSort(T[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }

  private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
    T pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (arr[j].compareTo(pivot) <= 0) {
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, high);
    return i + 1;
  }

  private static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // @SuppressWarnings("unchecked")- нужна , чтобы компилятор игнорировал
  // придупреждения
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSort(T[] arr) {
    if (arr.length <= 1)
      return;
    T[] left = (T[]) new Comparable[arr.length / 2];
    T[] right = (T[]) new Comparable[arr.length - left.length];
    System.arraycopy(arr, 0, left, 0, left.length);
    System.arraycopy(arr, left.length, right, 0, right.length);
    mergeSort(left);
    mergeSort(right);
    merge(arr, left, right);
  }

  private static <T extends Comparable<T>> void merge(T[] result, T[] left, T[] right) {
    int i = 0, j = 0, k = 0;
    while (i < left.length && j < right.length) {
      if (left[i].compareTo(right[j]) <= 0) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      }
    }
    while (i < left.length)
      result[k++] = left[i++];
    while (j < right.length)
      result[k++] = right[j++];
  }

  public static Integer[] generateRandomArray(int size) {
    Random rand = new Random();
    Integer[] arr = new Integer[size];
    for (int i = 0; i < size; i++) {
      arr[i] = rand.nextInt(1000000);
    }
    return arr;
  }

  public static <T> void copyArray(T[] src, T[] dst) {
    System.arraycopy(src, 0, dst, 0, src.length);
  }

  public static <T> void printArray(T[] arr, int limit) {
    for (int i = 0; i < Math.min(limit, arr.length); i++) {
      System.out.print(arr[i] + (i < Math.min(limit, arr.length) - 1 ? ", " : ""));
    }
    if (arr.length > limit)
      System.out.print(", ...");
    System.out.println();
  }

  public static long measureTime(Runnable sortTask) {
    long start = System.nanoTime();
    sortTask.run();
    return System.nanoTime() - start;
  }

  public static void main(String[] args) {
    int[] sizes = { 100, 1000, 5000, 10000, 50000, 100000 };

    System.out.println("===== Сравнение времени сортировки (в миллисекундах) =====");
    System.out.printf("%-10s %-15s %-15s\n", "Размер", "Быстрая", "Слиянием");
    System.out.println("---------------------------------------------");

    for (int size : sizes) {
      Integer[] original = generateRandomArray(size);
      Integer[] arrQuick = new Integer[size];
      Integer[] arrMerge = new Integer[size];

      copyArray(original, arrQuick);
      copyArray(original, arrMerge);

      long timeQuick = measureTime(() -> quickSort(arrQuick));

      long timeMerge = measureTime(() -> mergeSort(arrMerge));

      double msQuick = timeQuick / 1_000_000.0;
      double msMerge = timeMerge / 1_000_000.0;

      System.out.printf("%-10d %-15.3f %-15.3f\n", size, msQuick, msMerge);

      if (size == 100) {
        System.out.println("\nПример первых 10 элементов после сортировки (быстрая):");
        printArray(arrQuick, 10);
        System.out.println("Пример первых 10 элементов после сортировки (слиянием):");
        printArray(arrMerge, 10);
      }
    }
  }
}