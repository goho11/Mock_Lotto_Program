package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// modal 설정 체크 및 종료 시 console 종료를 위한 임시 프레임
class tempFrame extends JFrame {
	private LottoData[] lottoDatas;

	public tempFrame() {
		super("임시 창");
		JPanel pnl = new JPanel();
		JButton callResult = new JButton("결과창 호출");

		add(pnl);
		pnl.add(callResult);

		callResultDialog(callResult);

		setSize(550, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lottoDatas = ResultDialog.testLotto();
	}

	private void callResultDialog(JButton callResult) {
		callResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultDialog resultD = new ResultDialog(lottoDatas, tempFrame.this);
				resultD.setVisible(true);
			}
		});
	}
}

public class ResultDialog extends JDialog {
	private int resultMoney;
	private FontHolder fontHolder = new FontHolder();
	private JLabel[][] lblNums = new JLabel[5][6];
	private JLabel[][] lblCircles = new JLabel[5][6];
	private static int bonus;

	private String[] resultString = new String[5];
	private String[] autoString = new String[5];
	private Set<Integer> resultTreeSet = RandomResult();
	private LottoData[] lottoDatas;
	private int[] lottoArr = new int[6];

	// 테스트용 필드
	private Set<Integer> testSet;
	private static JFrame tempFrame = new tempFrame();

	public ResultDialog(LottoData[] lottoData, JFrame mainFrame) {

		// 배포용으로 만들 경우
//		this.lottoDatas = lottoData;

		// equalsNum 메서드 수정
//		if (resultTreeSet.contains(lottoArr[i])) {

		// showLottoResultNum 메서드 수정
//		Integer[] resultArray = resultTreeSet.toArray(new Integer[6]);
		
		// 아이콘 라벨 설정 수정
//		if (resultTreeSet.contains(lottoArr[j])) {

		
		// 테스트용 로또 데이터 설정
		this.lottoDatas = testLotto();
		testSet = new TreeSet<>();
		testSet.add(1);
		testSet.add(5);
		testSet.add(6);
		testSet.add(11);
		testSet.add(22);
		testSet.add(33);

		// 다이얼로그 세팅 (FlowLayout.CENTER)
		resultDialogSetting();
		setLocationRelativeTo(mainFrame);

		// 당첨 회차 라벨
		showRound();

		// 이번 회차 랜덤으로 선정된 당첨 번호 출력
		showLottoResultNum();

		// 당첨 금액
		showWinMoney();

		// 당첨 결과 패널
		JPanel resultPanel = setResultPanel();
		add(resultPanel);
	}

	private void resultDialogSetting() {
		// 창 제일 위에 이름 어떻게 하지
		setTitle("로또 결과창");

		// 당첨되지 않을 경우 "꽝"이 출력되도록
		for (int i = 0; i < resultString.length; i++) {
			resultString[i] = "꽝";
		}

		// 요소들 끼리 간격 설정 (중앙정렬, hgap, vgap)
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		getContentPane().setBackground(Color.WHITE);
		setModal(true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 500);
	}

	private void showRound() {
		JLabel roundNow = new JLabel("1000회");
		roundNow.setPreferredSize(new Dimension(100, 30));
		roundNow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setColorCenterFont(roundNow, Color.BLACK, JLabel.CENTER, fontHolder.getDeriveFont(Font.PLAIN, 20));
		add(roundNow);
	}

