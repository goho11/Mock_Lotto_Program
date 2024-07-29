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
import javax.swing.SwingConstants;

class tempFrame extends JFrame {
	public tempFrame() {
		super("임시 창");
		JPanel pnl = new JPanel();
		JButton callResult = new JButton("결과창 호출");

		add(pnl);
		pnl.add(callResult);

		callResultDialog(callResult);

		setSize(550, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void callResultDialog(JButton callResult) {
		callResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultDialog resultD = new ResultDialog();
				resultD.setVisible(true);
			}
		});
	}
}

public class ResultDialog extends JDialog {
	private String resultMoney = String.valueOf(0);
	private FontHolder fontHolder = new FontHolder();
	private JLabel[][] lblNums = new JLabel[5][6];
	private JLabel[][] lblCircles = new JLabel[5][6];
	private LottoData lottoData;

	private Set<Integer> resultTreeSet;
	private int[] lottoArray = testLotto().getNums();
	private static int bonus;

	public static LottoData testLotto() {
		int[] arr = new int[] { 1, 11, 22, 33, 44, 45 };

		LottoData testData = new LottoData(arr, true);

		return testData;
	}

	public ResultDialog() {
//		public ResultDialog(LottoData[] lottoDatas) {
		// 창 제일 위에 이름 어떻게 하지
		setTitle("로또 결과창");
		// 요소들 끼리 간격 설정 (중앙정렬, hgap, vgap)
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		getContentPane().setBackground(Color.WHITE);

		// 당첨 번호
		JLabel winNumber = new JLabel("1000회");
		winNumber.setPreferredSize(new Dimension(100, 30));
		winNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		winNumber.setHorizontalAlignment(SwingConstants.CENTER);
		winNumber.setVerticalAlignment(SwingConstants.CENTER);
		add(winNumber);

		showLottoResult();

		// 당첨 금액
		JLabel winMoney = new JLabel();
		winMoney.setText("당첨금액: " + resultMoney + "원");
		winMoney.setPreferredSize(new Dimension(150, 30));
		winMoney.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		winMoney.setHorizontalAlignment(SwingConstants.CENTER);
		winMoney.setVerticalAlignment(SwingConstants.CENTER);
		add(winMoney);

		// 결과창 추가
		JPanel pnlCenter = initCenter();
		add(pnlCenter);

		setModal(true);
		setSize(550, 500);
	}

	// lottoDatas를 받아서 다이얼로그를 보여주는 메소드
	public static void showDialog(LottoData[] lottoDatas) {
//		ResultDialog resultDialog = new ResultDialog(lottoDatas);
//		resultDialog.setVisible(true);
	}

	private JPanel initCenter() {
		JPanel pnlCenter = new JPanel(null);
		pnlCenter.setPreferredSize(new Dimension(540, 300));
		pnlCenter.setBackground(Color.WHITE);

		JLabel[] lblCode = new JLabel[5];
		for (int i = 0; i < lblCode.length; i++) {
			char c = (char) ('A' + i);
			lblCode[i] = new JLabel(String.valueOf(c) + " (자동) ");
			lblCode[i].setBounds(35, i * 60 - 3, 120, 60);
			lblCode[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
			pnlCenter.add(lblCode[i]);
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				lblNums[i][j] = new JLabel("");
				lblNums[i][j].setBounds(j * 60 + 110, i * 60 - 3, 60, 60);
				lblNums[i][j].setForeground(Color.WHITE);
				lblNums[i][j].setHorizontalAlignment(JLabel.CENTER);
				lblNums[i][j].setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
				lblNums[i][j].setText("" + lottoArray[j]);
				pnlCenter.add(lblNums[i][j]);
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				lblCircles[i][j] = numToColor(lottoArray[j]);
				lblCircles[i][j].setBounds(j * 60 + 110, i * 60, 60, 60);
				pnlCenter.add(lblCircles[i][j]);
			}
		}
		JLabel[] lblResult = new JLabel[5];
		for (int i = 0; i < lblCode.length; i++) {
			lblResult[i] = new JLabel("꽝");
			lblResult[i].setBounds(480, i * 60 - 3, 60, 60);
			lblResult[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
			pnlCenter.add(lblResult[i]);
		}
		return pnlCenter;
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

	public static Set<Integer> RandomResult() {
		Random random = new Random();
		Set<Integer> set = new TreeSet<>();
		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}
		do {
			bonus = random.nextInt(45) + 1;
		} while (set.contains(bonus));
		System.out.println(set + " + " + bonus);

		return set;
	}

	private void showLottoResult() {
		// 당첨 번호 아이콘 6개 + 1
		JPanel showWinNumPnl = new JPanel();
		showWinNumPnl.setPreferredSize(new Dimension(490, 70));
		showWinNumPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		showWinNumPnl.setLayout(null);
		showWinNumPnl.setBackground(Color.WHITE);
		add(showWinNumPnl);

		resultTreeSet = RandomResult();
		Integer[] resultArray = resultTreeSet.toArray(new Integer[6]);

		for (int i = 0; i < resultArray.length; i++) {
			JLabel lblResultNum = new JLabel("" + resultArray[i]);
			lblResultNum.setForeground(Color.WHITE);
			lblResultNum.setHorizontalAlignment(JLabel.CENTER);
			lblResultNum.setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
			lblResultNum.setBounds(i * 60 + 10, 6 - 3, 60, 60);
			showWinNumPnl.add(lblResultNum);

			JLabel lblResultCircle = new JLabel();
			lblResultCircle = numToColor(resultArray[i]);
			lblResultCircle.setBounds(i * 60 + 10, 6, 60, 60);
			showWinNumPnl.add(lblResultCircle);
		}

		JLabel lblResultNum = new JLabel("" + bonus);
		lblResultNum.setForeground(Color.WHITE);
		lblResultNum.setHorizontalAlignment(JLabel.CENTER);
		lblResultNum.setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
		lblResultNum.setBounds(7 * 60, 6 - 3, 60, 60);
		showWinNumPnl.add(lblResultNum);

		JLabel lblResultCircle = new JLabel();
		lblResultCircle = numToColor(bonus);
		lblResultCircle.setBounds(7 * 60, 6, 60, 60);
		showWinNumPnl.add(lblResultCircle);

		JLabel lblPlus = new JLabel(" + ");
		lblPlus.setForeground(Color.BLACK);
		lblPlus.setHorizontalAlignment(JLabel.CENTER);
		lblPlus.setVerticalAlignment(JLabel.CENTER);
		lblPlus.setFont(fontHolder.getDeriveFont(Font.PLAIN, 25));
		lblPlus.setBounds(6 * 60 + 20, 20, 30, 30);
		showWinNumPnl.add(lblPlus);
	}

	public static void main(String[] args) {
		new tempFrame().setVisible(true);
		// 29일 작업본까지 적용
	}
}
