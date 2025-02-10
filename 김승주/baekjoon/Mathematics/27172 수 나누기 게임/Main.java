import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> cards = new HashMap<>();
        int[] scores = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards.put(Integer.valueOf(st.nextToken()), i);
        }
        List<Integer> sorted = new ArrayList<>(cards.keySet());
        Collections.sort(sorted);
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; (sorted.get(i) << 1) <= sorted.get(j); j--) {
                if (sorted.get(j) % sorted.get(i) == 0) {
                    scores[cards.get(sorted.get(i))]++;
                    scores[cards.get(sorted.get(j))]--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int score : scores) {
            sb.append(score).append(" ");
        }
        System.out.println(sb.toString());
    }
}