	private void showLottoResultNum() {
		// 당첨 번호 아이콘 6개 + 1
		JPanel showWinNumPnl = new JPanel();
		showWinNumPnl.setPreferredSize(new Dimension(490, 70));
		showWinNumPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		showWinNumPnl.setLayout(null);
		showWinNumPnl.setBackground(Color.WHITE);
		add(showWinNumPnl);

		// 배포용
//		Integer[] resultArray = resultTreeSet.toArray(new Integer[6]);

		// 테스트용
		Integer[] resultArray = testSet.toArray(new Integer[6]);

		for (int i = 0; i < resultArray.length; i++) {
			JLabel lblResultNum = new JLabel("" + resultArray[i]);
			setColorCenterFont(lblResultNum, Color.WHITE, JLabel.CENTER, fontHolder.getDeriveFont(Font.PLAIN, 17));
			lblResultNum.setBounds(i * 60 + 10, 6 - 3, 60, 60);
			showWinNumPnl.add(lblResultNum);

			JLabel lblResultCircle = new JLabel();
			lblResultCircle = numToColor(resultArray[i]);
			lblResultCircle.setBounds(i * 60 + 10, 6, 60, 60);
			showWinNumPnl.add(lblResultCircle);
		}
		JLabel lblPlus = new JLabel(" + ");
		setColorCenterFont(lblPlus, Color.BLACK, JLabel.CENTER, fontHolder.getDeriveFont(Font.PLAIN, 25));
		lblPlus.setBounds(6 * 60 + 20, 20, 30, 30);
		showWinNumPnl.add(lblPlus);

		JLabel lblResultNum = new JLabel("" + bonus);
		setColorCenterFont(lblResultNum, Color.WHITE, JLabel.CENTER, fontHolder.getDeriveFont(Font.PLAIN, 17));
		lblResultNum.setBounds(7 * 60, 6 - 3, 60, 60);
		showWinNumPnl.add(lblResultNum);

		JLabel lblResultCircle = new JLabel();
		lblResultCircle = numToColor(bonus);
		lblResultCircle.setBounds(7 * 60, 6, 60, 60);
		showWinNumPnl.add(lblResultCircle);
	}

	private void showWinMoney() {
		JLabel winMoney = new JLabel();

		// 당첨금
		resultMoney = calculateMoney();

		winMoney.setText("당첨금액: " + resultMoney + "원");
		winMoney.setPreferredSize(new Dimension(150, 30));
		winMoney.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		winMoney.setHorizontalAlignment(JLabel.CENTER);
		winMoney.setVerticalAlignment(JLabel.CENTER);
		add(winMoney);
	}

	private JPanel setResultPanel() {
		JPanel pnlCenter = new JPanel(null);
		pnlCenter.setPreferredSize(new Dimension(540, 300));
		pnlCenter.setBackground(Color.WHITE);

		JLabel[] lblCode = new JLabel[5];
		for (int i = 0; i < lblCode.length; i++) {
			if (lottoDatas[i] != null) {
				char c = (char) ('A' + i);

				autoString[i] = " (반자동) ";
				lblCode[i] = new JLabel(String.valueOf(c) + autoString[i]);
				lblCode[i].setBounds(20, i * 60 - 3, 120, 60);
				lblCode[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
				pnlCenter.add(lblCode[i]);
			}
		}

		// 번호 라벨
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (lottoDatas[i] != null) {
					// 로또 데이터에서 배열 추출
					lottoArr = lottoDatas[i].getNums();

					lblNums[i][j] = new JLabel("");
					lblNums[i][j].setBounds(j * 60 + 110, i * 60 - 3, 60, 60);
					setColorCenterFont(lblNums[i][j], Color.WHITE, JLabel.CENTER,
							fontHolder.getDeriveFont(Font.PLAIN, 17));
					lblNums[i][j].setText("" + lottoArr[j]);
					pnlCenter.add(lblNums[i][j]);
				}
			}
		}

