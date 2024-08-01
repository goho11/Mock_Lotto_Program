package lottoTeam3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoRecord {
	private List<Integer> lotteryNums; // 당첨 번호
	private int lotteryBonus; // 당첨 보너스 번호
	private List<LottoData[]> buyLotto; // 구매자가 선택한 번호

	public List<LottoData[]> getBuyLotto() {
		return buyLotto;
	}

	public LottoRecord() {
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
		LottoRecord lottoRecord = new LottoRecord();
		String[] splitFields = line.split("/");
		String[] strNums = splitFields[0].substring(1, splitFields[0].length() - 1).split(", ");
		Integer[] iNums = new Integer[6];
		for (int i = 0; i < iNums.length; i++) {
			iNums[i] = Integer.valueOf(strNums[i]);
		}
		lottoRecord.lotteryNums = Arrays.asList(iNums);
		lottoRecord.lotteryBonus = Integer.parseInt(splitFields[1]);
		String[] splitBuy = splitFields[2].split("\\+");
		for (int i = 0; i < splitBuy.length; i++) {
			LottoData[] lottoDatas = new LottoData[5];
			String[] splitGame = splitBuy[i].substring(1, splitBuy[i].length() - 1).split(", ");
			for (int j = 0; j < splitGame.length; j++) {
				if (splitGame[j].equals("null")) {
					lottoDatas[j] = null;
					continue;
				}
				String[] splitLottoData = splitGame[j].split(" ");
				int[] nums = new int[6];
				for (int k = 0; k < splitLottoData.length - 1; k++) {
					nums[k] = Integer.parseInt(splitLottoData[k]);
				}
				Mode mode = Mode.getMode(splitLottoData[6]);
				lottoDatas[j] = new LottoData(nums, mode);
			}
			lottoRecord.buyLotto.add(lottoDatas);
		}
		return lottoRecord;
	}

	public LottoData[] getLottoDatas(int index) {
		if (index < buyLotto.size())
			return buyLotto.get(index);
		return null;
	}

	public boolean hasBought() {
		return !buyLotto.isEmpty();
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

//	public static void main(String[] args) {
//		String s = "[1, 2, 3, 4, 5, 6]/0/[8 12 15 24 29 35 AUTO, 16 20 21 27 30 40 AUTO, 1 7 26 33 38 41 AUTO, 5 7 10 16 21 43 AUTO, null]+[6 14 28 29 39 43 AUTO, 4 9 10 19 24 43 AUTO, 9 19 22 23 31 41 AUTO, 13 17 18 21 35 42 AUTO, 3 7 11 15 25 37 AUTO]+[1 8 12 25 43 45 AUTO, 4 9 19 24 29 39 AUTO, 8 14 19 26 37 42 AUTO, 17 25 28 31 34 38 AUTO, 12 29 31 33 37 42 AUTO]+";
//		LottoRecord lr = LottoRecord.tryParse(s);
//		System.out.println(lr.getLottoDatas(0));
//		System.out.println(s.equals(lr.toString()));
//	}

}
