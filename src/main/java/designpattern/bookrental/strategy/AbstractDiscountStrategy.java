package designpattern.bookrental.strategy;

public abstract class AbstractDiscountStrategy implements DiscountStrategy{

  @Override
  public double discount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("금액은 0보다 커야 합니다.");
    }
    return calculateDiscount(amount);
  }

  protected abstract double calculateDiscount(double amount);
}
