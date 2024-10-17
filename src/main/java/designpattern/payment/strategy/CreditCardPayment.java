package designpattern.payment.strategy;

public class CreditCardPayment implements PaymentStrategy{

  @Override
  public void payment(int amount) {
    System.out.println(amount + " 신용카드로 결제");
  }
}
