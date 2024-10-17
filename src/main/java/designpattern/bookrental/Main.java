package designpattern.bookrental;

import designpattern.bookrental.model.enums.CategoryType;
import designpattern.bookrental.model.enums.MemberLevel;
import designpattern.bookrental.model.member.Member;
import designpattern.bookrental.strategy.GeneralDiscount;
import designpattern.bookrental.strategy.PremiumDiscount;
import designpattern.bookrental.strategy.VIPDiscount;

public class Main {
  public static void main(String[] args) {
    // 대여 시스템 초기화 (싱글턴을 사용할 경우)
    RentalManager rentalManager = RentalManager.getInstance();

    // 대여할 책 정보 설정
    String bookId_science = rentalManager.addBook("Effective Java", CategoryType.SCIENCE);
    String bookId_history = rentalManager.addBook("Cracking the coding interviews",
        CategoryType.HISTORY);
    String bookId_novel = rentalManager.addBook("fish", CategoryType.NOVEL);


    Member regularMember = rentalManager.registerMember("John Doe", MemberLevel.REGULAR);
    Member premiumMember = rentalManager.registerMember("Jane Smith", MemberLevel.PREMIUM);
    Member vipMember = rentalManager.registerMember("Alice Brown", MemberLevel.VIP);

    // 회원 등급에 따른 할인 테스트
    System.out.println("=== 일반 회원 대여 테스트 ===");
    rentalManager.rentBook(bookId_science, regularMember, new GeneralDiscount());

    System.out.println("=== 프리미엄 회원 대여 테스트 ===");
    rentalManager.rentBook(bookId_history, premiumMember, new PremiumDiscount());

    System.out.println("=== VIP 회원 대여 테스트 ===");
    rentalManager.rentBook(bookId_novel, vipMember, new VIPDiscount());

  }
}
