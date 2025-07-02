import java.util.*;

class Solution {
    static long answer = 0;
    static String op[] = {"+","-","*"};
    static boolean[] visited = new boolean[3];
    static List<Long> numList = new ArrayList<>();
    static List<String> opList = new ArrayList<>();
    static String[] perm = new String[3];
    
    public long solution(String expression) {
        StringTokenizer st = new StringTokenizer(expression, "*+-", true);
        while (!st.hasMoreTokens()) {
            numList.add(Long.valueOf(st.nextToken()));
            opList.add(st.nextToken());
        }
        
        makePermutation(0);
        
        return answer;
    }
    
    static void makePermutation(int depth){
        if(depth==op.length){
            sol();
            return;
        }
        
        for(int i=0; i<op.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            perm[depth] = op[i];
            makePermutation(depth+1);
            visited[i] = false;
        }
    }
    
    static void sol(){
        List<String> oper = new ArrayList<>();
        oper.addAll(opList);
        
        List<Long> num = new ArrayList<>();
        num.addAll(numList);
        
        for (String operator : perm) {
            for(int j = 0; j < oper.size(); j++){
                if(oper.get(j).equals(operator)){
                    long n1 = num.get(j);
                    long n2 = num.get(j + 1);
                    long res = cal(n1, n2, operator);
                    
                    num.remove(j + 1);
                    num.remove(j);
                    oper.remove(j);
                    
                    num.add(j, res);
                    
                    j--;
                }
            }
        }
        
        answer = Math.max(answer, Math.abs(num.get(0)));
    }
    
    static long cal(long n1, long n2, String op){
        long res = 0;
        switch (op){
            case "*": 
                res = n1 * n2;
                break;
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n1 - n2;
                break;
        }
        
        return res;
    }
}