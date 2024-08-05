package lottoTeam3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BulkPurchaseDialog extends JDialog {
	boolean buy;

	private BulkPurchaseDialog(JFrame frame) {
		JPanel pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(300, 300));
		add(pnl);

		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static int showDialog(JFrame frame) {
		BulkPurchaseDialog bulkPurchaseDialog = new BulkPurchaseDialog(frame);
		bulkPurchaseDialog.setVisible(true);
		return 0;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton btn = new JButton();
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BulkPurchaseDialog.showDialog(frame);
			}
		});
		frame.add(btn);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(500, 500);
	}
}
