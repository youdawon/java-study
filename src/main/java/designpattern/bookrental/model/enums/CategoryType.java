package designpattern.bookrental.model.enums;

public enum CategoryType {
  SCIENCE("과학"),
  HISTORY("역사"),
  NOVEL("소설 ");

  private String categoryName;

  CategoryType(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return categoryName;
  }
}