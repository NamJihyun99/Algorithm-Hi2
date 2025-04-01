import java.util.*;
import java.io.*;

// 백준 2110번 공유기 설치
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] points = new int[N];
        for (int i=0; i<N; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(points);
        int left = 0; 
        int right = points[N-1];
        while (left <= right) {
            int mid = (left + right)/2;
            int count = 1;
            int prev = points[0];
            for (int i=1; i<N; i++) {
                if (points[i]-prev >= mid) {
                    count++;
                    prev = points[i];
                }
            }
            // count가 작다 = 최소 간격이 너무 크다
            if (count >= C) {
                // 매개변수를 키워야
                left = mid+1;
            } else {
                // 매개변수를 줄여야
                right = mid-1;
            }
        }
        System.out.println(left-1);
    }
}
