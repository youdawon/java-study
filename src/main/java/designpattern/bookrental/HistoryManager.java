package designpattern.bookrental;

import designpattern.bookrental.model.category.Category;
import designpattern.bookrental.factory.CategoryFactory;
import designpattern.bookrental.model.member.Member;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HistoryManager {

  private static HistoryManager instance;
  private Map<String, RentalHistory> rentalHistories;


  public static HistoryManager getInstance(){
    if (instance == null) { // 첫 번째 체크 (동기화 없이 빠르게 확인)
      synchronized (HistoryManager.class) {
        if (instance == null) { // 두 번째 체크 (동기화 내에서 확인)
          instance = new HistoryManager();
        }
      }
    }
    return instance;
  }

  public HistoryManager() {
    this.rentalHistories = new ConcurrentHashMap<>();
  }

  void updateRentalHistory(Member member, Book book){
    RentalHistory rentalHistory = new RentalHistory(member.getMemberId(), book.getBookId());
    Category category = CategoryFactory.getCategory(book.getCategoryType());
    rentalHistory.setExpirationDate(rentalHistory.getRentDate().plusDays(category.getPeriod()));
    rentalHistories.put(book.getBookId(), rentalHistory);
  }

  public boolean isBookAvailable(String bookId){
    if(rentalHistories.containsKey(bookId)){
      return false;
    }
    return true;
  }
}
