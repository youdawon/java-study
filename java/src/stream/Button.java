package stream;

public class Button {

	// @FunctionalInterface
	// public static interface ClickListener {
	// 	void onClick();
	// }
	//
	// private ClickListener clickListener;
	//
	// public void setClickListener(ClickListener clickListener){
	// 	this.clickListener = clickListener;
	// }
	//
	// public void click(){
	// 	this.clickListener.onClick();
	// }
	//
	// public static void main(String[] args){
	// 	Button btnOk = new Button();
	// 	btnOk.setClickListener(() -> System.out.println("Ok 버튼을 클릭했습니다."));
	// 	btnOk.click();
	//
	// 	Button btnCancel = new Button();
	// 	btnCancel.setClickListener(() -> System.out.println("Cancel 버튼을 클릭했습니다."));
	// 	btnCancel.click();
	// }

	@FunctionalInterface
	public interface Function{
		double apply(int a, int y);
	}

	public static double calc(Function fun){
		double x = 10;
		double y = 4;
		return fun.apply(10, 4);
	}

	public static void main(String[] args){
		double result = calc((x, y) -> (x / y));
		System.out.println("resut " + result);
	}


}
