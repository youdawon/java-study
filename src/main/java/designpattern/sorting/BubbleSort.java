package designpattern.sorting;

public class BubbleSort implements SortStrategy{

  @Override
  public void sort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
    System.out.println("Bubble Sort 완료");
  }
}
