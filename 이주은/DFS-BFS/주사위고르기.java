import java.util.*;

class Solution {
    int N;
    int half;
    
    int[][] Dice;
    
    int[] A;
    int[] B;
    
    int maxWin = 0;
    int[] answer;
    
    public int[] solution(int[][] dice) {
        Dice = dice;
        N = dice.length;
        half = N/2;
        
        A = new int[half];
        B = new int[half];
        answer = new int[half];
        
        comb(0, 0 ,0);
        
        return answer;
    }
    
    private void comb(int aIdx, int bIdx, int idx) {
        if(aIdx == half && bIdx == half) {
            calc();
            return;
        }
        
        if(aIdx < half) {
            A[aIdx] = idx;
                
            comb(aIdx+1, bIdx, idx+1);
        }
            
        if(bIdx < half) {
            B[bIdx] = idx;
            comb(aIdx, bIdx+1, idx+1);
        }
    }
    
    private void calc() {
        int[] aSum = new int[501];
        int[] bSum = new int[501];
        
        dfs(A, aSum, 0, 0);
        dfs(B, bSum, 0, 0);
        
        int win = 0;
        
        //A가 이기는 경우의 수 계산
        for(int a=1; a<=500; a++) {
            int cnt = 0;
            
            for(int b=1; b<a; b++) {
                cnt += bSum[b];
            }
            
            win += aSum[a] * cnt;
        }
        
       
        if(win > maxWin) {
            maxWin = win;
            for(int i=0; i<half; i++) {
                answer[i] = A[i] + 1;
            }
            
            // System.out.println();
            // System.out.println(win);
            // System.out.println(Arrays.toString(answer));
        }
    }
    
    //주사위 굴린 결과 구하기 -> sumArr에 각각의 경우의 수 카운트
    private void dfs(int[] selected, int[] sumArr, int sum, int cnt) {
        if(cnt == half) {
            sumArr[sum]++;
            return;
        }
        
        for(int i=0; i<6; i++) {
            dfs(selected, sumArr, sum + Dice[selected[cnt]][i], cnt+1);
        }   
    }
}
