package lottoTeam3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

	public NumberChoose() {
		setTitle("로또 번호 선택");
		// 모달 대화상자 만듬. 사용자가 대화상자를 닫기 전까지 다른 창과 상호작용 불가
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel pnl = new JPanel(new BorderLayout());
		// 패널A : 1~45 숫자범위
		JPanel pnlA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 패너류 : 기능 안내 버튼
		JPanel pnlB = new JPanel();
		btns = new JButton[45];
		check = new JButton("확인");
		reset = new JButton("초기화");
		auto = new JButton("자동");

		reset.addActionListener(this); // 배열값이 아니라 위에 있어도 됨
		check.addActionListener(this);
		auto.addActionListener(this);

		// 배열은 같다고 하면 안됨. 마지막 번호를 추가하고 싶으면 -1
		Font font = new Font("맑은 고딕", Font.BOLD, 18);
		for (int i = 0; i < btns.length; i++) {
			pnlA.add(btns[i] = new JButton(String.valueOf(i + 1)));
			btns[i].setPreferredSize(new Dimension(35, 35));
			btns[i].setFont(font);
			// setMargin 왼쪽 정렬
			btns[i].setMargin(new Insets(0, 0, 0, 0));
			// i값이 for문에 돌아감. 배열값
			btns[i].addActionListener(this); // 버튼 누르면 비활성화됨(로또 번호 선택)
		}

		pnlB.add(check);
		pnlB.add(reset);
		pnlB.add(auto);
		pnl.add(pnlA);
		pnl.add(pnlB, "South");

		add(pnl);
		setSize(421, 280);
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
				if (btns[i].getBackground().equals(Color.red)) {
					btns[i].setBackground(null);
					count--;
				} else {
					if (count < 6) {
						btns[i].setBackground(Color.red);
						count++;
					} else {
						JOptionPane.showMessageDialog(NumberChoose.this, "번호 6개를 선택했습니다");
					}
				}
				// void라서 리턴값 없음
				// 선택된 버튼만 누르고 끝남
				return;
			}
		}
		// 리셋 : 비활성화된 버튼이 활성화됨
		if (o.equals(reset)) {
			for (int i = 0; i < btns.length; i++) {
				if (btns[i].getBackground().equals(Color.red)) {
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
				if (btns[n].getBackground().equals(Color.red)) {
					// 기본이 true임.현재 활성화(true)이니깐 랜덤 선택 걸리면 비활성화(flase)로 돌리기
					// set을 해야 빨강값을 보낸다
					count--;
				} else {
					btns[n].setBackground(Color.red);
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
	public static LottoData showDialog() {
		NumberChoose nc = new NumberChoose();
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
				if (btns[i].getBackground().equals(Color.red)) {
					nums[j] = i + 1;
					j++;
				}
			}
		}
		return new LottoData(nums, buy);
	}
}
