package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JLabel[] circles = new JLabel[30];

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pnlCenter = new JPanel(null);
		JPanel pnlNorth = new JPanel();
		JPanel pnlSouth = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(700, 300));
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlNorth.setPreferredSize(new Dimension(0, 100));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlSouth.setPreferredSize(new Dimension(0, 100));
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new JLabel();
			circles[i].setIcon(LottoCircle.GRAY.getImageIcon());
			circles[i].setBounds((i % 6) * 60+10, i / 6 * 60+5, 50, 50);
			pnlCenter.add(circles[i]);
		}
		add(pnlCenter);
		add(pnlNorth, "North");
		add(pnlSouth, "South");
		pack();
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
