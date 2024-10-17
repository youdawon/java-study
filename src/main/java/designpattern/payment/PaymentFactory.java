package designpattern.payment;

import designpattern.payment.strategy.CreditCardPayment;
import designpattern.payment.strategy.GooglePayment;
import designpattern.payment.strategy.PaymentStrategy;
import designpattern.payment.strategy.PaypalPayment;

public class PaymentFactory {

  public static PaymentStrategy selectPayment(String location){

    switch (location){
      case "KOREA" :
        return new CreditCardPayment();
      case "USA" :
        return new PaypalPayment();
      case "GERMANY":
        return new GooglePayment();
      default:
        throw new IllegalArgumentException("지원하지 않는 결제 방식");
    }
  }
}
