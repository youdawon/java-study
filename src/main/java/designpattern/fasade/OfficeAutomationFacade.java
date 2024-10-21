package designpattern.fasade;

public class OfficeAutomationFacade {

  private ClimateControlSystem climateControlSystem;
  private ComputerSystem computerSystem;
  private LightingSystem lightingSystem;

  public OfficeAutomationFacade(ComputerSystem computerSystem, LightingSystem lightingSystem, ClimateControlSystem climateControlSystem) {
    this.computerSystem = computerSystem;
    this.lightingSystem = lightingSystem;
    this.climateControlSystem = climateControlSystem;
  }

  public final void switchOn(){
    System.out.println("업무 시작 준비 중...");
    this.climateControlSystem.switchOn();
    this.computerSystem.switchOn();
    this.lightingSystem.switchOn();
  }

  public final void switchOff(){
    System.out.println("업무 종료 준비 중...");
    this.climateControlSystem.switchOff();
    this.computerSystem.switchOff();
    this.lightingSystem.switchOff();
  }
}
