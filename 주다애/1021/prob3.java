import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        PriorityQueue<Car> pq = new PriorityQueue<>();
        for(int[] r : routes) {
            pq.add(new Car(r[0], r[1]));
        }
        Car c = pq.poll();
        int s = c.s; // 진입점
        int e = c.e; // 탈출점
        if(pq.isEmpty()) return answer;
        while(!pq.isEmpty()) {
            Car n = pq.peek();
            if(n.s <= e) {
                pq.poll();
                e = Math.min(n.e, e);
            }
            else {
                answer += 1;
                e = n.e;
                pq.poll();
            }
            
        }
        return answer;
    }
    
    static class Car implements Comparable<Car> {
        int s;
        int e;
        
        Car(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        public int compareTo(Car c) {
            if(this.s == c.s) return this.e - c.e; 
            return this.s - c.s;
        }
    }
}
