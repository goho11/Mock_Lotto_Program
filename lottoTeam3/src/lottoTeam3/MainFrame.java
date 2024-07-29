package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	private JLabel[][] lblNums = new JLabel[5][6];
	private JLabel[][] lblCircles = new JLabel[5][6];
	private JButton[] btnAmend = new JButton[5];
	private JButton[] btnDelete = new JButton[5];
	private FontHolder fontHolder = new FontHolder();
	private JLabel lblPrice;
	private int price;
	private JButton btnResult;
	private JButton btnReset;
	private JButton btnExit;
	private LottoData[] lottoDatas = new LottoData[6];

	public MainFrame() {
		JPanel pnlNorth = initNorth();
		JPanel pnlCenter = initCenter();
		JPanel pnlSouth = initSouth();

		test();

		add(pnlCenter);
		add(pnlNorth, "North");
		add(pnlSouth, "South");

		pack();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameClose();
			}
		});
	}

	private JPanel initSouth() {
		JPanel pnlSouth = new JPanel(null);
		pnlSouth.setPreferredSize(new Dimension(0, 70));
		pnlSouth.setBackground(Color.WHITE);

		lblPrice = new JLabel("총 가격: " + price + "원");
		lblPrice.setBounds(35, 25, 150, 30);
//		lblPrice.setBorder(BorderFactory.createLineBorder(Color.black));
		lblPrice.setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
		pnlSouth.add(lblPrice);

		btnResult = createMyButton("결과", new Rectangle(375, 25, 50, 30), pnlSouth);
		btnReset = createMyButton("초기화", new Rectangle(440, 25, 70, 30), pnlSouth);
		btnExit = createMyButton("종료", new Rectangle(525, 25, 50, 30), pnlSouth);

		return pnlSouth;
	}

	private void test() {
		price = 5000;
		lblPrice.setText("총 가격: " + price + "원");
		for (int i = 0; i < lottoDatas.length; i++) {
			lottoDatas[i] = new LottoData(new int[] {}, true);
		}

		for (int i = 0; i < lblCircles.length; i++) {
			for (int j = 0; j < lblCircles[i].length; j++) {
				lblCircles[i][j].setIcon(LottoCircle.RED.getImageIcon());
			}
		}

		for (int i = 0; i < lblNums.length; i++) {
			for (int j = 0; j < lblNums[i].length; j++) {
				lblNums[i][j].setText("45");
			}
		}
	}

	private JPanel initCenter() {
		JPanel pnlCenter = new JPanel(null);
		pnlCenter.setPreferredSize(new Dimension(600, 300));
		pnlCenter.setBackground(Color.WHITE);

		JLabel[] lblCode = new JLabel[5];
		for (int i = 0; i < lblCode.length; i++) {
			char c = (char) ('A' + i);
			lblCode[i] = new JLabel(String.valueOf(c));
			lblCode[i].setBounds(35, i * 60 - 3, 60, 60);
//			lblCode[i].setHorizontalAlignment(JLabel.CENTER);
			lblCode[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
			pnlCenter.add(lblCode[i]);
		}

		for (int i = 0; i < lblNums.length; i++) {
			for (int j = 0; j < lblNums[i].length; j++) {
				lblNums[i][j] = new JLabel("");
				lblNums[i][j].setBounds(j * 60 + 70, i * 60 - 3, 60, 60);
//				lblNums[i][j].setBorder(BorderFactory.createLineBorder(Color.RED)); // 테두리 테스트
				lblNums[i][j].setForeground(Color.WHITE);
				lblNums[i][j].setHorizontalAlignment(JLabel.CENTER);
				lblNums[i][j].setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
				pnlCenter.add(lblNums[i][j]);
			}
		}

		for (int i = 0; i < lblCircles.length; i++) {
			for (int j = 0; j < lblCircles[i].length; j++) {
				lblCircles[i][j] = new JLabel(LottoCircle.BLACK.getImageIcon());
				lblCircles[i][j].setBounds(j * 60 + 70, i * 60, 60, 60);
				pnlCenter.add(lblCircles[i][j]);
			}
		}

		for (int i = 0; i < btnAmend.length; i++) {
			btnAmend[i] = createMyButton("수정", new Rectangle(460, i * 60 + 13, 50, 30), pnlCenter);
		}

		for (int i = 0; i < btnDelete.length; i++) {
			btnDelete[i] = createMyButton("삭제", new Rectangle(525, i * 60 + 13, 50, 30), pnlCenter);
		}
		return pnlCenter;
	}

	private JButton createMyButton(String text, Rectangle r, JPanel pnl) {
		JButton btn = new JButton(text);
		btn.setBackground(Color.WHITE);
		btn.setBounds(r);
		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.setFocusable(false);
		btn.setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
		btn.addActionListener(this);
		pnl.add(btn);
		return btn;
	}

	private JPanel initNorth() {
		JPanel pnlNorth = new JPanel();
		pnlNorth.setPreferredSize(new Dimension(0, 90));
		pnlNorth.setBackground(Color.WHITE);

		JLabel lblLotto = new JLabel(new ImageIcon(MainFrame.class.getResource("/resource/lotto.png")));
		pnlNorth.add(lblLotto);

		return pnlNorth;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		for (int i = 0; i < btnAmend.length; i++) {
			if (o.equals(btnAmend[i])) {
				lottoDatas[i] = new LottoData(new int[] {1,3,7,15,35,43}, true);
//						NumberChoose
				return;
			}
		}
		for (int i = 0; i < btnDelete.length; i++) {
			if (o.equals(btnDelete[i])) {
				if (lottoDatas[i] == null)
					return;
				if (!lottoDatas[i].isBuy())
					return;
				lottoDatas[i].setBuy(false);
				for (int j = 0; j < lblNums[i].length; j++) {
					lblNums[i][j].setText("");
				}
				for (int j = 0; j < lblCircles[i].length; j++) {
					lblCircles[i][j].setIcon(LottoCircle.BLACK.getImageIcon());
				}
				price -= 1000;
				lblPrice.setText("총 가격: " + price + "원");

				return;
			}
		}
		if (o.equals(btnResult)) {
			// 결과 다이알로그 출력 lottoDatas 넘겨줌
//			 ResultDialog.showDialog(lottoDatas);
		} else if (o.equals(btnReset)) {
			for (int i = 0; i < lblNums.length; i++) {
				for (int j = 0; j < lblNums[i].length; j++) {
					lblNums[i][j].setText("");
				}
			}
			for (int i = 0; i < lblCircles.length; i++) {
				for (int j = 0; j < lblCircles[i].length; j++) {
					lblCircles[i][j].setIcon(LottoCircle.BLACK.getImageIcon());
				}
			}
			Arrays.fill(lottoDatas, null);
			price = 0;
			lblPrice.setText("총 가격: " + price + "원");
		} else if (o.equals(btnExit)) {
			frameClose();
		}
	}

	private void frameClose() {
		int input = JOptionPane.showOptionDialog(MainFrame.this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, null, null);
		if (input == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
