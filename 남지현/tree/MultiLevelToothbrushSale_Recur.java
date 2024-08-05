import java.util.*;

// 프로그래머스 - 다단계 칫솔 판매 (재귀)

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> nodes = new HashMap<>();
        Node root = new Node("-", null);
        nodes.put("-", root);
        for (int i=0; i<enroll.length; i++) {
            nodes.put(enroll[i], new Node(enroll[i], nodes.get(referral[i])));
        }
        for (int i=0; i<seller.length; i++) {
            divide(nodes.get(seller[i]), amount[i]*100);
        }
        int[] answer = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
             answer[i] = nodes.get(enroll[i]).count;
        }
        return answer;
    }
    
    private void divide(Node node, int amount) {
        if (node.parent == null || amount == 0) {
            node.count += amount;
            return;
        }
        int remain = amount*10/100;
        node.count += amount-remain;
        divide(node.parent, remain); 
    }
    
    class Node {
        String name;
        Node parent;
        int count=0;
        
        Node (String name, Node parent) {
            this.name = name;
            this.parent = parent;
        }
    }
}
