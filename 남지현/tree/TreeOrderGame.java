import java.util.*;

// 프로그래머스 - 길 찾기 게임 (Lv 3)

class Solution {
    int[][] answer;
    int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        List<Node> nodes = new ArrayList<>();
        for (int i=0; i<nodeinfo.length; i++) {
            nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(nodes);
        Node root = nodes.get(0);
        for (int i=1; i<nodes.size(); i++) {
            makeTree(root, nodes.get(i));
        }
        idx=0;
        preOrder(root);
        idx=0;
        postOrder(root);
        return answer;
    }
    
    private void makeTree(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else makeTree(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else makeTree(parent.right, child);
        }
    }
    
    private void preOrder(Node node) {
        answer[0][idx++] = node.num;
        if (node.left != null) preOrder(node.left);
        if (node.right != null) preOrder(node.right);
    }
    
    private void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        answer[1][idx++] = node.num;
    }
    
    static class Node implements Comparable<Node> {
        int num;
        int x;
        int y;
        Node left;
        Node right;
        
        Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Node node) {
            return this.y==node.y? this.x-node.x: node.y-this.y;
        }
    }
}
