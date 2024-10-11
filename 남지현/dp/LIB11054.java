import java.util.*;
import java.io.*;

// 백준 11054번 - 가장 긴 바이토닉 부분 수열

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        int[] increasing = new int[N];
        int[] decreasing = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int max = 0;
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            for (int j=0; j<i; j++) {
                if (arr[i]>arr[j] && increasing[i]<increasing[j]) {
                    increasing[i] = increasing[j];
                }
            }
            increasing[i]++;
        }
        for (int i=N-1; i>=0; i--) {
            for (int j=N-1; j>i; j--) {
                if (arr[i]>arr[j] && decreasing[i]<decreasing[j]) {
                    decreasing[i] = decreasing[j];
                }
            }
            decreasing[i]++;
            max = Math.max(max, increasing[i]+decreasing[i]-1);
        }
        System.out.println(max);
    }
}
