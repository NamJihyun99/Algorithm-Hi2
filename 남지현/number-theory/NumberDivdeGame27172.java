import java.util.*;
import java.io.*;

// 백준 27172번 수 나누기 게임
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        int max = -1;
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, numbers[i]);
        }
        int[] scores = new int[max+1];
        boolean[] contains = new boolean[max+1];
        for (int i : numbers) {
            contains[i] = true;
        }
        for (int i : numbers) {
            for (int j=2*i; j<=max; j+=i) {
                if (contains[j]) {
                    // j % i == 0
                    scores[i]++;
                    scores[j]--;
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i : numbers) {
            answer.append(scores[i]).append(" ");
        }
        System.out.println(answer.toString());
    }
}
