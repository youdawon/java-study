package designpattern.coffeemaker2.decorator;

import designpattern.coffeemaker2.Coffee;

public class SugarDecorator extends CoffeeDecorator {

  public SugarDecorator(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription() + ",설탕을 추가하였습니다.";
  }
}
