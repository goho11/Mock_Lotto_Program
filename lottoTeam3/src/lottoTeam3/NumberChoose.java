package lottoTeam3;

import java.awt.BorderLayout;
<<<<<<< HEAD
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
>>>>>>> branch 'gugu2' of https://github.com/LeeKangHo1/lottoTeam3.git

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class NumberChoose extends JDialog {
	public NumberChoose() {
		setTitle("로또 번호 선택");

//		setLayout(new BorderLayout());
		setSize(421, 280);

		JPanel pnl = new JPanel(new BorderLayout());
		// 패널A : 1~45 숫자범위
		JPanel pnlA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 패너류 : 기능 안내 버튼
		JPanel pnlB = new JPanel();
		JButton[] btn = new JButton[45];
		JButton check = new JButton("확인");
		JButton reset = new JButton("초기화");
		JButton auto = new JButton("자동");

		// 배열은 같다고 하면 안됨. 마지막 번호를 추가하고 싶으면 -1
		Font font = new Font("맑은 고딕", Font.BOLD, 18);
		for (int i = 0; i < btn.length; i++) {
			pnlA.add(btn[i] = new JButton(String.valueOf(i + 1)));
			btn[i].setPreferredSize(new Dimension(35, 35));
			btn[i].setFont(font);
			btn[i].setMargin(new Insets(0, 0, 0, 0));
		}

		pnlB.add(check);
		pnlB.add(reset);
		pnlB.add(auto);

		pnl.add(pnlA);
		pnl.add(pnlB, "South");
		add(pnl);
	}

	public static void main(String[] args) {
		new NumberChoose().setVisible(true);
	}
}
