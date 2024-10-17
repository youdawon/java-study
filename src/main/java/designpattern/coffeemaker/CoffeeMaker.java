package designpattern.coffeemaker;

import designpattern.coffeemaker.strategy.LatteStrategy;
import designpattern.coffeemaker.strategy.ReceipeStrategy;

public class CoffeeMaker {

  private int water;
  private int coffeeBeans;
  private int milk;

  public CoffeeMaker(int water, int coffeeBeans, int milk) {
    this.water = water;
    this.coffeeBeans = coffeeBeans;
    this.milk = milk;
  }

  public String makeDrink(String name){
    Drink drink = DrinkFactory.selectDrink(name);

    if(water < drink.getWater() ||
    coffeeBeans < drink.getCoffeeBeans() ||
    milk < drink.getMilk()){
      return "재료가 부족합니다.";
    }

    water -= drink.getWater();
    coffeeBeans -= drink.getCoffeeBeans();
    milk -= drink.getMilk();

    return drink.getName() + "가 준비되었습니다.";
  }

  public void refill(int water, int coffeeBeans, int milk){
    this.water += water;
    this.coffeeBeans += coffeeBeans;
    this.milk += milk;
    System.out.println("재료가 보충되었습니다.");
  }

  public static void main(String[] args){

    CoffeeMaker coffeeMaker = new CoffeeMaker(10, 10, 10);
    Drink drink = new Drink("latte", new LatteStrategy());
    drink.setReceipeStrategy(new ReceipeStrategy() {
      @Override
      public int getWater() {
        return 4;
      }

      @Override
      public int getMilk() {
        return 5;
      }

      @Override
      public int getCoffeeBeans() {
        return 3;
      }
    });

    System.out.println(coffeeMaker.makeDrink("Latte"));
  }
}
