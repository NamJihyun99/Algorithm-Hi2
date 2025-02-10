import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        boolean[] exists = new boolean[1000001];
        int[] score = new int[1000001];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            // 입력에 존재하는 숫자에 대해 exists 배열에 true로 마킹
            exists[cards[i]] = true;
        }
		
        // 모든 카드를 순회하며 반복
        for (int i : cards) {
        	// 현재 카드의 모든 배수들을 확인
            for (int j = i * 2; j < 1000001; j += i) {
            	// i의 배수인 j가 입력에 존재했다면 i의 승점 증가, j의 승점 감소
                if (exists[j]) {
                    score[i]++;
                    score[j]--;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int num : cards) {
            sb.append(score[num]).append(" ");
        }
        
        System.out.println(sb.toString());
    }
}