		// 아이콘 라벨
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (lottoDatas[i] != null) {
					// 로또 데이터에서 배열 추출
					lottoArr = lottoDatas[i].getNums();
					
					// 배포용
//					if (resultTreeSet.contains(lottoArr[j])) {
					
					// 테스트용
					if (testSet.contains(lottoArr[j])) {
						
						lblCircles[i][j] = numToColor(lottoArr[j]);
						lblCircles[i][j].setBounds(j * 60 + 110, i * 60, 60, 60);
						pnlCenter.add(lblCircles[i][j]);
					} else {
						lblCircles[i][j] = numToBlack(lottoArr[j]);
						lblCircles[i][j].setBounds(j * 60 + 110, i * 60, 60, 60);
						pnlCenter.add(lblCircles[i][j]);
					}
				}
			}
		}
		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblCode.length; i++) {
			if (lottoDatas[i] != null) {
				lblResult[i] = new JLabel(resultString[i]);
				lblResult[i].setBounds(480, i * 60 - 3, 60, 60);
				lblResult[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
				pnlCenter.add(lblResult[i]);
			}
		}
		return pnlCenter;
	}

	private void setColorCenterFont(JLabel lbl, Color color, int alignment, Font font) {
		lbl.setForeground(color);
		lbl.setHorizontalAlignment(alignment);
		lbl.setVerticalAlignment(alignment);
		lbl.setFont(font);
	}

	private int equalsNum(int[] lottoArr) {
		int count = 0;
		for (int i = 0; i < lottoArr.length; i++) {
			// 배포용
//			if (resultTreeSet.contains(lottoArr[i])) {

			// 테스트 세팅
			if (testSet.contains(lottoArr[i])) {
				count++;
			}
		}
		return count;
	}

	private int calculateMoney() {
		int money = 0;
		for (int i = 0; i < 5; i++) {
			if (lottoDatas[i] != null) {

				lottoArr = lottoDatas[i].getNums();
				int count = equalsNum(lottoArr);

				// 1등, 6개 번호 일치
				if (count == 6) {
					money += 100_000_000;
					resultString[i] = "1등";
				} else if (count == 5) {
					// 3등, 5개 번호 일치
					money += 3_000_000;
					resultString[i] = "3등";

					// 2등, 5개 번호 일치 + 보너스 볼과 번호 일치
					for (int j = 0; j < lottoArr.length; j++) {
						if (lottoArr[j] == bonus) {
							money += 17_000_000;
							resultString[i] = "2등";
						}
					}
					// 4등, 4개 번호 일치
				} else if (count == 4) {
					money += 50_000;
					resultString[i] = "4등";
					// 5등, 3개 번호 일치
				} else if (count == 3) {
					money += 5_000;
					resultString[i] = "5등";
				}
			} else if (lottoDatas[i] == null) {

			}
		}
		return money;
	}

	// yellow blue red gray green 순서
	public static JLabel numToColor(int n) {
		JLabel lbl = new JLabel();
		if (n <= 10) {
			lbl.setIcon(LottoCircle.YELLOW.getImageIcon());
		} else if (10 < n && n <= 20) {
			lbl.setIcon(LottoCircle.BLUE.getImageIcon());
		} else if (20 < n && n <= 30) {
			lbl.setIcon(LottoCircle.RED.getImageIcon());
		} else if (30 < n && n <= 40) {
			lbl.setIcon(LottoCircle.GRAY.getImageIcon());
		} else if (40 < n && n <= 50) {
			lbl.setIcon(LottoCircle.GREEN.getImageIcon());
		}
		return lbl;
	}
	
	public static JLabel numToBlack(int n) {
		JLabel lbl = new JLabel();
		lbl.setIcon(LottoCircle.BLACK.getImageIcon());
		return lbl;
	}

	public static Set<Integer> RandomResult() {
		Random random = new Random();
		Set<Integer> set = new TreeSet<>();
		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}
		do {
			bonus = random.nextInt(45) + 1;
		} while (set.contains(bonus));
//		System.out.println(set + " + " + bonus);

		return set;
	}

	// lottoDatas를 받아서 결과 다이얼로그를 보여주는 메소드
	public static void showDialog(LottoData[] lottoData, JFrame mainFrame) {
		ResultDialog resultDialog = new ResultDialog(lottoData, mainFrame);
		resultDialog.setVisible(true);
	}

	// boolean은 numberchoose에서 mainframe으로 올 때 사용하는 거라 result에선 불필요하다.
	public static LottoData[] testLotto() {
		LottoData[] lottoDatas = new LottoData[5];

		int[] arr = new int[] { 1, 5, 6, 10, 20, 30 };
		LottoData testData = new LottoData(arr, true);

		int[] arr2 = new int[] { 2, 12, 22, 32, 42, 43 };
		LottoData testData2 = new LottoData(arr2, true);

		lottoDatas[0] = testData;
		lottoDatas[1] = testData;
		lottoDatas[2] = testData2;

		return lottoDatas;
	}

	public static void main(String[] args) {
		tempFrame.setVisible(true);
		// 30일
	}
}
