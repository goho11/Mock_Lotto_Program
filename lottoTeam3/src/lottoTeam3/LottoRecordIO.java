package lottoTeam3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LottoRecordIO {
	public static List<LottoRecord> readLottoRecord(File source) {
		List<LottoRecord> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(source));
			String line;
			while ((line = br.readLine()) != null) {
				try {
					LottoRecord lr = LottoRecord.tryParse(line);
					list.add(lr);
				} catch (Exception e) {
					System.out.println("로또 레코드 parsing 시, 예외가 발생했으나 다시 재개하려 합니다.");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static boolean writeLottoRecords(File dest, LottoRecord lottoRecord) {
		boolean exists = dest.exists();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(dest, true));

			pw.println(lottoRecord);
		} catch (IOException e) {
			System.out.println("출력 도중에 문제가 생겨서, 파일 기록을 추가할 수 없었습니다.");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		return exists;
	}
}
