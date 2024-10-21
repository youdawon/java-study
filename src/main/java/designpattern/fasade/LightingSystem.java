package designpattern.fasade;

public class LightingSystem implements AutomationSystem{

  @Override
  public void switchOn() {
    System.out.println("조명 시스템을 켰습니다.");
  }

  @Override
  public void switchOff() {
    System.out.println("조명 시스템을 껐습니다.");
  }
}

