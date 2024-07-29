package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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


	public ResultDialog() {
//		public ResultDialog(LottoData[] lottoDatas) {
		// 창 제일 위에 이름 어떻게 하지
		setTitle("로또 결과창");
		// 요소들 끼리 간격 설정 (중앙정렬, hgap, vgap)
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		
		// 당첨 번호
		JLabel winNumber = new JLabel("당첨번호");
		winNumber.setPreferredSize(new Dimension(100, 30));
		winNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		winNumber.setHorizontalAlignment(SwingConstants.CENTER);
		winNumber.setVerticalAlignment(SwingConstants.CENTER);
		add(winNumber);
		
		// 당첨 번호 아이콘 6개 + 1
		JPanel showWinNumPnl = new JPanel();
		showWinNumPnl.setPreferredSize(new Dimension(400, 50));
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
		
		for (int i = 0; i < 6; i++) {
			setShowMyNum("A", "당첨");
		}
		
		setModal(true);
		setSize(500, 500);
	}
	
	// 내가 정한 번호 보여주는 패널 (복권 갯수에 따라 다르니까 5개로 분리해야함)
	private void setShowMyNum(String s, String result){
		JPanel pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(500, 60));
		JLabel lbl = new JLabel();
		lbl.setPreferredSize(new Dimension(50, 60));
		lbl.setText(s + " " + result);
		pnl.add(lbl);
		for (int i = 10; i < 61; i += 10) {
			lblNum = makeNumCircle(i);
			pnl.add(lblNum);
		}
		ResultDialog.this.add(pnl);
		
	}
	
	//	0~9 : 노란색
	//	10~19: 파란색
	//	20~29: 빨간색
	//	30~39: 검은색
	//	40~45: 초록색
	// 임시로 버튼으로 채워둔 상태
	private JLabel makeNumCircle(int selectedNum) {
		JLabel lbl = new JLabel();
		lbl.setText(String.valueOf(selectedNum));
		lbl.setPreferredSize(new Dimension(60, 60));
		return lbl;
	}
	
	public static void showDialog(LottoData[] lottoDatas) {
//		ResultDialog resultDialog = new ResultDialog(lottoDatas);
//		resultDialog.setVisible(true);
	}
	

	public static void main(String[] args) {
		new tempFrame().setVisible(true);
	}
}