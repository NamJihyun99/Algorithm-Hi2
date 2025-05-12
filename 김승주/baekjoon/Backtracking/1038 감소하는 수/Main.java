import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N <= 10) {
            System.out.println(N);
            return;
        }
        if (N > 1022) {
            System.out.println(-1);
            return;
        }

        List<Long>[] lists = new List[10];
        for (int i = 0; i < 10; i++) {
            lists[i] = new ArrayList<>();
            lists[i].add((long)i);
        }

        List<Long> result = new ArrayList<>(lists[0]);
        for (int first = 1; first <= 9; first++) {
            for (int second = 0; second < first; second++) {
                for (long num : lists[second]) {
                    lists[first].add(num + first * (long) Math.pow(10, String.valueOf(num).length()));
                }
            }
            result.addAll(lists[first]);
        }
        Collections.sort(result);
        System.out.println(result.get(N));
    }
}