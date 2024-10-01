import java.util.*;
import java.io.*;

// 백준 18111번 - 마인크래프트

class Main {

    static final int MAX = 64_000_001;

    private static void faster() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] counts = new int[257];
        int min = MAX;
        int max = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<M; j++) {
                int height = Integer.parseInt(st.nextToken());
                counts[height]++;
                min = Math.min(min, height);
                max = Math.max(max, height);
            }
        }
        int answerTime = 2*MAX;
        int answerHeight = 0;
        for (int num = min; num <= max; num++) {
            int remain = B;
            int time = 0;
            boolean success = true;
            for (int h=256; h>=0; h--) {
                if (counts[h]==0) continue;
                if (h >= num) { // 블록 제거하여 저장
                    time += 2*(h-num)*counts[h];
                    remain += (h-num)*counts[h];
                } else if (remain >= (num-h)*counts[h]){ // 채워야 하는 블록 충분한 경우
                    time += (num-h)*counts[h];
                    remain -= (num-h)*counts[h];
                } else {
                    success = false;
                    break;
                }
            }
            if (success) {
                if (answerTime > time) {
                    answerTime = time;
                    answerHeight = num;
                } else if (answerTime == time) {
                    if (answerHeight < num) {
                        answerHeight = num;
                    }
                }
            }
        }
        System.out.printf("%d %d\n", answerTime, answerHeight);
    }

    private static void trial1() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int len = N*M;
        int[] board = new int[len];
        int min = MAX;
        int max = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<M; j++) {
                int idx = i*M+j;
                board[idx] = Integer.parseInt(st.nextToken());
                min = Math.min(min, board[idx]);
                max = Math.max(max, board[idx]);
            }
        }
        int answerTime = 2*MAX;
        int answerHeight = 0;
        for (int num = min; num <= max; num++) {
            int remain = B;
            int time = 0;
            boolean success = true;
            for (int i=0; i<len; i++) {
                if (board[i]>num) {
                    remain += board[i]-num;
                    time += 2*(board[i]-num);
                }
            }
            for (int i=0; i<len; i++) {
                if (board[i]<num) {
                    if (remain >= num-board[i]) {
                        remain -= num-board[i];
                        time += num-board[i];
                    } else {
                        success = false;
                        break;
                    }
                }
            }
            if (success) {
                if (answerTime > time) {
                    answerTime = time;
                    answerHeight = num;
                } else if (answerTime == time) {
                    if (answerHeight < num) {
                        answerHeight = num;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answerTime).append(" ").append(answerHeight);
        System.out.println(sb);
    }
    
    public static void main(String[] args) throws Exception {
        trial1();
        // faster();
    }
}
