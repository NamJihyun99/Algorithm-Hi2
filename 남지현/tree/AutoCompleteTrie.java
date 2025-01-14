import java.util.*;

// 프로그래머스 - 자동완성

class Solution {
    
    public int solution(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node node = root;
            for (int i=0; i<word.length(); i++) {
                if (!node.child.containsKey(word.charAt(i))) {
                    node.child.put(word.charAt(i), new Node());
                }
                node = node.child.get(word.charAt(i));
                node.count++;
            }
        }
        
        int answer = 0;
        for (String word : words) {
            Node node = root;
            for (int i=0; i<word.length(); i++) {
                node = node.child.get(word.charAt(i));
                if (node.count==1 || i==word.length()-1) {
                    answer += i+1;
                    break;
                }
            }
        }
        return answer;
    }
    
    static class Node {
        Map<Character, Node> child;
        int count;
        
        Node() {
            child = new HashMap<>();
            count = 0;
        }
    }
}
