package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class NumberChoose extends JDialog {
	public NumberChoose() {

		setLayout(new BorderLayout());
		setSize(500, 500);

		//String[] btnText = { "1", "2", "3" };

		JPanel pnl = new JPanel(new BorderLayout());
		JButton[] btn = new JButton[45];
		JButton check = new JButton("확인");
		JButton reset = new JButton("초기화");
		JButton auto = new JButton("자동");

		// BorderLayout
		add(pnl, "Center");
		pnl.setLayout(new GridLayout(6, 9));
		// 배열은 같다고 하면 안됨. 마지막 번호를 추가하고 싶으면 -1
		for (int i = 0; i < btn.length; i++) {
			pnl.add(btn[i] = new JButton(String.valueOf(i+1)));
		}
	}

	public static void main(String[] args) {
		new NumberChoose().setVisible(true);
	}
}
