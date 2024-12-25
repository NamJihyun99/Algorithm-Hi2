import java.util.*;
import java.io.*;

// 백준 2531번 회전 초밥 (매번 Set을 업데이트)

class Main {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // window_size
        int c = Integer.parseInt(st.nextToken());
        List<Integer> plates = new ArrayList<>();
        for (int i=0; i<N; i++) {
            plates.add(Integer.valueOf(br.readLine()));
        }
        int answer = 0;
        int left = 0;
        Set<Integer> window = new HashSet<>();
        while (left < N) {
            for (int i=left; i < left+k; i++) {
                window.add(plates.get(i % N));
            }
            window.add(c);
            answer = Math.max(answer, window.size());
            left++;
            window.clear();
        }
        System.out.println(answer);
    }
}
