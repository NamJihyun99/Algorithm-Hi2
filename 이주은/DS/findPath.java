//프로그래머스 길찾기 게임(https://school.programmers.co.kr/learn/courses/30/lessons/42892)
//ㅇㄻㄻㅇㄻㄹㅇㄴ
import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        List<Node> list = new ArrayList<>();
        int[][] answer = new int[2][N];
        
        for(int i=0; i<N; i++) {
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        list.sort((n1, n2) -> n1.y != n2.y ? n2.y - n1.y : n1.x - n2.x);
        
        Node root = list.get(0);
        
        for(int i=1; i<N; i++) {
            insertNode(root, list.get(i));
        }
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        
        travelPreOrder(root, pre);
        travelPostOrder(root, post);
        
        for(int i=0; i<N; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        
        return answer;
    }
    
    public void insertNode(Node p, Node n) {
        if(p.x < n.x) {
            if(p.right == null)
                p.right = n;
            else
                insertNode(p.right, n);
        }
        else {
            if(p.left == null)
                p.left = n;
            else
                insertNode(p.left, n);
        }
    }
    
    public void travelPreOrder(Node node, List list) {
        if(node == null)
            return;
        
        list.add(node.value);
        travelPreOrder(node.left, list);
        travelPreOrder(node.right, list);
    }
    
    public void travelPostOrder(Node node, List list) {
        if(node == null)
            return;
        
        travelPostOrder(node.left, list);
        travelPostOrder(node.right, list);
        list.add(node.value);
    }
}

class Node {
    int x, y, value;
    Node left, right;
    
    Node (int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
