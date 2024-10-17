package designpattern.coffeemaker;

import designpattern.coffeemaker.strategy.AmericanoStrategy;
import designpattern.coffeemaker.strategy.EspressoStrategy;
import designpattern.coffeemaker.strategy.LatteStrategy;

public class DrinkFactory {

  public static Drink selectDrink(String type){
    switch(type){
      case "Espresso":
        return new Drink("Espresso", new EspressoStrategy());
      case "Americano":
        return new Drink("Americano", new AmericanoStrategy());
      case "Latte":
        return new Drink("Latte", new LatteStrategy());
      default:
        throw new IllegalArgumentException("지원하지 않는 음료입니다.");
    }
  }
}
