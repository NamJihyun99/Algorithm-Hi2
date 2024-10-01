import java.io.*;

// 백준 2342번 - Dance Dance Revolution

class Main {

    private static int cost(int before, int after) {
        if (before==0) return 2;
        if (before==after) return 1;
        if (after-before==2 || before-after==2) return 4;
        return 3;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = bf.readLine().split(" ");
        int N = tokens.length-1;
        int[] points = new int[N];
        for (int i=0; i<N; i++) {
            points[i] = Integer.parseInt(tokens[i]);
        }
        int[][][] memo = new int[points.length][5][5];
        memo[0][points[0]][0] = 2;
        memo[0][0][points[0]] = 2;
        for (int i=1; i<N; i++) {
            for (int j=0; j<5; j++) {
                for (int k=0; k<5; k++) {
                    if (memo[i-1][j][k] == 0) continue;
                    if (memo[i][j][points[i]] == 0) {
                        memo[i][j][points[i]] = memo[i-1][j][k]+cost(k, points[i]);
                    } else {
                        memo[i][j][points[i]] = Math.min(memo[i][j][points[i]], memo[i-1][j][k]+cost(k, points[i]));
                    }
                    if (memo[i][points[i]][k] == 0) {
                        memo[i][points[i]][k] = memo[i-1][j][k]+cost(j, points[i]);
                    } else {
                        memo[i][points[i]][k] = Math.min(memo[i][points[i]][k], memo[i-1][j][k]+cost(j, points[i]));
                    }
                }
            }
        }
        int answer = 400_001;
        for (int i=0; i<5; i++) {
            if (memo[N-1][points[N-1]][i]>0)
                answer = Math.min(answer, memo[N-1][points[N-1]][i]);
            if (memo[N-1][i][points[N-1]]>0)
                answer = Math.min(answer, memo[N-1][i][points[N-1]]);
        }
        System.out.println(answer);
    }
}
