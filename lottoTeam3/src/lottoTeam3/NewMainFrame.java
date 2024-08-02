package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
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
	private JButton btnPrev;
	private JButton btnCur;
	private JButton endBtn;

	public NewMainFrame() {
		super("가상 로또 시뮬레이션");

		readLottoRecords();
		curLottoRecord = new LottoRecord(lottoRecordList.size() + 1);

		JPanel pnl = new JPanel(new BorderLayout());
		JPanel pnlPic = new JPanel();
		JPanel pnlBtn = new JPanel(null);

		pnlPic.setBackground(Color.WHITE);
		pnlBtn.setBackground(Color.WHITE);
		pnlBtn.setPreferredSize(new Dimension(450, 255));
		JLabel lblLotto = new JLabel(new ImageIcon(NewMainFrame.class.getResource("/resource/lotto.png")));
		pnlPic.add(lblLotto);

		buyBtn = createMyButton("구매(" + curLottoRecord.getPuchaseNum() + "장)", new Insets(0, 0, 0, 0), pnlBtn, 30,
				new Rectangle(20, 10, 200, 80));
		resultBtn = createMyButton("추첨", new Insets(0, 0, 0, 0), pnlBtn, 30, new Rectangle(20, 90, 200, 80));
		btnCur = createMyButton("현재 구매 확인", new Insets(0, 0, 0, 0), pnlBtn, 34, new Rectangle(220, 10, 200, 80));
		btnPrev = createMyButton("이전 회차 확인", new Insets(0, 0, 0, 0), pnlBtn, 30, new Rectangle(220, 90, 200, 80));
		endBtn = createMyButton("종료", new Insets(0, 0, 0, 0), pnlBtn, 34, new Rectangle(120, 190, 200, 50));

		pnl.add(pnlPic, "North");
		pnl.add(pnlBtn);
		add(pnl);
		pack();
		addWindowListener(new WindowAdapter() { // 윈도우 어댑터를 사용하여 필요한 메서드만 구현
			@Override
			public void windowClosing(WindowEvent e) { // x를 누를 때
				frameClose(); // 종료 확인 다이알로그를 처리하는 메서드
			}
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		pack();
		// 임시로 가운데에서 창 나오도록, setSize와 Pack(); 이후에 작성해야 한다.
		setLocation((1920 - getWidth()) / 2, (1080 - getHeight()) / 2);
	}

	// 버튼 셋팅 메서드
	private JButton createMyButton(String text, Insets insets, JPanel pnl, int fontSize, Rectangle r) {
		JButton btn = new JButton(text);
		btn.setFocusable(false);
		btn.setMargin(insets);
		btn.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 30));
		btn.setBackground(Color.WHITE);
		btn.setBounds(r);
		btn.addActionListener(this);
		pnl.add(btn);
		return btn;
	}

	public void settingBtnWhenSellectAll(boolean b) {
		resultBtn.setEnabled(!b); // 추첨
		btnCur.setEnabled(!b); // 현재 구매 확인
		btnPrev.setEnabled(!b); // 이전 회차 확인
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(buyBtn)) { // 구매
			LottoData[] ld = PurchaseDialog.showDialog(lottoRecordList, curLottoRecord, this);
			// ***화면을 처음 키면 구매만 활성화 적용으로
			// 현재 구매 클릭후 안사면 비활성화됨
			settingBtnWhenSellectAll(true);
			if (ld != null) {
				curLottoRecord.addBuyLotto(ld);
				buyBtn.setText("구매(" + curLottoRecord.getPuchaseNum() + "장)");
				settingBtnWhenSellectAll(false);
			}
		} else if (o.equals(resultBtn)) { // 추첨
			ResultDialog.showDialog(curLottoRecord, this);
//			lottoRecordList.add(curLottoRecord);
			curLottoRecord = new LottoRecord(1);
			buyBtn.setText("구매(" + curLottoRecord.getPuchaseNum() + "장)");
		} else if (o.equals(endBtn)) {
			frameClose();
		}
		// 현재 구매 확인 : 선택한 번호가 적용된 창 출력
		else if (o.equals(btnCur)) {

		} else if (o.equals(btnPrev)) {
			PrevLottoDialog.showDialog(this, lottoRecordList);
		}
	}

	// 종료확인 다이얼로그 표시
	private void frameClose() {
		int input = JOptionPane.showOptionDialog(this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
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