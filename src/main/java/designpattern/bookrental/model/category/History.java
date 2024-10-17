package designpattern.bookrental.model.category;

import designpattern.bookrental.model.enums.CategoryType;

public class History implements Category {

  @Override
  public double getPrice() {
    return 1.2;
  }
  @Override
  public int getPeriod() {
    return 14;
  }

  @Override
  public CategoryType getCategoryId() {
    return CategoryType.HISTORY;
  }

  @Override
  public String getCategoryName() {
    return CategoryType.HISTORY.getCategoryName();
  }
}
