package designpattern.fasade;

public class ClimateControlSystem implements AutomationSystem{

  @Override
  public void switchOn() {
    System.out.println("온도 조절 시스템을 켰습니다.");
  }

  @Override
  public void switchOff() {
    System.out.println("온도 조절 시스템을 껐습니다.");
  }

  public void setTemperature(int temperature){
    System.out.println("시스템 온도를 " + temperature + "로 설정했습니다.");
  }
}
