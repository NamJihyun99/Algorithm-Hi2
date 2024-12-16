import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(input[i]);
        }
        
        int i = 0;
        int j = 0;
        int sum = A[i];
        int count = 0;
        while (i < N) {
            if (i == N - 1) {
                if ((sum > M && j == i) || (sum <= M)) {
                    if (sum == M) {
                        count++;
                    }
                    break;
                }
            }
            
            if (sum == M) {
                count++;
                sum += A[++i];
            } else if (sum > M) {
                if (j == i) {
                    sum += A[++i];
                }
                sum -= A[j++];
            } else {
                sum += A[++i];
            }
        }
        System.out.println(count);
    }
}