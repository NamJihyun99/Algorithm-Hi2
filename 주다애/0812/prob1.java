import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문자열 복사(골드 5)
// 1. 메모리 초과 -> Set에 모든 문자열 조합 저장하지 말자 --> 그냥 s에서 검사하면 됨
public class BaekJoon2195 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();

		int pLen = p.length();
		int start = 0;
		int end = p.length();
		int cnt = 0;
		while (start < pLen) {
			if(s.contains(p.substring(start, end))) {
				cnt++;
				start = end;
				end = pLen;
			}
			else {
				end -= 1;
			}
		}
		System.out.println(cnt);
	}
}
