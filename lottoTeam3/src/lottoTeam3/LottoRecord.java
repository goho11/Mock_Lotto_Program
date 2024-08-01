package lottoTeam3;

import java.util.ArrayList;
import java.util.List;

public class LottoRecord {
	// 해당 회차 우승 번호 저장
	private List<Integer> lotteryNums;
	// 해당 회차 보너스 따로 저장
	private int lotteryBonus;
	// 해당 회차 구매한 로또 목록들
	private List<LottoData[]> buyLotto;
	
	private List<Integer> prizeMoney;
	private int totalPrizeMoney;

	public List<LottoData[]> getBuyLotto() {
		return buyLotto;
	}

	public LottoRecord() {
		lotteryNums = new ArrayList<>();
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
		for (LottoData[] lottoDatas : buyLotto) {
			for (LottoData lottoData : lottoDatas) {
				stringBuilder.append(lottoData).append(' ');
			}
			stringBuilder.append('\n');
		}

		return stringBuilder.toString();
	}

}
