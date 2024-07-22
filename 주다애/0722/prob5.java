import java.util.*;

class Solution {
    static int[][] answer;
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(list);
        
        Node root = list.get(0); // 루트 노드 
        
        // 트리 만들기
        int size = list.size();
        for(int k = 1; k < size; k++) {
            makeTree(root, list.get(k));
        }
        
        // 1. 전위 순회
        preOrder(root, answer[0]);
        
        idx = 0;
        // 2. 후위 순회
        postOrder(root, answer[1]);
        
        return answer;
    }
    
    private void makeTree(Node parent, Node child) {
        // 왼쪽 자식
        if (parent.x > child.x) {
            //왼쪽이 비어있으면
            if(parent.left == null) parent.left = child;
            // 이미 채워져있으면
            else makeTree(parent.left, child);
        }
        // 오른쪽 자식
        else {
            if (parent.right == null) parent.right = child;
            else makeTree(parent.right, child);
        }
    }
    
    private void preOrder(Node n, int[] arr) {
        if(n != null) {
            arr[idx++] = n.val;
            preOrder(n.left, arr);
            preOrder(n.right, arr);
        }
    }
    
    private void postOrder(Node n, int[] arr) {
        if (n != null) {
            postOrder(n.left, arr);
            postOrder(n.right, arr);
            arr[idx++] = n.val;
        }
    }
    
    static class Node implements Comparable<Node> {
        int val;
        int x;
        int y;
        Node left;
        Node right;
        
        Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Node n) {
            if(this.y == n.y) return this.x - n.x;
            return n.y - this.y;
        }
    } 
}
