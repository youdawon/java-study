package designpattern.singleton;

public class Logger {

  private static Logger instance;

  public Logger() {
  }

  public static Logger getInstance() {
    if(instance == null){
      instance = new Logger();
    }
    return instance;
  }

  public void log(String message) {
    System.out.println("Log: " + message);
  }

  public static void main(String[] args){
    Logger logger1 = Logger.getInstance();

    logger1.log("test");

    Logger logger2 = Logger.getInstance();

    logger2.log("test2");

    System.out.println(logger1 == logger2);
  }
}
