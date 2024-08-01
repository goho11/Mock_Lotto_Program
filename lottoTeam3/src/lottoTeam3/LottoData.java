package lottoTeam3;

public class LottoData {
	private int[] nums = new int[6];
	private Mode mode;

	public LottoData(int[] nums, Mode mode) {
		super();
		this.nums = nums;
		this.mode = mode;
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i : nums) {
			stringBuilder.append(i).append(' ');
		}
		stringBuilder.append(mode);
		return stringBuilder.toString();
	}
}
