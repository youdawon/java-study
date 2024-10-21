package designpattern.fasade;

/**
 * 문제 설명:
 *
 * 어떤 회사에서 직원들의 업무 환경을 자동화하는 시스템을 설계하려고 합니다. 이 시스템은 직원들이 출근하거나 퇴근할 때, 여러 가지 장치들을 한꺼번에 제어하여 업무 환경을 편리하게 조성해 줍니다. 퍼사드 패턴을 사용하여 이 시스템을 설계해보세요.
 *
 * 자동화 시스템의 주요 장치들:
 *
 * 컴퓨터 시스템(ComputerSystem): 컴퓨터를 켜고 끌 수 있습니다.
 *
 * 조명 시스템(LightingSystem): 사무실 조명을 켜고 끌 수 있습니다.
 *
 * 난방/냉방 시스템(ClimateControlSystem): 온도를 설정하고 켜고 끌 수 있습니다.
 *
 * 기능 요구사항:
 *
 * 업무 시작 시, 컴퓨터를 켜고 조명을 켜며 난방/냉방 시스템을 적정 온도로 설정하는 단일 명령을 제공합니다.
 *
 * 업무 종료 시, 컴퓨터와 조명을 끄고 난방/냉방 시스템을 비활성화하는 명령을 제공합니다.
 *
 * 각 장치는 개별적으로도 제어할 수 있어야 하며, 퍼사드 클래스를 통해 복합적인 제어가 가능해야 합니다.
 *
 * 요구사항 요약:
 *
 * OfficeAutomationFacade 클래스를 설계하여 ComputerSystem, LightingSystem,
 * ClimateControlSystem을 쉽게 제어할 수 있는 인터페이스를 제공합니다.
 *
 * 사용자는 퍼사드 클래스를 통해 업무 시작 및 업무 종료를 간단한 명령으로 실행할 수 있어야 합니다.
 *
 * 각 장치는 독립적으로 제어할 수 있는 기능도 있어야 합니다.
 *
 * 이 문제를 해결하면서 퍼사드 패턴을 활용하여 복잡한 서브시스템을 단순하게 사용하는 방법을 연습해 보세요.
 */
public class OfficeAutomationSystem {

  public static void main(String[] args){
    ClimateControlSystem climateControlSystem = new ClimateControlSystem();
    ComputerSystem computerSystem = new ComputerSystem();
    LightingSystem lightingSystem = new LightingSystem();

    OfficeAutomationFacade officeAutomationFacade = new OfficeAutomationFacade(computerSystem,
        lightingSystem, climateControlSystem);

    officeAutomationFacade.switchOn();
    climateControlSystem.setTemperature(29);
    officeAutomationFacade.switchOff();
  }

}
