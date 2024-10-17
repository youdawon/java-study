package designpattern.coffeemaker.strategy;

public class LatteStrategy implements ReceipeStrategy{

  @Override
  public int getWater() {
    return 2;
  }

  @Override
  public int getMilk() {
    return 2;
  }

  @Override
  public int getCoffeeBeans() {
    return 2;
  }
}
