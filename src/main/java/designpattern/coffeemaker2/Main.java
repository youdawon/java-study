package designpattern.coffeemaker2;

import designpattern.coffeemaker2.decorator.CoffeeDecorator;
import designpattern.coffeemaker2.decorator.MilkDecorator;

public class Main {

  public static void main(String[] args){

    Coffee coffee = new Latte();
    coffee.prepareCoffee();
    CoffeeOption coffeeOption = new MilkDecorator(coffee);
    System.out.println(coffeeOption.getDescription());
  }
}
