import java.io.*;

// 백준 5639번 이진 검색 트리
class Main {
    static StringBuilder sb;

    static void postPrint(Node node) {
        if (node == null) {
            return;
        }
        postPrint(node.left);
        postPrint(node.right);
        sb.append(node.number).append("\n");
    }

    static class Node {
        int number;
        Node left;
        Node right;

        Node (int number) {
            this.number = number;
        }

        void makeTree(int num) {
            if (this.number > num ){
                if (this.left == null) {
                    this.left = new Node(num);
                } else {
                    this.left.makeTree(num);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(num);
                } else {
                    this.right.makeTree(num);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Node root = new Node(Integer.parseInt(line));
        while ((line = br.readLine()) != null) {
            root.makeTree(Integer.parseInt(line));
        }
        sb = new StringBuilder();
        postPrint(root);
        System.out.print(sb);
    }
}
