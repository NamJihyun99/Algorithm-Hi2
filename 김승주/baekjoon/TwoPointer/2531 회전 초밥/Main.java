import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Integer> sushies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sushies.add(Integer.valueOf(br.readLine()));
        }

        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < k; i++) {
            dq.offer(sushies.get(i));
        }
        dq.addLast(c);

        Set<Integer> set = new HashSet<>(dq);
        if (set.size() == k + 1) {
            System.out.println(k + 1);
            return;
        }
        
        int max = -1;
        for (int i = 1, j = 0; i < N; i++) {
            dq.removeFirst();
            dq.removeLast(); // 쿠폰 번호 제거
            if (i + k <= N) {
                dq.addLast(sushies.get(i + k - 1));
            } else {
                dq.addLast(sushies.get(j++));
            }
            dq.addLast(c);

            set.clear();
            set.addAll(dq);
            if (set.size() == k + 1) {
                System.out.println(k + 1);
                return;
            }
            max = Math.max(max, set.size());
        }

        System.out.println(max);
    }
}