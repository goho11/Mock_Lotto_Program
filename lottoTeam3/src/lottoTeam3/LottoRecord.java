package lottoTeam3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.StringBinding;

public class LottoRecord {
	private List<Integer> lotteryNums; // 당첨 번호
	private int lotteryBonus; // 당첨 보너스 번호
	private List<LottoData[]> buyLotto; // 구매자가 선택한 번호

	public LottoRecord() {
		lotteryNums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		buyLotto = new ArrayList<>();
	}

	public void SetLottery(List<Integer> lotteryNums, int lotteryBonus) {
		this.lotteryNums = lotteryNums;
		this.lotteryBonus = lotteryBonus;
	}

	public void addBuyLotto(LottoData[] lottoDatas) {
		buyLotto.add(lottoDatas);
	}

	public static LottoRecord tryParse(String line) {
		
		
		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(lotteryNums).append('/').append(lotteryBonus).append('/');
		for (LottoData[] lottoDatas : buyLotto) {
			stringBuilder.append(Arrays.toString(lottoDatas)).append('+');
		}
		return stringBuilder.toString();
	}

}
