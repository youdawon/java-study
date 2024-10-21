package designpattern.juicemixker;

public abstract class Juice {

  public void makeJuice(){
    boilWater();
    prepareFruit();
    pourJuice();
    if(customerWantsSugar()) {
      addSugar();
    }
  }

  public void boilWater(){
    System.out.println("물을 끓입니다.");
  }

  public void pourJuice(){
    System.out.println("주스를 붓습니다.");
  }

  // Hook 메서드: 기본적으로는 설탕을 넣지 않음
  public boolean customerWantsSugar() {
    return true; // 기본적으로 설탕을 넣을 수 있음
  }

  public void addSugar() {
    System.out.println("설탕을 넣습니다.");
  }

  public abstract void prepareFruit();

}
