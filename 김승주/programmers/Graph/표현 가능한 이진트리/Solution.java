import java.util.*;

class Solution {
    static String binary;
    static int[] answer;

    public int[] solution(long[] numbers) {
        answer = new int[numbers.length];
        Arrays.fill(answer, 1);

        for (int i = 0; i < numbers.length; i++) {
            StringBuilder sb = new StringBuilder(Long.toBinaryString(numbers[i]));

            while (((sb.length() + 1) & sb.length()) != 0) {
                sb.insert(0, '0');
            }

            binary = sb.toString();
            makeBinaryTree(0, binary.length() - 1, i);
        }

        return answer;
    }

    private static Node makeBinaryTree(int start, int end, int idx) {
        if (start == end) {
            return new Node(binary.charAt(start));
        }

        int rootLoc = (start + end) / 2;
        Node root = new Node(binary.charAt(rootLoc));

        root.left = makeBinaryTree(start, rootLoc - 1, idx);
        root.right = makeBinaryTree(rootLoc + 1, end, idx);

        if (root.val == '0' && (root.left.val == '1' || root.right.val == '1')) {
            answer[idx] = 0;
        }

        return root;
    }

    static class Node {
        char val;
        Node left;
        Node right;

        Node(char val) {
            this.val = val;
        }
    }
}
