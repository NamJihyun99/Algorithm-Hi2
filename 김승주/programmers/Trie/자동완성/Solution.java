import java.util.*;
import java.util.Map.Entry;

class Solution {
    static int answer = 0;
    public int solution(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                curr.children.putIfAbsent(word.charAt(i), new Node());
                curr = curr.children.get(word.charAt(i));
                curr.count++;
            }
            curr.isEnd = true;
        }
        for (Entry<Character, Node> entry : root.children.entrySet()) {
            traverse(entry.getValue(), 1);
        }
        return answer;
    }
    
    private static void traverse(Node node, int num) {
        if (node.isEnd) {
            answer += num;
        }
        if (!node.isEnd && node.count < 2) {
            answer += num;
            return;
        }
        
        for (Entry<Character, Node> entry : node.children.entrySet()) {
            traverse(entry.getValue(), num + 1);
        }
    }
    
    static class Node {
        Map<Character, Node> children;
        boolean isEnd;
        int count;
        
        Node() {
            this.children = new HashMap<>();
            this.isEnd = false;
            this.count = 0;
        }
    }
}
