import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑(골드 5)
// 1. 시간 초과 --> O(N^2)
// 2. Stack --> O(N)
public class BaekJoon2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 입력
        for (int i = 0; i < n; i++) {
            int h = Integer.parseInt(st.nextToken());
            // 비었으면 나보다 큰 탑 없음
            if(stack.isEmpty()) {
                stack.add(new int[]{i + 1, h});
                sb.append("0 ");
                continue;
            }
            while (true) {
                // 비었으면 나보다 큰 탑 없음
                if(stack.isEmpty()) {
                    stack.add(new int[]{i + 1, h});
                    sb.append("0 ");
                    break;
                }
                // 나보다 큰 탑이 있으면
                if(stack.peek()[1] > h) {
                    sb.append(stack.peek()[0] + " ");
                    stack.add(new int[]{i + 1, h});
                    break;
                }
                // 내가 더 크면
                else {
                    stack.pop();
                }
            }
        }
        System.out.println(sb);
    }
}
