package designpattern.bookrental;

import designpattern.bookrental.model.enums.CategoryType;
import designpattern.bookrental.model.enums.MemberLevel;
import designpattern.bookrental.model.member.Member;
import designpattern.bookrental.strategy.DiscountStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RentalManager {

  private static RentalManager instance;
  private Map<String, Member> members;
  private Map<String, Book> books;
  private PaymentManager paymentManager;
  private HistoryManager historyManager;
  private AtomicLong bookNumber;
  private AtomicLong memberNumber;

  public static RentalManager getInstance(){
    if (instance == null) { // 첫 번째 체크 (동기화 없이 빠르게 확인)
      synchronized (RentalManager.class) {
        if (instance == null) { // 두 번째 체크 (동기화 내에서 확인)
          instance = new RentalManager();
        }
      }
    }
    return instance;
  }

  public RentalManager() {
    this.members = new ConcurrentHashMap<>();
    this.books = new ConcurrentHashMap<>();
    this.bookNumber = new AtomicLong(0);
    this.memberNumber = new AtomicLong(0);
    this.paymentManager = PaymentManager.getInstance();
    this.historyManager = HistoryManager.getInstance();
  }

  public Member registerMember(String memberName, MemberLevel memberLevel){
    String memberId = "member_" + memberNumber.getAndIncrement();
    members.put(memberId, new Member(memberId, memberName, memberLevel));
    return members.get(memberId);
  }

  public String addBook(String bookName, CategoryType categoryType){
    String bookId = "book_" + bookNumber.getAndIncrement();
    try {

      books.put(bookId, new Book(bookId, bookName, categoryType));
    }catch (Exception e){
      e.printStackTrace();
    }
    return bookId;
  }

  public synchronized void rentBook(String bookId, Member member, DiscountStrategy discountStrategy){
    if(!historyManager.isBookAvailable(bookId)){
      System.out.println("이미 대여중인 책입니다.");
      return;
    }
    Book book = books.get(bookId);
    paymentManager.processPayment(member, book, discountStrategy);
    historyManager.updateRentalHistory(member, book);
  }
}
