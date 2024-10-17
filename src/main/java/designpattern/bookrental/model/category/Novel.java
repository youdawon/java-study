package designpattern.bookrental.model.category;

import designpattern.bookrental.model.enums.CategoryType;

public class Novel implements Category {

  @Override
  public double getPrice() {
    return 1;
  }

  @Override
  public int getPeriod() {
    return 10;
  }

  @Override
  public CategoryType getCategoryId() {
    return CategoryType.NOVEL;
  }

  @Override
  public String getCategoryName() {
    return CategoryType.NOVEL.getCategoryName();
  }
}
