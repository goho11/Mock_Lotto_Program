package lottoTeam3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author GGG
 *
 */
public class MainFrame extends JFrame implements ActionListener {
	private JLabel[][] lblNums = new JLabel[5][6]; // 로또 숫자 라벨
	private JLabel[][] lblCircles = new JLabel[5][6]; // 로또 원 아이콘 라벨
	private JButton[] btnAmend = new JButton[5]; // 수정 버튼
	private JButton[] btnDelete = new JButton[5]; // 삭제 버튼
	private FontHolder fontHolder = new FontHolder(); // 폰트 홀더
	private JLabel lblPrice; // 가격 라벨
	private int price; // 가격 정보
	private JButton btnResult; // 결과 버튼
	private JButton btnReset; // 초기화 버튼
	private JButton btnExit; // 종료 버튼
	private LottoData[] lottoDatas = new LottoData[6]; // 로또 정보

	public MainFrame() {
		JPanel pnlNorth = new JPanel(); // 플로우 레이아웃으로
		initNorth(pnlNorth); // 상단 패널 전체 생성
		JPanel pnlCenter = new JPanel(null); // 앱솔루트 레이아웃으로
		initCenter(pnlCenter); // 중앙 패널 전체 생성
		JPanel pnlSouth = new JPanel(null); // 앱솔루트 레이아웃으로
		initSouth(pnlSouth); // 하단 패널 전체 생성

		add(pnlCenter); // 중앙 패널 추가
		add(pnlNorth, "North"); // 상단 패널 추가
		add(pnlSouth, "South"); // 하단 패널 추가

		pack(); // 화면 크기를 패널 크기에 맞춤
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // x를 눌러도 꺼지지 않게

		// x를 눌렀을 때 종료 확인 다이알로그가 생성되게 윈도우리스너 추가
		addWindowListener(new WindowAdapter() { // 윈도우 어댑터를 사용하여 필요한 메서드만 구현
			@Override
			public void windowClosing(WindowEvent e) { // x를 누를 때
				frameClose(); // 종료 확인 다이알로그를 처리하는 메서드
			}
		});
	}

	private void initSouth(JPanel pnl) {
		pnl.setPreferredSize(new Dimension(0, 70)); // 하단 패널 세로크기 설정
		pnl.setBackground(Color.WHITE); // 배경 흰색으로 설정

		lblPrice = new JLabel("총 가격: " + price + "원"); // 가격 패널 생성
		lblPrice.setBounds(35, 25, 150, 30); // 가격 패널 크기 설정
//		lblPrice.setBorder(BorderFactory.createLineBorder(Color.black)); // 라벨 크기 확인을 위한 테두리 테스트
		lblPrice.setFont(fontHolder.getDeriveFont(Font.PLAIN, 20)); // 가격 라벨 폰트설정
		pnl.add(lblPrice); // 가격 라벨 추가

		btnResult = createMyButton("결과", new Rectangle(375, 25, 50, 30), pnl); // 메서드를 사용하여 결과 버튼 생성
		btnReset = createMyButton("초기화", new Rectangle(440, 25, 70, 30), pnl); // 메서드를 사용하여 초기화 버튼 생성
		btnExit = createMyButton("종료", new Rectangle(525, 25, 50, 30), pnl); // 메서드를 사용하여 종료 버튼 생성

	}

	private void initCenter(JPanel pnl) {
		pnl.setPreferredSize(new Dimension(600, 300)); // 중앙 패널 크기 설정
		pnl.setBackground(Color.WHITE); // 배경 흰색으로 설정

		JLabel[] lblCode = new JLabel[5]; // 로또 A~E까지 표시해주는 라벨
		for (int i = 0; i < lblCode.length; i++) { // 5번 반복
			char c = (char) ('A' + i); // A문자를 i씩 증가시켜서 A~E까지 char에 대입
			lblCode[i] = new JLabel(String.valueOf(c)); // 문자 c로 라벨 생성
			lblCode[i].setBounds(35, i * 60 - 3, 60, 60); // 라벨 바운드 설정
			lblCode[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20)); // 폰트 설정
			pnl.add(lblCode[i]); // 라벨 추가
		}

