package lottoTeam3;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoResult {
	public static void main(String[] args) {
		Random random = new Random();
		Set<Integer> set = new TreeSet<>();
		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}
		int bonus;
		do {
			bonus = random.nextInt(45) + 1;
		} while (set.contains(bonus));
		System.out.println(set + " + " + bonus);
	}
}