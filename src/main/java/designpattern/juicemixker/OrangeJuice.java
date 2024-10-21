package designpattern.juicemixker;

public class OrangeJuice  extends Juice{

  @Override
  public void prepareFruit() {
    System.out.println("사과를 준비합니다.");
  }

  @Override
  public boolean customerWantsSugar() {
    return false;
  }
}