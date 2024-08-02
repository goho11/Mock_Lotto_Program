package lottoTeam3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewMainFrame extends JFrame implements ActionListener {
	private List<LottoRecord> lottoRecordList;
	private LottoRecord curLottoRecord;
	private JButton buyBtn;
	private JButton resultBtn;
	private JButton endBtn;
	private JButton btnPrev;
	private JButton btnCur;
	private JLabel curGameLbl;

	public NewMainFrame() {
		super("로또 프로그램");

		readLottoRecords();
		curLottoRecord = new LottoRecord(lottoRecordList.size() + 1);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblLotto = new JLabel(new ImageIcon(NewMainFrame.class.getResource("/resource/lotto.png")));
		add(lblLotto, "North");

		curGameLbl = new JLabel((lottoRecordList.size() + 1) + "회");
		curGameLbl.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 25));
		curGameLbl.setHorizontalAlignment(JLabel.CENTER);
		add(curGameLbl, "West");

		
		JLabel countTicketsLbl = new JLabel("구매 장 수 X 장");
		countTicketsLbl.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 25));
		countTicketsLbl.setHorizontalAlignment(JLabel.CENTER);
		add(countTicketsLbl);

		JPanel southPnl = new JPanel();
		southPnl.setBackground(Color.WHITE);

		btnCur = createMyButton("현재 구매 확인", southPnl);
		btnPrev = createMyButton("이전 회차 확인", southPnl);
		buyBtn = createMyButton("구매", southPnl);
		resultBtn = createMyButton("추첨", southPnl);
		endBtn = createMyButton("종료", southPnl);

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
		setResizable(false);
		// 임시로 가운데에서 창 나오도록, setSize와 Pack(); 이후에 작성해야 한다.
		setLocation((1920 - getWidth()) / 2, (1080 - getHeight()) / 2);
	}

	private JButton createMyButton(String text, JPanel pnl) {
		JButton btn = new JButton(text);
		btn.setMargin(new Insets(0, 2, 0, 2));
		btn.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		btn.setBackground(Color.WHITE);
		btn.setFocusable(false);
		btn.addActionListener(this);
		pnl.add(btn);
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnPrev)) {
			PrevLottoDialog.showDialog(this, lottoRecordList);
		} else if (o.equals(btnCur)) {

		} else if (o.equals(buyBtn)) {
			LottoData[] ld = PurchaseDialog.showDialog(lottoRecordList, curLottoRecord, this);
			if (ld != null)
				curLottoRecord.addBuyLotto(ld);
		} else if (o.equals(resultBtn)) {
			ResultDialog.showDialog(curLottoRecord, this);
			lottoRecordList.add(curLottoRecord);
			curGameLbl.setText((lottoRecordList.size() + 1) + "회");
			curLottoRecord = new LottoRecord(lottoRecordList.size() + 1);
		} else if (o.equals(endBtn)) {
			frameClose();
		}
	}

	// 종료확인 다이얼로그 표시
	private void frameClose() {
		int input = JOptionPane.showOptionDialog(NewMainFrame.this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, null, null); // 종료 확인 다이알로그 출력 및 값 대입
		if (input == JOptionPane.YES_OPTION) { // 종료 확인을 눌렀을 때
			dispose(); // 창 사라지게
		}
	}

	public void readLottoRecords() {
		File file = new File(".//lotto.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		lottoRecordList = LottoRecordIO.readLottoRecord(file);
	}

	public static void main(String[] args) {
		new NewMainFrame().setVisible(true);
	}

	public LottoRecord getCurLottoRecord() {
		return curLottoRecord;
	}
}
