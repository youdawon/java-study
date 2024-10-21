package designpattern.coffeemaker2.decorator;

import designpattern.coffeemaker2.Coffee;

public class SyrupDecorator extends CoffeeDecorator{

  public SyrupDecorator(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription() + ",시럽을 추가하였습니다.";
  }
}
