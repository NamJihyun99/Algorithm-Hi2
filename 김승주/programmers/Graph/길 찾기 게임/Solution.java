// https://making-a-scene.notion.site/ca5951e9281941b7a11a8b9b91d82377?pvs=4

import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[][] a = solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    static int[][] answer;
    static int index = -1;
    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        if (nodeinfo.length == 1) {
            answer[0][0] = 1;
            answer[1][0] = 1;
            return answer;
        }
        List<Node> nodeList = new ArrayList<>();
        int rootDepth = -1;
        Node root = null;
        for (int i = 0; i < nodeinfo.length; i++) {
            Node node = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
            nodeList.add(node);
            if (node.y > rootDepth) {
                rootDepth = node.y;
                root = node;
            }
        }
        findChildren(root, nodeList);
        preorder(nodeinfo.length, root);
        postorder(root);
        
        return answer;
    }
    
    private static void findChildren(Node root, List<Node> nodes) {
        if (root == null || nodes.isEmpty())  return;
        if (nodes.size() == 1) {
            if (nodes.get(0).x < root.x) {
                root.left = nodes.get(0);
            } else {
                root.right = nodes.get(0);
            }
            return;
        }
        if (nodes.size() == 2) {
            if (nodes.get(0).x < root.x && root.x < nodes.get(1).x) {
                root.left = nodes.get(0);
                root.right = nodes.get(1);
                return;
            }
            if (nodes.get(1).x < root.x && root.x < nodes.get(0).x) {
                root.left = nodes.get(1);
                root.right = nodes.get(0);
                return;
            }
        }
        
        List<Node> leftSubnodes = new ArrayList<>();
        Node leftChild = null;
        List<Node> rightSubnodes = new ArrayList<>();
        Node rightChild = null;
        int rootDepth = -1;
        for (Node node : nodes) {
            if (node.x == root.x)  continue;
            if (node.x < root.x) {
                if (node.y >= rootDepth) {
                    if (leftChild != null) {
                        leftSubnodes.add(leftChild);
                    }
                    rootDepth = node.y;
                    leftChild = node;
                } else {
                    leftSubnodes.add(node);
                }
            } else {
                if (node.y >= rootDepth) {
                    if (rightChild != null) {
                        rightSubnodes.add(rightChild);
                    }
                    rootDepth = node.y;
                    rightChild = node;
                } else {
                    rightSubnodes.add(node);
                }
            }         
        }
        root.left = leftChild;
        root.right = rightChild;
        findChildren(leftChild, leftSubnodes);
        findChildren(rightChild, rightSubnodes);
    }
    
    private static void preorder(int length, Node root) {
        Stack<Node> s = new Stack<>();
        int[] result = new int[length];
        s.push(root);
        int idx = -1;
        while (!s.isEmpty()) {
            Node curr = s.pop();
            result[++idx] = curr.val;
            if (curr.right != null) {
                s.push(curr.right);
            }
            if (curr.left != null) {
                s.push(curr.left);
            }
        }
        answer[0] = result;
    }
    
    private static void postorder(Node root) {
        if (root != null) {
            if (root.left != null) {
                postorder(root.left);
            }
            if (root.right != null) {
                postorder(root.right);
            }
            answer[1][++index] = root.val;
        }
    }
    
    
    static class Node {
        int x;
        int y;
        int val;
        Node left;
        Node right;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}