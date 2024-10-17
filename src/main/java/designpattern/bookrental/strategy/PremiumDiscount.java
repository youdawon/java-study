package designpattern.bookrental.strategy;

public class PremiumDiscount extends AbstractDiscountStrategy{

  @Override
  public double calculateDiscount(double price) {
    return price - price * 0.1;
  }
}
