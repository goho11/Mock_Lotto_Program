package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NumberChoose extends JDialog implements ActionListener {
	// 지역변수>필드. 어디서든 사용가능(Ctrl + 1)
	// 필드값
	private JButton[] btns;
	private int count = 0;
	private JButton reset;
	private JButton check;
	private JButton auto;
	private boolean buy = false;
	private FontHolder fontHolder = new FontHolder();
	private Mode mode = Mode.AUTO; // 기본은 자동모드

	public NumberChoose(LottoData inputlotto, JFrame frame) {
		setTitle("번호 선택");
		// 대화상자 닫기 전까지 다른 상호작용 불가
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel pnl = new JPanel(new BorderLayout());
		// 패널A : 1~45 숫자범위 / 숫자버튼 왼쪽정렬
		JPanel pnlA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlA.setBackground(Color.WHITE);
		// 패널B : 기능 버튼
		JPanel pnlB = new JPanel();
		pnlB.setBackground(Color.WHITE);

		btns = new JButton[45];
		check = new JButton("확인");
		reset = new JButton("초기화");
		auto = new JButton("자동");

		check.addActionListener(this);
		check.setBackground(Color.WHITE);
		reset.addActionListener(this);
		reset.setBackground(Color.WHITE);
		auto.addActionListener(this);
		auto.setBackground(Color.WHITE);

		// 버튼 여백 제거 (위, 왼, 아래, 오)
		reset.setFocusable(false);
		reset.setMargin(new Insets(0, 2, 0, 2));
		reset.setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
		check.setFocusable(false);
		check.setMargin(new Insets(0, 2, 0, 2));
		check.setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));
		auto.setFocusable(false);
		auto.setMargin(new Insets(0, 2, 0, 2));
		auto.setFont(fontHolder.getDeriveFont(Font.PLAIN, 17));

		// 버튼 숫자 배열
		for (int i = 0; i < btns.length; i++) {
			pnlA.add(btns[i] = new JButton(String.valueOf(i + 1)));
			btns[i].setPreferredSize(new Dimension(35, 35));
			btns[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
			btns[i].setMargin(new Insets(0, 0, 0, 0)); // 버튼 여백
			btns[i].setBackground(Color.WHITE);
			btns[i].addActionListener(this); // 기능 추가
			btns[i].setFocusable(false); // 선택시 네모 박스 안뜨게
		}
		// 로또 번호 수정
		if (inputlotto != null) {
			int nums[] = inputlotto.getNums();
			for (int i = 0; i < nums.length; i++) {
				btns[nums[i] - 1].setBackground(Color.GRAY);
			}
			// count 6은 자동 모드(기본모드)
			mode = inputlotto.getMode();
			count = 6;
		}

		pnlB.add(check);
		pnlB.add(reset);
		pnlB.add(auto);
		pnl.add(pnlA);
		pnl.add(pnlB, "South");

		add(pnl);
		setSize(421, 280);

		// setModal 대화상자를 닫기 전까지 다른 상호작용 불가
		setModal(true);
		// 다이얼로그 창을 닫기
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// JFrame 위치 지정 - frame은 메인창 위치를 의미한다
		int x = frame.getX();
		int y = frame.getY();
		int width = frame.getWidth();
		setLocation(x + width, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 클릭한 버튼을 e이벤트 정보로 가져와라
		// getSource 버튼 객체
		Object o = e.getSource();

		// 로또 번호 선택
		// 버튼 6개 클릭시 비활성화 - count 증가
		for (int i = 0; i < btns.length; i++) {
			// 오브젝트는 equals를 포함한다 - btns[i]번째 비교
			if (o.equals(btns[i])) {
				// 버튼 선택O - 화이트
				// 버튼 선택X - 그레이
				if (btns[i].getBackground().equals(Color.WHITE)) {
					if (count < 6) {
						btns[i].setBackground(Color.GRAY);
						count++;
						if (count < 6) {
							mode = Mode.SEMI;
						} else {
							mode = Mode.MANUAL; // 수동 : 번호 전부 선택 클릭
						}
					} else {
						JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택했습니다");
					}
				} else {
					// 버튼 선택취소 - 그레이>화이트, count 감소
					btns[i].setBackground(Color.WHITE);
					count--;
					if (count == 0) {
						mode = Mode.AUTO;
					} else {
						mode = Mode.SEMI;
					}
				}
				// void return : 해당 함수 빠져 나오기(break와 비슷함)
				return;
			}
		}
		// 선택된 번호 리셋
		// 버튼 비활성화(그레이)>활성화(기본값), count 초기화
		if (o.equals(reset)) {
			for (int i = 0; i < btns.length; i++) {
				if (btns[i].getBackground().equals(Color.GRAY)) {
					btns[i].setBackground(null);
				}
			}
			count = 0;
			mode = Mode.AUTO;
			// 랜덤 번호 6개 자동 선택
			// 번호 6개 이상 선택시 - 선택불가 메세지 출력
		} else if (o.equals(auto)) {
			if (count == 6) {
				JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택했습니다");
				return;
			}
			// 최대 6번까지 랜덤 선택 - 선택된 횟수만큼 count 감소
			Random r = new Random();
			for (; count < 6; count++) {
				int n = r.nextInt(45);
				if (btns[n].getBackground().equals(Color.GRAY)) {
					count--;
				} else { // count가 6이면 랜덤 번호 선택
					btns[n].setBackground(Color.GRAY);
				}
			}
			// 자동으로 바로 들어감
			buy = true;
			dispose();

			// 확인 - 선택된 숫자가 6개 이면 빠져나옴
		} else if (o.equals(check)) {
			if (count != 6) {
				JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택해주세요");
				return;
			}
			// 선택창 닫기 위해 만든 객체
			buy = true;
			dispose();
		}
	}

	// '확인'클릭시 번호 6개를 LottoData로 전달
	public static LottoData showDialog(LottoData inputlotto, JFrame frame) {
		NumberChoose nc = new NumberChoose(inputlotto, frame);
		nc.setVisible(true);
		LottoData lottoData = nc.getLottoData();
		return lottoData;
	}

	// 창을 끄면 선택된 숫자가 전달됨
	// buy는 boolean. 따로 지정할 필요가 없다
	private LottoData getLottoData() {
		int[] nums = new int[6];
		if (buy) {
			int j = 0;
			for (int i = 0; i < btns.length; i++) {
				if (btns[i].getBackground().equals(Color.GRAY)) {
					nums[j] = i + 1;
					j++;
				}
			}
		}
		return new LottoData(nums, buy, mode);
	}
}
