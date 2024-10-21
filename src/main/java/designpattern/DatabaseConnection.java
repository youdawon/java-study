package designpattern;

public class DatabaseConnection {

  private static DatabaseConnection instance;

  public DatabaseConnection(){
    System.out.println("데이터베이스가 생성되었습니다.");
  }

  public static DatabaseConnection getInstance(){
    if(instance == null){
      synchronized (DatabaseConnection.class){
        if(instance == null){
          instance = new DatabaseConnection();
        }
      }
    }
    return instance;
  }

  public static void main(String[] args){
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
  }
}
