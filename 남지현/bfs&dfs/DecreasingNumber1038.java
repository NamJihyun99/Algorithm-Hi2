import java.util.*;
import java.io.*;

// 백준 1038번 감소하는 수
class Main {

    static List<Long> numbers;

    private static void dfs(int idx, long result) {
        if (idx > 10) return;
        numbers.add(result);
        for (int i=0; i<result%10; i++) {
            dfs(idx+1, result*10+i);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numbers = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
    
        if (N < 10) {
            System.out.println(N);
            return;
        } else if (N >= 1023) {
            System.out.println(-1);
            return;
        }
        for (int i=0; i<10; i++) {
            dfs(1, i);
        }
        Collections.sort(numbers);
        System.out.println(numbers.get(N));
    }
}
