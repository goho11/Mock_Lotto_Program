package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JLabel[][] circles = new JLabel[5][6];
	private JButton[] btnAmend = new JButton[5];
	private JButton[] btnDelete = new JButton[5];

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pnlCenter = new JPanel(null);
		JPanel pnlNorth = new JPanel();
		JPanel pnlSouth = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(700, 300));
		pnlCenter.setBackground(Color.WHITE);
//		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlNorth.setPreferredSize(new Dimension(0, 100));
		pnlNorth.setBackground(Color.WHITE);
//		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlSouth.setPreferredSize(new Dimension(0, 100));
		pnlSouth.setBackground(Color.WHITE);
//		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i = 0; i < circles.length; i++) {
			for (int j = 0; j < circles[i].length; j++) {
				circles[i][j] = new JLabel(LottoCircle.GRAY.getImageIcon());
				circles[i][j].setBounds(j * 60 + 100, i * 60, 60, 60);
				pnlCenter.add(circles[i][j]);
			}
		}
		for (int i = 0; i < btnAmend.length; i++) {
			btnAmend[i] = new JButton("수정");
			btnAmend[i].setBounds(500, i * 60 + 10, 60, 30);
			pnlCenter.add(btnAmend[i]);
		}
		for (int i = 0; i < btnDelete.length; i++) {
			btnDelete[i] = new JButton("삭제");
			btnDelete[i].setBounds(580, i * 60 + 10, 60, 30);
			pnlCenter.add(btnDelete[i]);
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
