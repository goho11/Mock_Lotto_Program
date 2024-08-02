package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PurchasedLottoDialog extends JDialog {
	private String roundText;
	private int listIndex;

	private LottoRecord lottoRecord;
	private JFrame mainFrame;
	private JComboBox<String> comboBox;
	private JPanel resultPanel;
	private JLabel roundNow;
	private LottoData[] lottoDatas;
	private JLabel[] lblCodes;
	private JLabel[][] lblNums;
	private JLabel[][] lblCircles;
	private JLabel[] lblResults;

	public PurchasedLottoDialog(LottoRecord lottoRecord, JFrame mainFrame) {
		this.lottoRecord = lottoRecord;
		this.mainFrame = mainFrame;

		// 필요한 component 초기화
		initComponents();

		// component의 내용을 설정
		setAndUpdate();

	}

	private void initComponents() {

		// resultDiaglog 세팅
		iniResultDialog();

		// 회차 라벨, 회차 선택 가능한 드랍다운 버튼 세팅
		iniRoundLblAndDropdown();

		// 당첨 결과 패널 생성
		iniResultPanel();
		createCopyBtn();
	}

	private void iniResultDialog() {
		// 창 제일 위에 이름 어떻게 하지
		setTitle("로또 결과창");

		// 요소들 끼리 간격 설정 (중앙정렬, hgap, vgap)
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		getContentPane().setBackground(Color.WHITE);

		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(mainFrame);
		setResizable(false);
	}

	private void iniRoundLblAndDropdown() {
		roundNow = new JLabel();
		roundNow.setPreferredSize(new Dimension(150, 30));
		setColorCenterFont(roundNow, Color.BLACK, JLabel.CENTER, 20);
		add(roundNow);

		comboBox = new JComboBox<>();
		for (int i = 0; i < lottoRecord.getPuchaseNum(); i++) {
			comboBox.addItem(String.valueOf(i + 1) + "번 로또");
		}
		comboBox.setPreferredSize(new Dimension(75, 30));
		add(comboBox);

		// 드랍 다운 버튼 리스너 설정
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listIndex = comboBox.getSelectedIndex();
				setAndUpdate();
			}
		});
	}

	private void iniResultPanel() {
		resultPanel = new JPanel(null);
		resultPanel.setPreferredSize(new Dimension(540, 300));
		resultPanel.setBackground(Color.WHITE);

		// A (반자동) 출력
		lblCodes = new JLabel[5];
		for (int i = 0; i < lblCodes.length; i++) {
			lblCodes[i] = new JLabel();
			lblCodes[i].setBounds(5, i * 60 - 3, 120, 60);
			setColorCenterFont(lblCodes[i], Color.BLACK, JLabel.CENTER, 20);
			resultPanel.add(lblCodes[i]);
		}
		// 번호 라벨,아이콘 라벨
		lblNums = new JLabel[5][6];
		lblCircles = new JLabel[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				// 로또 데이터에서 배열 추출
				lblNums[i][j] = new JLabel();
				lblNums[i][j].setBounds(j * 60 + 110, i * 60 - 3, 60, 60);
				setColorCenterFont(lblNums[i][j], Color.WHITE, JLabel.CENTER, 17);
				resultPanel.add(lblNums[i][j]);

				lblCircles[i][j] = new JLabel();
				lblCircles[i][j].setBounds(j * 60 + 110, i * 60, 60, 60);
				resultPanel.add(lblCircles[i][j]);
			}
		}

		// 결과 패널을 메인프레임에 추가
		add(resultPanel);
	}

	private void createCopyBtn() {
		for (int i = 0; i < 5; i++) {
			JButton copyBtn = new JButton("복사");
			copyBtn.setBounds(480, i * 60 + 10, 40, 30);
			copyBtn.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, 20));
			copyBtn.setMargin(new Insets(0, 0, 0, 0));
			copyBtn.setForeground(Color.BLACK);
			copyBtn.setBackground(Color.WHITE);
			copyBtn.setFocusable(false);
			resultPanel.add(copyBtn);
			
			int countNum = i;
			
			copyBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LottoData.setCopy(lottoDatas[countNum]);
					return;
				}
			});
		}
		
		
	}

	private void setAndUpdate() {
		setResultDialog();

		setRoundLblAndDropdown();

		setResultPanel();
	}

	private void setResultDialog() {
		lottoDatas = lottoRecord.getLottoDatas(listIndex);
		int count = 0;
		for (LottoData lottoData : lottoDatas) {
			if (lottoData == null) {
				break;
			}
			count++;
		}
		setSize(550, 65 + count * 60);
	}

	private void setRoundLblAndDropdown() {
		roundText = String.valueOf("현재 구매한 로또 ");
		roundNow.setText(roundText);
	}

	private void setResultPanel() {
		// A (반자동) 출력
		for (int i = 0; i < lblCodes.length; i++) {
			if (lottoDatas[i] != null) {
				char c = (char) ('A' + i);
				lblCodes[i].setText(String.valueOf(c) + " (" + lottoDatas[i].getMode().getKorean() + ")");
			}
		}
		// 번호 라벨,아이콘 라벨
		for (int i = 0; i < 5; i++) {
			if (lottoDatas[i] == null)
				break;
			for (int j = 0; j < 6; j++) {
				// 로또 데이터에서 배열 추출
				int[] lottoArr = lottoDatas[i].getNums();
				lblNums[i][j].setText("" + lottoArr[j]);
				setToColor(lblCircles[i][j], lottoArr[j]);
			}
		}
	}

	private void setColorCenterFont(JLabel lbl, Color color, int alignment, int fontSize) {
		lbl.setForeground(color);
		lbl.setHorizontalAlignment(alignment);
		lbl.setVerticalAlignment(alignment);
		lbl.setFont(FontHolder.getInstance().getDeriveFont(Font.PLAIN, fontSize));
	}

	// yellow blue red gray green 순서
	public JLabel numToColor(int n) {
		JLabel lbl = new JLabel();
		if (n <= 10) {
			lbl.setIcon(LottoCircle.YELLOW.getImageIcon());
		} else if (10 < n && n <= 20) {
			lbl.setIcon(LottoCircle.BLUE.getImageIcon());
		} else if (20 < n && n <= 30) {
			lbl.setIcon(LottoCircle.RED.getImageIcon());
		} else if (30 < n && n <= 40) {
			lbl.setIcon(LottoCircle.GRAY.getImageIcon());
		} else if (40 < n && n <= 50) {
			lbl.setIcon(LottoCircle.GREEN.getImageIcon());
		}
		return lbl;
	}

	public void setToColor(JLabel lbl, int n) {
		if (n <= 10) {
			lbl.setIcon(LottoCircle.YELLOW.getImageIcon());
		} else if (10 < n && n <= 20) {
			lbl.setIcon(LottoCircle.BLUE.getImageIcon());
		} else if (20 < n && n <= 30) {
			lbl.setIcon(LottoCircle.RED.getImageIcon());
		} else if (30 < n && n <= 40) {
			lbl.setIcon(LottoCircle.GRAY.getImageIcon());
		} else if (40 < n && n <= 50) {
			lbl.setIcon(LottoCircle.GREEN.getImageIcon());
		}
	}

	public JLabel numToBlack() {
		JLabel lbl = new JLabel();
		lbl.setIcon(LottoCircle.BLACK.getImageIcon());
		return lbl;
	}

	// lottoDatas를 받아서 결과 다이얼로그를 보여주는 메소드
	public static void showDialog(LottoRecord lottoRecord, JFrame mainFrame) {
		PurchasedLottoDialog resultDialog = new PurchasedLottoDialog(lottoRecord, mainFrame);
		resultDialog.setVisible(true);
	}
}
