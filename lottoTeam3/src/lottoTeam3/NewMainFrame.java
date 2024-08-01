package lottoTeam3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewMainFrame extends JFrame {
	private List<LottoRecord> lottoRecordList;
	private LottoData[] lottoDatas;
	private LottoData[] lottoDataNull = new LottoData[5];
	private LottoRecord curLottoRecord = new LottoRecord();

	public NewMainFrame() {
		super("로또 프로그램");

		lottoDatas = testLotto();

		lottoRecordList = new ArrayList<>();
		getContentPane().setBackground(Color.WHITE);

		JLabel lblLotto = new JLabel(new ImageIcon(NewMainFrame.class.getResource("/resource/lotto.png")));
		add(lblLotto, "North");

		JLabel countTicketsLbl = new JLabel("구매 장 수 X 장");
		countTicketsLbl.setFont(new Font(countTicketsLbl.getFont().getName(), Font.PLAIN, 24));
		countTicketsLbl.setHorizontalAlignment(JLabel.CENTER);
		add(countTicketsLbl);
		JButton buyBtn = new JButton("구매");
		buyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LottoData[] ld = PurchaseDialog.showDialog(NewMainFrame.this);
				if (ld != null)
					curLottoRecord.addBuyLotto(ld);
				System.out.println(curLottoRecord);
			}
		});
		JButton resultBtn = new JButton("결과");
		resultBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultDialog.showDialog(curLottoRecord, NewMainFrame.this);
			}
		});
		JButton endBtn = new JButton("종료");
		endBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameClose();
			}
		});

		JPanel southPnl = new JPanel();
		southPnl.setBackground(Color.WHITE);

		southPnl.add(buyBtn, "South");
		southPnl.add(resultBtn, "South");
		southPnl.add(endBtn, "South");
		add(southPnl, "South");

		// x를 눌렀을 때 종료 확인 다이알로그가 생성되게 윈도우리스너 추가
		addWindowListener(new WindowAdapter() { // 윈도우 어댑터를 사용하여 필요한 메서드만 구현
			@Override
			public void windowClosing(WindowEvent e) { // x를 누를 때
				frameClose(); // 종료 확인 다이알로그를 처리하는 메서드
			}
		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		// 임시로 가운데에서 창 나오도록, setSize와 Pack(); 이후에 작성해야 한다.
		setLocation((1920 - getWidth()) / 2, (1080 - getHeight()) / 2);
	}

	// 종료확인 다이얼로그 표시
	private void frameClose() {
		int input = JOptionPane.showOptionDialog(NewMainFrame.this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, null, null); // 종료 확인 다이알로그 출력 및 값 대입
		if (input == JOptionPane.YES_OPTION) { // 종료 확인을 눌렀을 때
			dispose(); // 창 사라지게
		}
	}

	public LottoData[] testLotto() {
		LottoData[] lottoDatas = new LottoData[5];

		int[] arr = new int[] { 1, 11, 21, 31, 41, 33 };
		LottoData testData = new LottoData(arr, Mode.AUTO);

		int[] arr1 = new int[] { 1, 11, 21, 31, 41, 7 };
		LottoData testData1 = new LottoData(arr1, Mode.SEMI);

		int[] arr2 = new int[] { 1, 11, 21, 31, 41, 45 };
		LottoData testData2 = new LottoData(arr2, Mode.MANUAL);

		int[] arr3 = new int[] { 1, 11, 21, 7, 41, 27 };
		LottoData testData3 = new LottoData(arr3, Mode.MANUAL);

		int[] arr4 = new int[] { 2, 7, 21, 31, 38, 45 };
		LottoData testData4 = new LottoData(arr4, Mode.MANUAL);

		lottoDatas[0] = testData;
		lottoDatas[1] = testData1;
		lottoDatas[2] = testData2;
		lottoDatas[3] = testData3;
		lottoDatas[4] = testData4;

		return lottoDatas;
	}

	public static void main(String[] args) {
		new NewMainFrame().setVisible(true);
	}
}
