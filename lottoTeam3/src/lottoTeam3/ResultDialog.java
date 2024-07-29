package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		setSize(700, 500);
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
	private JLabel lblNum;
	private FontHolder fontHolder = new FontHolder();
	private JLabel[][] lblNums = new JLabel[5][6];
	private JLabel[][] lblCircles = new JLabel[5][6];


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
		
		// 당첨 번호 아이콘 6개 + 1
		JPanel showWinNumPnl = new JPanel();
		showWinNumPnl.setPreferredSize(new Dimension(500, 50));
		showWinNumPnl.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		add(showWinNumPnl);
		
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
		for (int i = 0; i < lblNums.length; i++) {
			for (int j = 0; j < lblNums[i].length; j++) {
				lblNums[i][j] = new JLabel("");
				lblNums[i][j].setBounds(j * 60 + 110, i * 60 - 3, 60, 60);
				lblNums[i][j].setForeground(Color.WHITE);
				lblNums[i][j].setHorizontalAlignment(JLabel.CENTER);
				lblNums[i][j].setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
				lblNums[i][j].setText("45");
				pnlCenter.add(lblNums[i][j]);
			}
		}
		//	0~9 : 노란색
		//	10~19: 파란색
		//	20~29: 빨간색
		//	30~39: 검은색
		//	40~45: 초록색
		for (int i = 0; i < lblCircles.length; i++) {
			for (int j = 0; j < lblCircles[i].length; j++) {
				lblCircles[i][j] = new JLabel(LottoCircle.YELLOW.getImageIcon());
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
	

	public static void main(String[] args) {
		new tempFrame().setVisible(true);
	}
}
