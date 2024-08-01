package lottoTeam3;

import java.awt.Window;
import java.util.List;

import javax.swing.JDialog;

public class PrevLottoDialog extends JDialog {

	public PrevLottoDialog(Window window, List<LottoRecord> list) {
		setTitle("이전 회차 확인");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocation(window.getX() + window.getWidth(), window.getY());
		setResizable(false);
		setModal(true);
	}

	public static void showDialog(Window window, List<LottoRecord> list) {
		PrevLottoDialog prevLottoDialog = new PrevLottoDialog(window, list);
		prevLottoDialog.setVisible(true);
	}
}
