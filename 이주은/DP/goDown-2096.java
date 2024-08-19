import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] min;
    static int[][] max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        min = new int[N][3];
        max = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            min[i][0] = Integer.parseInt(st.nextToken());
            min[i][1] = Integer.parseInt(st.nextToken());
            min[i][2] = Integer.parseInt(st.nextToken());

            max[i][0] = min[i][0];
            max[i][1] = min[i][1];
            max[i][2] = min[i][2];
        }

        for(int i = 1; i < N; i++) {
            max[i][0] += Math.max(max[i-1][0], max[i-1][1]);
            max[i][1] += Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]);
            max[i][2] += Math.max(max[i-1][1], max[i-1][2]);

            min[i][0] += Math.min(min[i-1][0], min[i-1][1]);
            min[i][1] += Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]);
            min[i][2] += Math.min(min[i-1][1], min[i-1][2]);
        }

        Arrays.sort(min[N-1]);
        Arrays.sort(max[N-1]);

        System.out.println(max[N-1][2]+" "+min[N-1][0]);
    }
}
