import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        
        int p = 0;
        for(int i=0; i<picks.length; i++) {
            p += picks[i];
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr2[1] - arr1[1]);
        
        for(int i=0; i<minerals.length; i+=5) {
            int start = i;
            int sum = stone(i, minerals);

            pq.add(new int[] {start , sum});
            if(pq.size() == p)
                break;
        }
        
        int answer = 0;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(picks[0] > 0) {
                answer += dia(now[0], minerals);
                picks[0] --;
            }
            else if(picks[1] > 0) {
                answer += iron(now[0], minerals);
                picks[1] --;
            }
            else if(picks[2] > 0) {
                answer += now[1];
                picks[2] --;
            }
        }
        
        return answer;
    }
    
    private int dia(int start, String[] minerals) {
        int result = 0;
        
        for(int j=0; j<5 && start + j < minerals.length; j++) {
                result ++;
        }
        
        return result;
    }
    
    private int iron(int start, String[] minerals) {
        int result = 0;
        
        for(int j=0; j<5 && start + j < minerals.length; j++) {
            if(minerals[start + j].equals("diamond"))
                result += 5;
            else
                result += 1;
        }
        
        return result;
    }
    
    private int stone(int start, String[] minerals) {
        int result = 0;
        
        for(int j=0; j<5 && start + j < minerals.length; j++) {
            if(minerals[start + j].equals("diamond"))
                result += 25;
            else if(minerals[start + j].equals("iron"))
                result += 5;
            else
                result += 1;
        }
        
        return result;
    }
}
