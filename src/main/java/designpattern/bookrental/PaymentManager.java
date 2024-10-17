package designpattern.bookrental;

import designpattern.bookrental.model.category.Category;
import designpattern.bookrental.factory.CategoryFactory;
import designpattern.bookrental.model.member.Member;
import designpattern.bookrental.strategy.DiscountStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentManager {

  private static PaymentManager instance;
  private Map<String, Double> paymentHistory;
  private double basicPrice;

  public PaymentManager(){
    this.basicPrice = 10000;
    this.paymentHistory = new ConcurrentHashMap<>();
  }

  public static PaymentManager getInstance(){
    if (instance == null) { // 첫 번째 체크 (동기화 없이 빠르게 확인)
      synchronized (PaymentManager.class) {
        if (instance == null) { // 두 번째 체크 (동기화 내에서 확인)
          instance = new PaymentManager();
        }
      }
    }
    return instance;
  }

  public void processPayment(Member member, Book book, DiscountStrategy discountStrategy){
    Category category = CategoryFactory.getCategory(book.getCategoryType());
    double priceByCategoryType = basicPrice * category.getPrice();
    double discountPrice = discountStrategy.discount(priceByCategoryType);
    paymentHistory.put(member.getMemberId(), discountPrice);
    System.out.println("결제가 완료 되었습니다. 정가 : " + priceByCategoryType + ", 할인 적용가 : " + discountPrice);
  }
}
