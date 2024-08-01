package lottoTeam3;

import java.util.Arrays;
import java.util.List;

public class LottoRecord {
	private List<Integer> lotteryNums;
	private int lotteryBonus;
	private List<LottoData> buyLotto;
	private List<Integer> prizeMoney;
	private int totalPrizeMoney;

	public LottoRecord() {
	}

	public void SetLottery(List<Integer> lotteryNums, int lotteryBonus) {
		this.lotteryNums = lotteryNums;
		this.lotteryBonus = lotteryBonus;
	}

	public void addBuyLotto(LottoData[] lottoDatas) {
		buyLotto.addAll(Arrays.asList(lottoDatas));
	}

	public static LottoRecord tryParse(String line) {

		return null;
	}

	@Override
	public String toString() {

		return null;
	}

}
