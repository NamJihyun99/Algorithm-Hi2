import java.util.*;
import java.io.*;

// 백준 8111번 0과 1
class Main {
    
    static class Number {
        String str;
        int val;
        Number(String str, int val) {
            this.str = str;
            this.val = val;
        }
    }

    static String solve(int N) {
        if (N == 1) {
            return "1";
        }
        boolean[] visited = new boolean[N+1];
        Queue<Number> queue = new ArrayDeque<>();
        visited[1] = true;
        queue.add(new Number("1", 1));
        while (!queue.isEmpty()) {
            Number number = queue.poll();
            if (number.val == 0) {
                return number.str;
            }
            int next;
            next = number.val*10;
            next %= N;
            if (!visited[next]) {
                visited[next] = true;
                queue.add(new Number(number.str.concat("0"), next));
            }
            next = number.val*10+1;
            next %= N;
            if (!visited[next]) {
                visited[next] = true;
                queue.add(new Number(number.str.concat("1"), next));
            }
        }
        return "BRAK";
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<T; i++) {
            sb.append(solve(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }
}
