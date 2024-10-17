package designpattern.bookrental.factory;

import designpattern.bookrental.model.category.Category;
import designpattern.bookrental.model.category.History;
import designpattern.bookrental.model.category.Novel;
import designpattern.bookrental.model.category.Science;
import designpattern.bookrental.model.enums.CategoryType;

public class CategoryFactory{

  public static Category getCategory(CategoryType categoryType){

    switch (categoryType) {
      case SCIENCE:
        return new Science();
      case HISTORY:
        return new History();
      case NOVEL:
        return new Novel();
      default:
        throw new IllegalArgumentException("존재하지 않는 카테고리");
    }
  }
}
