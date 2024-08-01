package lottoTeam3;

import java.awt.Window;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;

public class PrevLottoDialog extends JDialog {

	private List<LottoRecord> lottoRecordList;
	private JComboBox<String> comboBox;

	public PrevLottoDialog(Window window, List<LottoRecord> lottoRecordList) {
		this.lottoRecordList = lottoRecordList;
		comboBox = new JComboBox<>();
		for (int i = 0; i < lottoRecordList.size(); i++) {
			comboBox.addItem((i + 1) + "회");
		}

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
