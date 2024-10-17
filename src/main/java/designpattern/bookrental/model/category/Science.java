package designpattern.bookrental.model.category;

import designpattern.bookrental.model.enums.CategoryType;

public class Science implements Category {

  @Override
  public double getPrice() {
    return 1.5;
  }

  @Override
  public int getPeriod() {
    return 7;
  }

  @Override
  public CategoryType getCategoryId() {
    return CategoryType.SCIENCE;
  }

  @Override
  public String getCategoryName() {
    return CategoryType.SCIENCE.getCategoryName();
  }
}
