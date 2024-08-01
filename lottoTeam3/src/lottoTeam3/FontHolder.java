package lottoTeam3;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

//현재 메이플 폰트
public class FontHolder {
	private static final FontHolder instance = new FontHolder();
	private Font myFont;

	private FontHolder() {
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, FontHolder.class.getResourceAsStream("/resource/Maplestory Bold.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FontHolder getInstance() {
		return instance;
	}

	public Font getDeriveFont(int style, float size) {
		return myFont.deriveFont(style, size);
	}
}
