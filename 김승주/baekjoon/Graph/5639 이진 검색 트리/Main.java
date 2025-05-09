import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();
            if (input.isEmpty() || input.isBlank()) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }
        
        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
            
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node (int value) {
            this.value = value;
        }

        public void insert(int n) {
            if (n < this.value) {
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }
}