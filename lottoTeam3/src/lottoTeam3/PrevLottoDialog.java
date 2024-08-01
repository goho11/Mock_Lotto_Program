package lottoTeam3;

import java.awt.Window;
import java.util.List;

import javax.swing.JDialog;

public class PrevLottoDialog extends JDialog {

	public PrevLottoDialog(Window window, List<LottoRecord> list) {

	}

	public static void showDialog(Window window, List<LottoRecord> list) {
		PrevLottoDialog prevLottoDialog = new PrevLottoDialog(window, list);
		prevLottoDialog.setVisible(true);
	}
}
