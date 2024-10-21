package designpattern.coffeemaker2;

public class Latte extends Coffee{

  @Override
  public boolean customerWantedMilk() {
    return true;
  }

  @Override
  public String getDescription() {
    return "라테가 준비되었습니다.";
  }
}
