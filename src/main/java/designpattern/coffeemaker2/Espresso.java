package designpattern.coffeemaker2;

public class Espresso extends Coffee {

  @Override
  public boolean customerWantedMilk() {
    return false;
  }

  @Override
  public String getDescription() {
    return "에스프레소가 준비되었습니다.";
  }
}
