import java.util.*;
class Solution {
    static String[] temp = {"*", "+", "-"};
    static List<Long> num = new ArrayList<>();
    static List<String> op = new ArrayList<>(); 
    static boolean[] visited;
    static String[] path;
    static long answer;
    public long solution(String expression) {
        answer = Long.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(expression, "+*-", true);
        int cnt = 0;
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            // System.out.println(str);
            if(str.equals("*") || str.equals("+") || str.equals("-")) {
                op.add(str);
            }
            else num.add(Long.valueOf(str));
        }
        path = new String[3];
        visited = new boolean[3];
        run(0);
        return answer;
    }
    
    static void run(int level) {
        if(level == 3) {
            answer = Math.max(answer, calc());
            return;
        }
        for(int i = 0; i < 3; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            path[level] = temp[i];
            run(level + 1);
            visited[i] = false;
            path[level] = "";
        }
    }
    
    static long calc() {
        List<Long> numl = new ArrayList<>();
        List<String> opl = new ArrayList<>();
        
        numl.addAll(num);
        opl.addAll(op);
        
        for(String p : path) {
            for(int i = 0; i < opl.size(); i++) {
                String now = opl.get(i);
                if(now.equals(p)) {
                    long a = numl.get(i);
                    long b = numl.get(i + 1);
                    long res = 0;
                    if(now.equals("*")) {
                        res = a * b;
                    }
                    if(now.equals("+")) {
                        res = a + b;
                    }
                    if(now.equals("-")) {
                        res = a - b;
                    }
                    numl.remove(i + 1);
                    numl.remove(i);
                    opl.remove(i);
                    
                    numl.add(i, res);
                    i -= 1;
                }
            }
        }
        return Math.abs(numl.get(0));
    }
}
