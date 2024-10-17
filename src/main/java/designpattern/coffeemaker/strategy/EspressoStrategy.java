package designpattern.coffeemaker.strategy;

public class EspressoStrategy implements ReceipeStrategy{

  @Override
  public int getWater() {
    return 1;
  }

  @Override
  public int getMilk() {
    return 0;
  }

  @Override
  public int getCoffeeBeans() {
    return 3;
  }
}
