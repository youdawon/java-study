package designpattern.bookrental;

import designpattern.bookrental.model.enums.CategoryType;
import java.time.LocalDate;

public class Book {

  private String bookId;
  private String bookName;
  private CategoryType categoryType;
  private LocalDate creationDate;

  public Book(String bookId, String bookName, CategoryType categoryType) {
    this.bookId = bookId;
    this.bookName = bookName;
    this.categoryType = categoryType;
    this.creationDate = LocalDate.now();
  }

  public String getBookId() {
    return bookId;
  }

  public String getBookName() {
    return bookName;
  }

  public CategoryType getCategoryType() {
    return categoryType;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }
}


