import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<Integer>[] tree;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        // dp[i]: i번 노드를 루트로 하는 서브 트리에서 필요한 최소 얼리 어답터 수
        dp = new int[N + 1][2]; 
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        tree[1].add(0);
        System.out.println(dynamicProgramming(1, 0, true));
    }

    private static int dynamicProgramming(int subRoot, int parent, boolean isEarlyAdopter) {
        // 리프 노드인 경우
        if (tree[subRoot].size() == 1) {
            return (isEarlyAdopter)? 0 : 1;
        }

        // 자신의 부모 노드가 얼리어답터인 경우: 자신은 얼리어답터여도 되고, 아니어도 됨.
        if (isEarlyAdopter) {
            if (dp[subRoot][0] == -1 || dp[subRoot][1] == -1) {
                int temp0 = 0; // 자신은 얼리어답터가 아닌 경우
                int temp1 = 1; // 자신도 얼리 어답터인 경우
                for (int neighbor : tree[subRoot]) {
                    if (neighbor == parent) {
                        continue;
                    }
                    temp0 += dynamicProgramming(neighbor, subRoot, false);
                    temp1 += dynamicProgramming(neighbor, subRoot, true);
                }
                dp[subRoot][0] = (dp[subRoot][0] == -1)? temp0 : dp[subRoot][0];
                dp[subRoot][1] = (dp[subRoot][1] == -1)? temp1 : dp[subRoot][1];
            }
        }
        // 자신의 부모가 얼리 어답터가 아닌 경우: 자신은 무조건 얼리어답터여야 함.
        else {
            if (dp[subRoot][1] == -1) {
                dp[subRoot][1] = 1;
                for (int neighbor : tree[subRoot]) {
                    if (neighbor == parent) {
                        continue;
                    }
                    dp[subRoot][1] += dynamicProgramming(neighbor, subRoot, true);
                }
            }
            return dp[subRoot][1];
        }

        return Math.min(dp[subRoot][0], dp[subRoot][1]);
    }
}