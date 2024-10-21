package designpattern.coffeemaker2.decorator;

import designpattern.coffeemaker2.Coffee;

public class MilkDecorator extends CoffeeDecorator{

  public MilkDecorator(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription() + ", 우유를 추가하였습니다.";
  }
}
