package lottoTeam3;

import javax.swing.ImageIcon;

public enum LottoCircle {
	GRAY("gray.png"), YELLOW("yellow.png"), BLUE("blue.png"), RED("red.png"), BLACK("black.png"), GREEN("green.png");

	private ImageIcon imageIcon;

	private LottoCircle(String filename) {
		imageIcon = new ImageIcon(LottoCircle.class.getResource("/resource/" + filename));
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}
}
