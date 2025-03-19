class Solution {
    List<Integer> answer = new ArrayList<>();
    public int[] solution(long[] numbers) {
        for (long num : numbers) {
            String binary = Long.toBinaryString(num);
            StringBuilder sb = new StringBuilder(binary).reverse();
            while ((sb.length() + 1) & sb.length() != 0) {
                sb.append('0');
            }
            makeBinaryTree(new Node(), sb.length() / 2, sb.toString());
        }
    }
    private static Node makeBinaryTree(Node tree, int idx, String reversed) {
        // idx가 2^n-1이면(== idx의 모든 비트가 1이면) 이전까지의 이진수로 포화 이진트리가 완성되었다는 뜻.
        if (idx == 0) {
            // 이진수의 트리 표현이 완료된 경우
            if (idx >= reversed.length()) {
                answer.add(1);
                return;
            }
            if (reversed.charAt(idx) == '0') {
                answer.add(0);
                return;
            }
            Node next = new Node('1');
            next.right = tree;
            next.left = makeBinaryTree(null, idx + 1, reversed);
            return next;
        }
        
        
        
        
    }
    
    static class Node {
        char val;
        Node left;
        Node right;
        
        Node (char val) {
            this.val = val;
        }
    }
}