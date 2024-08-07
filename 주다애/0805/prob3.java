import java.util.Scanner;

// 문자열 교환(실버 1)
// 풀긴 했는데 왜 맞는지 모르겠다.
public class BaekJoon1522 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String circle = sc.next();
		int len = circle.length();
		int aCnt = 0;
		// 'a' 개수 구하기
		for (int i = 0; i < len; i++) {
			if(circle.charAt(i) == 'a') aCnt++;
		}
		int min = len;
		for (int i = 0; i < len; i++) {
			// 'b' 개수
			int bCnt = 0;
			for (int j = i; j < aCnt + i; j++) {
				if(circle.charAt(j % len) == 'b') bCnt++;
			}
			min = Math.min(min, bCnt);
		}
		System.out.println(min);
	}
}
