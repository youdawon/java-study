package designpattern.bookrental.model.category;

import designpattern.bookrental.model.enums.CategoryType;

public interface Category {

  public CategoryType getCategoryId();
  public String getCategoryName();
  public double getPrice();
  public int getPeriod();


}
