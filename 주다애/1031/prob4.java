import java.util.*;

// 계산 안된 수식만 주어진 경우도 생각해야 한다.
class Solution {
    public String[] solution(String[] expressions) {
        // 숫자 및 연산자 저장
        List<Exp> list = new ArrayList<>();
        // 완전한 수식
        List<Exp> comp = new ArrayList<>();
        // 계산 안된 수식
        List<Exp> notComp = new ArrayList<>();
        
        for(String exp : expressions) {
            String[] temp = exp.split(" ");
            list.add(new Exp(temp[0], temp[2], temp[4], temp[1]));
            if(!temp[4].equals("X")) comp.add(new Exp(temp[0], temp[2], temp[4], temp[1]));
            else notComp.add(new Exp(temp[0], temp[2], temp[4], temp[1]));
        }
        
        String[] answer = new String[notComp.size()];
        
        int formation = 2; // 최소 2진법
        // 최소 진법 찾기
        for(String exp : expressions){
            for(int i = 0; i < exp.length(); i++){
                char c =  exp.charAt(i);
                if('0' <= c && c <= '9'){
                    formation = Math.max(formation, c - '0' + 1);
                }
            }
        }
        
        int size = comp.size();
        int[] dt = new int[10];
        
        // 완전한 수식 계산
        for(Exp exp : comp) {
            List<Integer> cand = calc(exp, formation);
            for(int c : cand) dt[c]++;
        }
        
        // 진법이 될 수 있는 숫자 list에 저장
        List<Integer> ops = new ArrayList<>();
        if(comp.size() == 0) { // 완전한 수식이 없는 경우
            for(int i = formation; i <= 9; i++) ops.add(i);
        }
        else {
            for(int i = 2; i < 10; i++) {
                if(dt[i] == size) ops.add(i);
            }       
        }
        
        // 계산 안된 수식 구하기
        for(Exp exp : notComp) {
            calcRes(exp, ops);
        }
        
        int idx = 0;
        for(Exp exp : notComp) {
            answer[idx++] = exp.a + " " + exp.op + " " + exp.b + " = " + exp.res; 
        }
        return answer;
    }
    
    static void calcRes(Exp exp, List<Integer> ops) {
        int start = ops.get(0);
        int end = ops.get(ops.size() - 1);
        
        // 가장 낮은 진법 결과 값 구하기
        int a = Integer.parseInt(exp.a, start);
        int b = Integer.parseInt(exp.b, start);
        if(exp.op.equals("-")) b *= -1;
        String res = Integer.toString(a + b, start);
        
        for(int i = start + 1; i <= end; i++) {
            int aa = Integer.parseInt(exp.a, i);
            int bb = Integer.parseInt(exp.b, i);
            if(exp.op.equals("-")) bb *= -1;
            if(!res.equals(Integer.toString(aa + bb, i))) {
                exp.res = "?";
                return;
            }
        }
        exp.res = res;
    }
    
    static List<Integer> calc(Exp exp, int formation) {
        List<Integer> cand = new ArrayList<>();
        for(int i = formation; i <= 9; i++) {
            int a = Integer.parseInt(exp.a, i);
            int b = Integer.parseInt(exp.b, i);
            if(exp.op.equals("-")) b *= -1;
            int r = Integer.parseInt(exp.res, i);
            if(r == a + b) cand.add(i);
        }
        return cand;
    }
    
    static class Exp {
        String a;
        String b;
        String res;
        String op;
        
        Exp(String a, String b, String res, String op) {
            this.a = a;
            this.b = b;
            this.res = res;
            this.op = op;
        }
    }
}
