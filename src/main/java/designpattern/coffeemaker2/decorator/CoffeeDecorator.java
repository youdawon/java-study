package designpattern.coffeemaker2.decorator;

import designpattern.coffeemaker2.Coffee;
import designpattern.coffeemaker2.CoffeeOption;

public abstract class CoffeeDecorator implements CoffeeOption {

  protected Coffee decoratedCoffee;

  public CoffeeDecorator(Coffee coffee){
    this.decoratedCoffee = coffee;
  }

  @Override
  public String getDescription() {
    return "추가한 옵션이 없습니다.";
  }
}
