package designpattern.payment.strategy;

public class GooglePayment implements PaymentStrategy{

  @Override
  public void payment(int amount) {
    System.out.println(amount + " 구글페이로 결제");
  }
}
