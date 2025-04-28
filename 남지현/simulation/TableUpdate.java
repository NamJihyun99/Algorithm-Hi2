import java.util.*;

// 프로그래머스 표 편집

class Solution {
    
    static class Node {
        Node prev, next;
        boolean isDeleted;
    }
    
    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        ArrayDeque<Node> removed = new ArrayDeque<>();
        nodes[0] = new Node();
        for (int i=1; i<n; i++) {
            nodes[i] = new Node();
            nodes[i].prev = nodes[i-1];
            nodes[i-1].next = nodes[i];
        }
        Node now = nodes[k];
        for (String s : cmd) {
            char c = s.charAt(0);
            if (c=='U') {
                int num = Integer.parseInt(s.split(" ")[1]);
                for (int i=0; i<num; i++) {
                    now = now.prev;
                }
            } else if (c=='D') {
                int num = Integer.parseInt(s.split(" ")[1]);
                for (int i=0; i<num; i++) {
                    now = now.next;
                }
            } else if (c=='C') {
                removed.addLast(now);
                now.isDeleted = true;
                if (now.prev != null) {
                    now.prev.next = now.next;
                }
                if (now.next != null) {
                    now.next.prev = now.prev;
                    now = now.next;
                } else {
                    now = now.prev;
                }
            } else if (c=='Z') {
                Node node = removed.pollLast();
                node.isDeleted = false;
                if (node.prev != null) {
                    node.prev.next = node;
                } 
                if (node.next != null) {
                    node.next.prev = node;
                } 
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            if (nodes[i].isDeleted) {
                sb.append('X');
            } else {
                sb.append('O');
            }
        }
        return sb.toString();
    }
}
