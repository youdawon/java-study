package designpattern.bookrental.strategy;

public class VIPDiscount extends AbstractDiscountStrategy{

  @Override
  public double calculateDiscount(double price) {
    return price - price * 0.2;
  }
}
