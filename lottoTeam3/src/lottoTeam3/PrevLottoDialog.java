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
	private JComboBox<String> comboBox;

	public PrevLottoDialog(Window window, List<LottoRecord> lottoRecordList) {
		this.lottoRecordList = lottoRecordList;
		JPanel pnl = new JPanel(null);
		pnl.setPreferredSize(new Dimension(500, 500));
		pnl.setBackground(Color.white);
		JLabel lblhwe = new JLabel("이전 회차 선택");
		lblhwe.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblhwe.setBounds(10, 10, 120, 30);
		lblhwe.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnl.add(lblhwe);
		comboBox = new JComboBox<>();
		for (int i = 0; i < lottoRecordList.size(); i++) {
			comboBox.addItem((i + 1000) + "회");
		}
		comboBox.setBounds(140, 10, 100, 30);
		comboBox.setBackground(Color.white);
		comboBox.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
		pnl.add(comboBox);

		add(pnl);
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
