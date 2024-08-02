package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrevLottoDialog extends JDialog {

	private List<LottoRecord> lottoRecordList;
	private JComboBox<String> comboBoxHwe;
	private JComboBox<String> comboBoxBeon;

	public PrevLottoDialog(Window window, List<LottoRecord> lottoRecordList) {
		this.lottoRecordList = lottoRecordList;
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.white);
		pnlNorth.setPreferredSize(new Dimension(0, 40));

		JLabel lblHwe = new JLabel("이전 회차 선택");
//		lblHwe.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblHwe.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnlNorth.add(lblHwe);

		comboBoxHwe = new JComboBox<>();
		for (int i = 0; i < lottoRecordList.size(); i++) {
			comboBoxHwe.addItem((i + 1000) + "회");
		}
		comboBoxHwe.setBackground(Color.WHITE);
		comboBoxHwe.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnlNorth.add(comboBoxHwe);

		JLabel lblBeon = new JLabel("구매 번호 선택");
		lblBeon.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnlNorth.add(lblBeon);

		comboBoxBeon = new JComboBox<>();
		for (int i = 0; i < lottoRecordList.get(0).getPuchaseNum(); i++) {
			comboBoxBeon.addItem(i + "번");
		}
		comboBoxBeon.setBackground(Color.WHITE);
		comboBoxBeon.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnlNorth.add(comboBoxBeon);

		JPanel pnlCenter = new JPanel(null);
		pnlCenter.setPreferredSize(new Dimension(500, 500));
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(pnlNorth, "North");

		add(pnlCenter);

		setTitle("이전 회차 확인");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocation(window.getX() + window.getWidth(), window.getY());
		setResizable(false);
		setModal(true);

	}

	public static void showDialog(Window window, List<LottoRecord> lottoRecordList) {
		PrevLottoDialog prevLottoDialog = new PrevLottoDialog(window, lottoRecordList);
		prevLottoDialog.setVisible(true);
	}
}
