import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt(); // 트리 높이
        int k = sc.nextInt(); // 리프 노드 당 초기 업무 개수
        int r = sc.nextInt(); // 총 업무 처리 일수
        int n = (int)Math.pow(2, h + 1) - 1; // 노드 개수
        int m = (int)Math.pow(2, h); // 리프 노드 개수

        Queue<Integer>[][] q = new Queue[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                q[i][j] = new LinkedList<>(); // 큐 초기화
            }
        }

        // 리프 노드에 값 추가
        for (int i = n - m; i < n; i++) {
            for (int j = 0; j < k; j++) {
                q[0][i].add(sc.nextInt());
            }
        }

        int res = 0;
        int mid = (int)Math.pow(2, h) - 1; // 중간 노드 개수

        // 업무 수행
        for (int day = 1; day <= r; day++) {
            int now = day % 2;

            // 1. 부사장 처리
            if (now == 1 && !q[0][0].isEmpty()) {
                res += q[0][0].poll(); // 홀수 날
            } else if (now == 0 && !q[1][0].isEmpty()) {
                res += q[1][0].poll(); // 짝수 날
            }

            // 2. 중간 업무 처리
            for (int i = 1; i <= mid; i++) {
                int parent = (i - 1) / 2; // 부모 노드 인덱스
                int idx = (i % 2 == 0) ? 1 : 0; // 부모 큐 선택
                if (now == 1 && !q[0][i].isEmpty()) { // 홀수 날 처리
                    q[idx][parent].add(q[0][i].poll());
                } else if (now == 0 && !q[1][i].isEmpty()) { // 짝수 날 처리
                    q[idx][parent].add(q[1][i].poll());
                }
            }

            // 3. 말단 업무 처리
            for (int i = n - m; i < n; i++) {
                if (!q[0][i].isEmpty()) {
                    int parent = (i - 1) / 2; // 부모 노드 인덱스
                    int child = q[0][i].poll();
                    if(i % 2 == 0) {
                        q[1][parent].add(child); // 현재 날 기준 부모 큐에 추가
                    }
                    else {
                        q[0][parent].add(child); 
                    }
                }
            }
        }
        System.out.println(res);
    }
}
