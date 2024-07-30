package lottoTeam3;

public class LottoData {
	private int[] nums = new int[6];
	private boolean buy;
	private Mode mode;

	public LottoData(int[] nums, boolean buy, Mode mode) {
		super();
		this.nums = nums;
		this.buy = buy;
		this.mode = mode;
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

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
