package designpattern.payment.strategy;

public class PaypalPayment implements PaymentStrategy {

  @Override
  public void payment(int amount) {
    System.out.println(amount + " PayPal로 결제");
  }
}
