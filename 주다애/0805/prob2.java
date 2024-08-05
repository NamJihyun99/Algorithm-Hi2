import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 이차원 배열과 연산(골드 4)
// 배열의 인덱스는 1부터 시작한다..;;
// 1. 정렬 메소드 실수
// 2. 계산 끝나고 나서 남은 공간 0으로 채워주기
// 3. 75%에서 틀림 --> 100초까지 확인해야 한다.
public class BaekJoon17140 {
    static int[][] arr;
    static int r, c, k;
    static int rLen = 3;
    static int cLen = 3;
    public static void main(String[] args) throws IOException {
        arr = new int[101][101];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    private static int solution() {
        // 100초에 통과할 수도 있음
        for (int i = 0; i <= 100; i++) {
            if(arr[r][c] == k) {
                return i;
            }
            if(rLen >= cLen) {
                for (int p = 1; p <= rLen; p++) {
                    operateR(p);
                }
            }
            else {
                for (int p = 1; p <= cLen; p++) {
                    operateC(p);
                }
            }
        }
        return -1;
    }

    private static void operateR(int p) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= cLen; i++) {
            if(arr[p][i] == 0) continue;
            int v = map.getOrDefault(arr[p][i], 0);
            map.put(arr[p][i], v + 1);
        }
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            pq.offer(new Info(key, map.get(key)));
        }
        int idx = 1;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            arr[p][idx++] = info.num;
            arr[p][idx++] = info.cnt;
        }
        cLen = Math.max(cLen, idx);

        // 남은 공간 0으로 채워주기
        for (int i = idx; i <= 100; i++) {
            arr[p][i] = 0;
        }
    }

    private static void operateC(int p) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= rLen; i++) {
            if(arr[i][p] == 0) continue;
            int v = map.getOrDefault(arr[i][p], 0);
            map.put(arr[i][p], v + 1);
        }
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            pq.offer(new Info(key, map.get(key)));
        }
        int idx = 1;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            arr[idx++][p] = info.num;
            arr[idx++][p] = info.cnt;
        }
        rLen = Math.max(rLen, idx);
        for (int i = idx; i <= 100; i++) {
            arr[i][p] = 0;
        }
    }

    static class Info implements Comparable<Info> {
        int num;
        int cnt;

        public Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            if(this.cnt == o.cnt) return this.num - o.num;
            return this.cnt - o.cnt;
        }
    }
}
