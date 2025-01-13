import java.util.*;

class Solution {
    static int answer = 0;
    public int solution(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                boolean flag = false;
                for (Node child : curr.children) {
                    if (child.letter == word.charAt(i)) {
                        if (i == word.length() - 1) {
                            child.isEnd = true;
                        }
                        child.count++;
                        flag = true;
                        curr = child;
                        break;
                    }
                }
                if (!flag) {
                    Node child = new Node(word.charAt(i));
                    if (i == word.length() - 1) {
                        child.isEnd = true;
                    }
                    curr.children.add(child);
                    curr = child;
                }
            }
        }
        for (Node start : root.children) {
            traverse(start, 1);
        }
        return answer;
    }
    
    private static void traverse(Node curr, int num) {
        if (curr.isEnd) {
            answer += num;
        }
        if (!curr.isEnd && curr.count == 1) {
            answer += num;
            return;
        }
        for (Node next : curr.children) {
            traverse(next, num + 1);
        }
    }
    
    static class Node {
        char letter;
        List<Node> children;
        int count;
        boolean isEnd;
        
        Node(char letter) {
            this.letter = letter;
            this.children = new ArrayList<>();
            this.count = 1;
            this.isEnd = false;
        }
        Node() {
            this.children = new ArrayList<>();
            this.count = 0;
            this.isEnd = false;
        }
    }
}