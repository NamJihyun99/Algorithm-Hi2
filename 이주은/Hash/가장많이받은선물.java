import java.util.*;

class Solution {    
    int N;
    
    
    public int solution(String[] friends, String[] gifts) {
        StringTokenizer st;
        N = friends.length;
        
        
        Map<String, Integer> mapper = new HashMap<>();
        int[][] graph = new int[N][N];
        int[] score = new int[N];
        int[] cnt = new int[N];
        
        for(String name : friends) {
            mapper.put(name, mapper.size());
        }
        
        for(String log : gifts) {
            st = new StringTokenizer(log);
            
            int from = mapper.get(st.nextToken());
            int to = mapper.get(st.nextToken());
            
            graph[from][to]++;
            score[from]++;
            score[to]--;
        }
        
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                if(graph[i][j] > graph[j][i]) {
                    cnt[i] ++;
                }
                else if(graph[i][j] < graph[j][i]) {
                    cnt[j] ++;
                }
                
                else if(score[i] > score[j]) {
                    cnt[i]++;
                }
                else if(score[i] < score[j]) {
                    cnt[j]++;
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<N; i++) {
            answer = Math.max(cnt[i], answer);
        }
        
        return answer;
    }
}
