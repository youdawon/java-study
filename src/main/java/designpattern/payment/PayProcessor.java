package designpattern.payment;

import designpattern.payment.strategy.PaymentStrategy;
import designpattern.payment.strategy.PaypalPayment;

public class PayProcessor {

  PaymentStrategy paymentStrategy;

  public void setPaymentStrategy(PaymentStrategy paymentStrategy){
    this.paymentStrategy = paymentStrategy;
  }

  public void pay(int amount){
    this.paymentStrategy.payment(amount);
  }

  public static void main(String[] args){
    PayProcessor payProcessor = new PayProcessor();
    payProcessor.setPaymentStrategy(PaymentFactory.selectPayment("KOREA"));
    payProcessor.pay(10000);
    payProcessor.setPaymentStrategy(new PaypalPayment());
    payProcessor.pay(10000);
  }
}
