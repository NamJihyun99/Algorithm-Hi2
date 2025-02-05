import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] arr = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        List<Mine> list = new ArrayList<>();
        int s = 0;
        for(int p : picks) s += p; // 곡괭이 개수
        int len = minerals.length;
        for(int i = 0; i < len; i += 5) {
            if(s == 0) break;
            int dia = 0;
            int iron = 0;
            int stone = 0;
            for(int j = i; j < i + 5; j++) {
                if(j == len) break;
                String now = minerals[j];
                int v = now.equals("diamond") ? 0 : now.equals("iron") ? 1 : 2;
                dia += arr[0][v];
                iron += arr[1][v];
                stone += arr[2][v];
            }
            // System.out.print(i + " " + dia + " " + iron + " " + stone + " " + "\n");
            
            list.add(new Mine(dia, iron, stone));
            s -= 1;
        }
        
        Collections.sort(list, (o1, o2) -> (o2.stone - o1.stone));
        for(Mine m : list) {
            int dia = m.dia;
            int iron = m.iron;
            int stone = m.stone;
            
            if(picks[0] > 0) {
                answer += dia;
                picks[0] -= 1;
                continue;
            }
            if(picks[1] > 0) {
                answer += iron;
                picks[1] -= 1;
                continue;
            }
            if(picks[2] > 0) {
                answer += stone;
                picks[2] -= 1;
                continue;
            }
        }
        return answer;
    }
    
    class Mine {
        int dia;
        int iron;
        int stone;
        
        Mine(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
    }
}
