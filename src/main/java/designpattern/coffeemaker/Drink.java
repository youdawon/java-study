package designpattern.coffeemaker;

import designpattern.coffeemaker.strategy.ReceipeStrategy;

public class Drink {

  String name;
  ReceipeStrategy receipeStrategy;

  public Drink(String name, ReceipeStrategy receipeStrategy) {
    this.name = name;
    this.receipeStrategy = receipeStrategy;
  }

  public int getMilk(){
    return receipeStrategy.getMilk();
  }

  public int getWater(){
    return receipeStrategy.getWater();
  }

  public int getCoffeeBeans(){
    return receipeStrategy.getCoffeeBeans();
  }

  public String getName(){
    return this.name;
  }

  public void setReceipeStrategy(ReceipeStrategy receipeStrategy) {
    this.receipeStrategy = receipeStrategy;
  }
}
