package designPattern.sorting;

public class SortContext {

  private SortStrategy strategy;

  public void setStrategy(SortStrategy strategy){
    this.strategy = strategy;
  }

  public void runSort(int[] arr){
    this.strategy.sort(arr);
  }

  public static void main(String[] args){
    SortContext sortContext = new SortContext();
    int[] numbers = {5, 3, 8, 6, 2};

    sortContext.setStrategy(new BubbleSort());
    sortContext.runSort(numbers);

    sortContext.setStrategy(new QuickSort());
    sortContext.runSort(numbers);
  }
}
