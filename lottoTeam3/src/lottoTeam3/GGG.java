package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GGG extends JFrame implements ActionListener {
	private List<LottoRecord> lottoRecordList;
	private LottoRecord curLottoRecord;
	private LottoData[] lottoDatas;
	private LottoData[] lottoDataNull = new LottoData[5];
	private JButton buyBtn;
	private JButton resultBtn;
	private JButton btnPrev;
	private JButton btnCur;
	private JButton endBtn;

	public GGG() {
		super("가상 로또 시뮬레이션");

		JPanel pnl = new JPanel(new BorderLayout());
		JPanel pnlPic = new JPanel();
		JPanel pnlBtn = new JPanel(null);
		curLottoRecord = new LottoRecord(1);

		pnlPic.setBackground(Color.WHITE);
		pnlBtn.setBackground(Color.WHITE);
		JLabel lblLotto = new JLabel(new ImageIcon(GGG.class.getResource("/resource/lotto.png")));
		pnlPic.add(lblLotto);

		buyBtn = btnSetting("구매(" + curLottoRecord.getPuchaseNum() + "장)", new Insets(0, 0, 0, 0), pnlBtn, 30,
				new Rectangle(20, 30, 200, 80));
		resultBtn = btnSetting("추첨", new Insets(0, 0, 0, 0), pnlBtn, 30, new Rectangle(20, 110, 200, 80));
		btnCur = btnSetting("현재 구매 확인", new Insets(0, 0, 0, 0), pnlBtn, 34, new Rectangle(220, 30, 200, 80));
		btnPrev = btnSetting("이전 회차 확인", new Insets(0, 0, 0, 0), pnlBtn, 30, new Rectangle(220, 110, 200, 80));
		endBtn = btnSetting("종료", new Insets(0, 0, 0, 0), pnlBtn, 34, new Rectangle(120, 210, 200, 50));

		pnl.add(pnlPic, "North");
		pnl.add(pnlBtn);
		add(pnl);
		setSize(450, 420);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 버튼 셋팅 메서드
	private JButton btnSetting(String text, Insets insets, JPanel pnl, int fontSize, Rectangle r) {
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
		if (o.equals(btnCur)) {

		}
	}

	// 종료확인 다이얼로그 표시
	private void frameClose() {
		int input = JOptionPane.showOptionDialog(GGG.this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
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
		new GGG().setVisible(true);
	}

	public LottoRecord getCurLottoRecord() {
		return curLottoRecord;
	}
}