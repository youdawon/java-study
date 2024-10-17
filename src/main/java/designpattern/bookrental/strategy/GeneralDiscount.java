package designpattern.bookrental.strategy;

public class GeneralDiscount extends AbstractDiscountStrategy{

  @Override
  public double calculateDiscount(double price) {
    return price;
  }
}
