package designpattern.bookrental;

import java.time.LocalDate;

public class RentalHistory {

  private String bookId;
  private String memberId;
  private LocalDate rentDate;
  private LocalDate expirationDate;

  public RentalHistory(String bookId, String memberId) {
    this.bookId = bookId;
    this.memberId = memberId;
    this.rentDate = LocalDate.now();
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getBookId() {
    return bookId;
  }

  public String getMemberId() {
    return memberId;
  }

  public LocalDate getRentDate() {
    return rentDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }
}
