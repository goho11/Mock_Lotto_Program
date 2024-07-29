package lottoTeam3;

public class LottoData {
	private int[] nums = new int[6];
	private boolean buy;

	public LottoData(int[] nums, boolean buy) {
		this.nums = nums;
		this.buy = buy;
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public boolean isBuy() {
		return buy;
	}

	public void setBuy(boolean buy) {
		this.buy = buy;
	}

}
