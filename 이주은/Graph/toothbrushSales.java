//[프로그래머스] 다단계 칫솔 판매 (https://school.programmers.co.kr/learn/courses/30/lessons/77486)

import java.util.*;

class Solution {
    int cnt = 0;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] profits = new int[N];
    
        Map<String, Node> map = new HashMap<>();
        Node center = new Node(null);
        map.put("-", center);
        
        for(int i=0; i<N; i++) {
            Node p = map.get(referral[i]);
            Node c = new Node(p);
            map.put(enroll[i], c);
        }
        
        for(int i=0; i<seller.length; i++) {
            Node c = map.get(seller[i]);
            
            int pProfit = amount[i]*10;
            c.profit += amount[i]*100 - pProfit;
            
            divideProfit(c.parent, pProfit);
        }
        
        for(int i=0; i<N; i++) {
            profits[i] = map.get(enroll[i]).profit;
        }
    
        return profits;
    }
    
    public void divideProfit(Node c, int amount) {
        int pProfit = amount/10;
        c.profit += amount - pProfit;
        
        if(pProfit != 0 && c.parent != null)
            divideProfit(c.parent, pProfit);
    }
}

class Node {
    int profit;
    int amount;
    Node parent;
    
    Node(Node parent) {
        this.parent = parent;
        
        profit = 0;
        amount = 0;
    }
}
