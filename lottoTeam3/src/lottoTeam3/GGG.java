package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GGG extends JFrame implements ActionListener {
	public GGG() {
		super("가상 로또 시뮬레이션");

		
		JPanel pnl = new JPanel(new BorderLayout());
		JPanel pnlPic = new JPanel();
		JPanel pnlBtn = new JPanel(null);

		pnlPic.setBackground(Color.WHITE);
		pnlBtn.setBackground(Color.WHITE);
		JLabel lblLotto = new JLabel(new ImageIcon(GGG.class.getResource("/resource/lotto.png")));
		pnlPic.add(lblLotto);

		JButton buy = new JButton("구매(n장)");
		buy.setBounds(20, 30, 200, 80);
		buy.setBackground(Color.WHITE);
		pnlBtn.add(buy);

		JButton buyCheck = new JButton("현재 구매 확인");
		buyCheck.setBounds(20, 110, 200, 80);
		buyCheck.setBackground(Color.WHITE);
		pnlBtn.add(buyCheck);

		JButton draw = new JButton("n회차 추첨");
		draw.setBounds(220, 30, 200, 80);
		draw.setBackground(Color.WHITE);
		pnlBtn.add(draw);

		JButton buyBefore = new JButton("이전 구매 확인");
		buyBefore.setBounds(220, 110, 200, 80);
		buyBefore.setBackground(Color.WHITE);
		pnlBtn.add(buyBefore);

		JButton end = new JButton("종료");
		end.setBounds(120, 210, 200, 50);
		end.setBackground(Color.WHITE);
		pnlBtn.add(end);

		pnl.add(pnlPic, "North");
		pnl.add(pnlBtn);
		add(pnl);
		setSize(450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new GGG().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
