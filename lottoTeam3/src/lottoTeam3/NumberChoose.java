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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Q. 번호 선택 대화상자에서, 실수로 선택된 번호 하나를 선택 취소 하고 싶어요
// A. enable을 setcolor로 변경하기. 바뀐 색상 클릭시 원래 색상 돌아옴
// enable 활성화, enable(true)하면 클릭이 되는 식

public class NumberChoose extends JDialog implements ActionListener {
	// 지역변수를 필드로 바꿔 괄호 밖에도 사용가능하게(컨트롤 1)
	// 필드값
	private JButton[] btns;
	private int count = 0;
	private JButton reset;
	private JButton check;
	private JButton auto;
	private boolean buy = false;
	private FontHolder fontHolder = new FontHolder();

	public NumberChoose(JFrame frame) {
		setTitle("로또 번호 선택");

		JPanel pnl = new JPanel(new BorderLayout());
		// 패널A : 1~45 숫자범위
		JPanel pnlA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 패널B : 기능 안내 버튼
		JPanel pnlB = new JPanel();
		btns = new JButton[45];
		check = new JButton("확인");
		reset = new JButton("초기화");
		auto = new JButton("자동");

		reset.addActionListener(this); // 배열값이 아니라 위에 있어도 됨
		check.addActionListener(this);
		auto.addActionListener(this);

		// 배열은 같다고 하면 안됨. 마지막 번호를 추가하고 싶으면 -1
		for (int i = 0; i < btns.length; i++) {
			pnlA.add(btns[i] = new JButton(String.valueOf(i + 1)));
			btns[i].setPreferredSize(new Dimension(35, 35));
			btns[i].setFont(fontHolder.getDeriveFont(Font.PLAIN, 20));
			// setMargin 왼쪽 정렬
			btns[i].setMargin(new Insets(0, 0, 0, 0));
			btns[i].setBackground(Color.WHITE);
			// i값이 for문에 돌아감. 배열값
			btns[i].addActionListener(this); // 버튼 누르면 비활성화됨(로또 번호 선택)
			// 버튼 선택시 가운데 네모 박스 안뜨게
			btns[i].setFocusable(false);
		}
		pnlA.setBackground(Color.WHITE);
		pnlB.setBackground(Color.WHITE);
		reset.setBackground(Color.WHITE);
		check.setBackground(Color.WHITE);
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

		pnlB.add(check);
		pnlB.add(reset);
		pnlB.add(auto);
		pnl.add(pnlA);
		pnl.add(pnlB, "South");

		add(pnl);
		setSize(421, 280);
		// setModal 대화상자를 닫기 전까지 다른 상호작용 불가
		setModal(true);
		// 다이얼로그 창을 닫아라
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// JFrame 위치 지정 - frame은 메인창 위치를 의미한다
		int x = frame.getX();
		int y = frame.getY();
		int width = frame.getWidth();
		setLocation(x + width, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 클릭한 버튼을 e이벤트 정보를 가져와라
		// getSource 버튼의 객체
		Object o = e.getSource();

		// 버튼 클릭시 비활성화 - 6개만 선택
		// 카운트가 6이면 클릭안되게 - 필드에 카운트를 추가
		for (int i = 0; i < btns.length; i++) {
			// 오브젝트안에 equals가 있다 - btns[i]번째와 같은지 비교해라
			if (o.equals(btns[i])) {
				// 버튼 선택 안함 - 화이트
				if (btns[i].getBackground().equals(Color.WHITE)) {
					if (count < 6) {
						// 버튼 선택 - 그레이
						btns[i].setBackground(Color.GRAY);
						count++;
					} else {
						JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택했습니다");
					}
				} else { // 버튼 선택된걸 취소시 화이트로 변경. 카운터 빼기
					btns[i].setBackground(Color.WHITE);
					count--;
				}
				// void라서 리턴값 없음
				// 선택된 버튼 누르면 끝남
				return;
			}
		}
		// 리셋 : 비활성화된 버튼이 활성화됨
		if (o.equals(reset)) {
			for (int i = 0; i < btns.length; i++) {
				if (btns[i].getBackground().equals(Color.GRAY)) {
					btns[i].setBackground(null);
				}
			}
			count = 0;
			// 자동 : 클릭시 랜덤 번호 최대 6개 선택(비활성화)
			// 중복번호가 나타나지 않게 설정하기
		} else if (o.equals(auto)) {
			// 번호 6개 선택되면, 선택불가 안내
			if (count == 6) {
				JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택했습니다");
				return;
			}
			Random r = new Random();
			for (; count < 6; count++) {
				int n = r.nextInt(45);
				// 버튼이 비활성화이면 카운트를 감소해라
				// 활성화 true, 비활성화 false를 반환 - 버튼을 체크하면 비활성화 상태
				// 카운트 줄이기
				if (btns[n].getBackground().equals(Color.GRAY)) {
					// 기본이 true임.현재 활성화(true)이니깐 랜덤 선택 걸리면 비활성화(flase)로 돌리기
					// set을 해야 빨강값을 보낸다
					count--;
				} else {
					btns[n].setBackground(Color.GRAY);
				}
			}
			// 확인
			// 확인 버튼 누르면
		} else if (o.equals(check)) {
			if (count != 6) {
				JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택해주세요");
				return; // void에 return이 있으면 해당 함수를 빠져 나온다. break와 비슷한 기능
			}
			// buy가 true로 변경
			buy = true;
			// 번호 선택 해당 창 닫기
			dispose();
		}
	}

	// '확인'클릭시 선택된 6개 번호가 로또데이터로 넘어가는 발판
	public static LottoData showDialog(JFrame frame) {
		NumberChoose nc = new NumberChoose(frame);
		nc.setVisible(true);
		LottoData lottoData = nc.getLottoData();
		return lottoData;
	}

	private LottoData getLottoData() {
		// 창이 꺼질때 비활성화된 숫자를 전달
		int[] nums = new int[6];
		// 비활성화 숫자를 nums에 넣어라
		// nums 0~5을. true일때만
		if (buy) { // buy는 boolean이라 따로 지정할 필요가 없다
			// 모든 버튼을 for문 돈다
			// isEnabled이 false이면 nums에 값을 넣어라
			int j = 0;
			for (int i = 0; i < btns.length; i++) {
				// 버튼 배경색을 가져와서 레드와 비교해라
				if (btns[i].getBackground().equals(Color.GRAY)) {
					nums[j] = i + 1;
					j++;
				}
			}
		}
		return new LottoData(nums, buy);
	}
}
