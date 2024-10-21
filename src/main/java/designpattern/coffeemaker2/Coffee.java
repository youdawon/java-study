package designpattern.coffeemaker2;

public abstract class Coffee {

  public void prepareCoffee(){
    boilWater();
    extractCoffee();
    if(customerWantedMilk()){
      addMilk();
    }
    mixIngredient();
  }

  public void boilWater(){
    System.out.println("물을 끓입니다.");
  }

  public void extractCoffee(){
    System.out.println("커피를 추출합니다.");
  }

  public void addMilk(){
    System.out.println("우유를 추가합니다");
  }

  public void mixIngredient(){
    System.out.println("재료를 혼합합니다.");
  }

  public abstract boolean customerWantedMilk();

  public abstract String getDescription();
}