		// 로또 숫자 라벨 배열 전체 초기화 및 설정
		for (int i = 0; i < lblNums.length; i++) {
			for (int j = 0; j < lblNums[i].length; j++) {
				lblNums[i][j] = new JLabel("");
				lblNums[i][j].setBounds(j * 60 + 69, i * 60 - 3, 60, 60); // 라벨 바운드 설정
//				lblNums[i][j].setBorder(BorderFactory.createLineBorder(Color.RED)); // 테두리 테스트
				lblNums[i][j].setForeground(Color.WHITE); // 폰트 색상 흰색으로 설정
				lblNums[i][j].setHorizontalAlignment(JLabel.CENTER); // 라벨 안의 글자 가운데 정렬
				lblNums[i][j].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20)); // 폰트 설정
				pnl.add(lblNums[i][j]); // 라벨 추가
			}
		}

		// 로또 원 아이콘 라벨 배열 전체 초기화 및 설정
		for (int i = 0; i < lblCircles.length; i++) {
			for (int j = 0; j < lblCircles[i].length; j++) {
				lblCircles[i][j] = new JLabel(LottoCircle.BLACK.getImageIcon()); // 라벨 검은 원으로 생성
				lblCircles[i][j].setBounds(j * 60 + 70, i * 60, 60, 60); // 라벨 바운드 설정
				pnl.add(lblCircles[i][j]); // 라벨 추가
			}
		}

		for (int i = 0; i < btnAmend.length; i++) {
			btnAmend[i] = createMyButton("수정", new Rectangle(460, i * 60 + 13, 50, 30), pnl); // 메서드를 사용하여 수정 버튼 생성 및 설정
		}

		for (int i = 0; i < btnDelete.length; i++) {
			btnDelete[i] = createMyButton("삭제", new Rectangle(525, i * 60 + 13, 50, 30), pnl); // 메서드를 사용하여 삭제 버튼 생성 및 설정
		}
	}

	// 버튼을 생성하고 설정하여 반환하는 메서드
	private JButton createMyButton(String text, Rectangle r, JPanel pnl) {
		JButton btn = new JButton(text); // 텍스트로 버튼 생성
		btn.setBackground(Color.WHITE); // 버튼 배경 흰색으로 설정
		btn.setBounds(r); // 버튼 바운드 설정
		btn.setMargin(new Insets(0, 0, 0, 0)); // 버튼 여백
		btn.setFocusable(false); // 버튼 포커스 안되게 설정 (클릭할 때 네모 뜸)
		btn.setFont(fontHolder.getDeriveFont(Font.PLAIN, 20)); // 버튼 폰트 설정
		btn.addActionListener(this); // 버튼 액션 리스너 추가
		pnl.add(btn); // 패널에 버튼 추가
		return btn; // 버튼 반환
	}

	private void initNorth(JPanel pnl) {
		pnl.setPreferredSize(new Dimension(0, 90)); // 상단 패널 세로 크기 90으로 설정
		pnl.setBackground(Color.WHITE); // 패널 배경 흰색으로 설정

		JLabel lblLotto = new JLabel(new ImageIcon(MainFrame.class.getResource("/resource/lotto.png"))); // 로또 아이콘 라벨 생성
		pnl.add(lblLotto); // 라벨 추가
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 액션 리스너 구현 메서드
		Object o = e.getSource(); // 액션 이벤트에서 클릭된 객체 가져오기
		for (int i = 0; i < btnAmend.length; i++) { // 수정 버튼
			if (o.equals(btnAmend[i])) { // 클릭된 버튼이 i번째 수정 버튼일 때
				LottoData input = new LottoData(randomLotto(), new Random().nextBoolean());
//								  NumberChoose.showDailog();
				if (!input.isBuy())
					return;
				boolean prev = lottoDatas[i] != null && lottoDatas[i].isBuy();
				System.out.println(prev);
				lottoDatas[i] = input;
				int[] nums = lottoDatas[i].getNums();
				for (int j = 0; j < nums.length; j++) {
					lblNums[i][j].setText(String.valueOf(nums[j]));
					if (nums[j] <= 10)
						lblCircles[i][j].setIcon(LottoCircle.YELLOW.getImageIcon());
					else if (nums[j] <= 20)
						lblCircles[i][j].setIcon(LottoCircle.BLUE.getImageIcon());
					else if (nums[j] <= 30)
						lblCircles[i][j].setIcon(LottoCircle.RED.getImageIcon());
					else if (nums[j] <= 40)
						lblCircles[i][j].setIcon(LottoCircle.GRAY.getImageIcon());
					else
						lblCircles[i][j].setIcon(LottoCircle.GREEN.getImageIcon());
				}
				if (!prev) {
					price += 1000;
					lblPrice.setText("총 가격: " + price + "원");
				}
				return;
			}
		}
		for (int i = 0; i < btnDelete.length; i++) { // 삭제 버튼
			if (o.equals(btnDelete[i])) {
				if (lottoDatas[i] == null)
					return;
				if (!lottoDatas[i].isBuy())
					return;
				lottoDatas[i].setBuy(false);
				for (int j = 0; j < lblNums[i].length; j++) {
					lblNums[i][j].setText("");
				}
				for (int j = 0; j < lblCircles[i].length; j++) {
					lblCircles[i][j].setIcon(LottoCircle.BLACK.getImageIcon());
				}
				price -= 1000;
				lblPrice.setText("총 가격: " + price + "원");

				return;
			}
		}
		if (o.equals(btnResult)) { // 결과 버튼
			ResultDialog.showDialog(lottoDatas);
		} else if (o.equals(btnReset)) { // 초기화 버튼
			for (int i = 0; i < lblNums.length; i++) {
				for (int j = 0; j < lblNums[i].length; j++) {
					lblNums[i][j].setText("");
				}
			}
			for (int i = 0; i < lblCircles.length; i++) {
				for (int j = 0; j < lblCircles[i].length; j++) {
					lblCircles[i][j].setIcon(LottoCircle.BLACK.getImageIcon());
				}
			}
			Arrays.fill(lottoDatas, null);
			price = 0;
			lblPrice.setText("총 가격: " + price + "원");
		} else if (o.equals(btnExit)) { // 종료 버튼
			frameClose();
		}
	}

	private void frameClose() {
		int input = JOptionPane.showOptionDialog(MainFrame.this, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, null, null);
		if (input == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private int[] randomLotto() {
		int[] result = new int[6];
		Random random = new Random();
		Set<Integer> set = new TreeSet<>();
		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}
		int i = 0;
		for (Integer num : set) {
			result[i] = num;
			i++;
		}
		return result;
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
