package designpattern.coffeemaker.strategy;

public class AmericanoStrategy implements ReceipeStrategy{

  @Override
  public int getWater() {
    return 3;
  }

  @Override
  public int getMilk() {
    return 0;
  }

  @Override
  public int getCoffeeBeans() {
    return 2;
  }
}
