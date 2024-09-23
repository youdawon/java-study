package etc;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * 문제 설명:
 * 카드 번호는 16자리 숫자이며, 예를 들어 4444 4444 4444 4444처럼 생겼습니다.
 * BIN 번호는 카드 번호의 처음 몇 자리 숫자로, 예를 들어 4444 4444 44가 BIN 번호입니다.
 * 입력으로는 BIN 번호 범위와 그 범위에 해당하는 카드 유형이 주어집니다.
 * 처음에 캐시를 만들어야 하고, 이후 주어진 카드 번호에 대해 카드 유형을 반환해야 합니다.
 * 이 연산은 대략 O(1) 시간 복잡도로 이루어져야 한다고 가정합니다.
 *
 * 예시:
 * 입력:
 *
 * BIN 범위 객체 = [["4444 4444 11", "4444 4444 44", "Visa credit"],
 * ["4500 0000 55", "4999 9999 00", "Visa debit"], ["4999 9999 99", "5555 0000 00", "Master credit"],
 * ["6666 4444 11", "7777 0000 00", "Amex"]].
 * 카드 번호: 4733 6109 7901 2139
 * 출력: Visa debit
 *
 * 설명: 4733 6109 7901 2139의 BIN은 4733 6109 79이며,
 * 이는 Visa debit의 BIN 범위(4500 0000 55 ~ 4999 9999 00)에 속합니다.
 *
 * BIN 범위 객체는 배열의 배열이며, 각 내부 배열의 [0]은 범위 시작, [1]은 범위 끝, [2]는 그 범위에 대한 카드 유형을 나타냅니다.
 * 주어진 카드 유형들은 연속적인 BIN 범위를 가질 필요가 없습니다.
 * 예를 들어, 카드 번호 6000 0000 0000 0000이 주어지면 이는 어떤 카드 유형에도 속하지 않으므로 null을 반환해야 합니다.
 */

public class Card {

	public Map<String, String> cardRanges = new TreeMap<>();

	public String returnCardType(String cardNumber){
		cardNumber = cardNumber.replace(" ", "").substring(0, 10);

		for(String cardInfos : this.cardRanges.keySet()){
			String startBin = cardInfos.split("_")[0];
			String endBin = cardInfos.split("_")[1];

			if(startBin.compareTo(cardNumber) <= 0 && endBin.compareTo(cardInfos) >= 0){
				return this.cardRanges.get(startBin + "_" +  endBin);
			}
		}

		return null;
	}


	public void setCardRanges(String[][] binRanges){
		for(String[] binRange : binRanges){
			String start = binRange[0].replace(" ", "");
			String end = binRange[1].replace(" ", "");
			String cardType = binRange[2];
			this.cardRanges.put(start + "_" + end , cardType);
		}
	}


	public static void main(String[] args){
		String[][] binRanges =  {
			{"4444 4444 11", "4444 4444 44", "Visa credit"},
			{"4500 0000 55", "4999 9999 00", "Visa debit"},
			{"4999 9999 99", "5555 0000 00", "Master credit"},
			{"6666 4444 11", "7777 0000 00", "Amex"}
		};

		String currCard = "4733 6109 7901 2139";;

		Card card = new Card();
		card.setCardRanges(binRanges);

	}
}
