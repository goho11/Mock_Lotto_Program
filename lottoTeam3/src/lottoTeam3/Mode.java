package lottoTeam3;

// NumberChoose에 활용
// 로또 번호 선택 기능  : 자동, 반자동, 수동
public enum Mode {
	AUTO("자동"), SEMI("반자동"), MANUAL("수동");

	private String korean;

	private Mode(String korean) {
		this.korean = korean;
	}

	public String getKorean() {
		return korean;
	}
}
