package designPattern.coffeemaker;

import designPattern.coffeemaker.strategy.AmericanoStrategy;
import designPattern.coffeemaker.strategy.EspressoStrategy;
import designPattern.coffeemaker.strategy.LatteStrategy;
import designPattern.coffeemaker.strategy.ReceipeStrategy;

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
