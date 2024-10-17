package designpattern.payment;

/**
 * 좋습니다! **전략 패턴과 팩토리 패턴을 함께 적용**할 수 있는 문제를 하나 드릴게요.
 *
 * ---
 *
 * ### 문제: 온라인 스토어 - 결제 시스템
 *
 * 온라인 스토어에서 고객이 다양한 결제 방법을 통해 결제를 할 수 있도록 결제 시스템을 설계하려고 합니다.
 * 결제 방법은 **신용카드, 페이팔, 구글 페이** 등 여러 옵션이 있으며, 각 결제 방법마다 처리 방식이 다릅니다.
 *
 * 또한, **결제 방식은 국가마다 다르게 설정**될 수 있어서,
 * 사용자의 위치(국가)에 맞는 결제 방식을 자동으로 선택해주는 기능도 필요합니다.
 * 예를 들어, 한국에서는 신용카드 결제를 주로 제공하고, 미국에서는 페이팔 결제를,
 * 독일에서는 구글 페이 결제를 기본 결제 방식으로 제공합니다.
 *
 * #### 요구사항
 * 1. **팩토리 패턴**을 사용해 각 국가에 맞는 기본 결제 방식 객체를 생성하도록 하세요.
 * 2. **전략 패턴**을 사용해 사용자가 원하는 결제 방식을 동적으로 선택하고 변경할 수 있도록 하세요.
 * 3. 사용자는 기본 설정된 결제 방식 외에도 다른 결제 방법으로 변경할 수 있어야 합니다.
 * 4. 결제 방식은 **인터페이스를 통해 같은 메서드(`pay`)**를 사용하여 결제를 수행할 수 있어야 합니다.
 *
 * #### 제약 사항
 * - 모든 결제 방식은 같은 인터페이스(예: `PaymentStrategy`)를 구현해야 합니다.
 * `pay(int amount)` 메서드를 통해 결제를 수행합니다.
 * - 사용자는 결제 방법을 변경할 수 있어야 하며, **팩토리 패턴과 전략 패턴을 함께 사용**하여 설계합니다.
 *
 * ---
 *
 * ### 예시 시나리오
 *
 * 1. 사용자가 한국에서 접속하면 기본 결제 방식으로 **신용카드 결제**가 선택됩니다.
 * 2. 사용자가 결제 페이지에서 **페이팔 결제**로 변경한 후 결제를 진행할 수 있습니다.
 * 3. 사용자가 미국에서 접속하면 기본 결제 방식으로 **페이팔 결제**가 선택됩니다.
 * 4. 결제 금액을 입력하면 현재 설정된 결제 방식에 따라 결제가 수행됩니다.
 *
 * ---
 */
public class Shopping {

}
