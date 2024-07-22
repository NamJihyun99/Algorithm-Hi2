import java.util.Scanner;

// A와 B2(골드 5)
// 1. 무작정 재귀 돌리면 시간 초과난다(O(N^2))
// 2. T의 길이를 줄이며 S와 같은지 확인하는 방법으로 해결하자!
public class BaekJoon12919 {
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();

        if (s.equals(t)) {
            System.out.println(1);
            return;
        }

        run(s, t);
        System.out.println(ans);
    }

    private static void run(String s, String t) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                ans = 1;
            }
            return;
        }
        if (t.charAt(0) == 'B') {
            String substring = t.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            run(s, string);
        }

        if (t.charAt(t.length() - 1) == 'A') {
            run(s, t.substring(0, t.length() - 1));
        }
    }
}
