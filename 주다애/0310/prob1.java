import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// IPv6(골드 5)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String[] temp = str.split(":");
		int size = temp.length;
		String res = "";
		if (str.equals("::")) {
			System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
			return;
		}
		// 연속 0 그룹 존재 X
		if (size == 8) {
			res = completeAddress(temp);
		}
		// 연속 0 그룹 존재 O
		else {
			res = completeAddress2(temp);
		}
		System.out.println(res);
	}

	static String completeAddress2(String[] temp) {
		List<String> gather = new ArrayList<>();
		int len = temp.length;
		int nums = 0;
		for (int i = 0; i < len; i++) {
			String s = temp[i];
			if (!s.equals("")) nums += 1;
		}
		int zero = 8 - nums;
		int idx = 0;
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			String s = temp[i];
			String str  = "";
			if (s.equals("")) {
				idx = i;
				cnt += 1;
				continue;
			}
			int sLen = s.length();
			int diff = 4 - sLen;
			if (diff == 0) {
				gather.add(s + ":");
				continue;
			}
			for (int j = 0; j < diff; j++) {
				str += "0";
			}
			str += s + ":";
			gather.add(str);
		}
		String comp = "";
		String[] arr = new String[8];
		// 1::1
		if (cnt == 1) {
			int k = 0;
			int index = 0;
			while (k != 8) {
				if (idx == k) {
					for (int i = 0; i < zero; i++) {
						arr[i + k] = "0000" + ":";
					}
					k += zero;
					continue;
				}
				else {
					arr[k] = gather.get(index++);
				}
				k += 1;
			}
		}
		else {
			// ::1
			if (cnt > 1) {
				for (int i = 0; i < zero; i++) {
					arr[i] = "0000" + ":";
				}
				int index = 0;
				for (int i = zero; i < 8; i++) {
					arr[i] = gather.get(index++);
				}
			}
			// 1::
			else {
				for (int i = 0; i < nums; i++) {
					arr[i] = gather.get(i);
				}
				for (int i = nums; i < 8; i++) {
					if (i == 7) arr[i] = "0000";
					else arr[i] = "0000" + ":";
				}
			}
		}
		for (String s : arr) comp += s;
		if (comp.length() > 39) comp = comp.substring(0, 39);
		return comp;
	}

    static String completeAddress(String[] temp) {
		String str = "";
		for (int i = 0; i < 8; i++) {
			String s = temp[i];
			int len = s.length();
			int diff = 4 - len;
			for (int j = 0; j < diff; j++) {
				str += "0";
			}
			str += s + ":";
		}
		if (str.length() > 39) str = str.substring(0, 39);
		return str;
	}
}
