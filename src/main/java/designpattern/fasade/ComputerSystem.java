package designpattern.fasade;

public class ComputerSystem implements AutomationSystem{

  @Override
  public void switchOn() {
    System.out.println("컴퓨터 시스템을 켰습니다.");
  }

  @Override
  public void switchOff() {
    System.out.println("컴퓨터 시스템을 껐습니다.");
  }
}

