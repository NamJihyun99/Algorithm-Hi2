import java.util.*;
import java.io.*;

// 백준 1406번 - 에디터

class Main {

    static ArrayDeque<Character> left;
    static ArrayDeque<Character> right;

    private static void process(String[] command) {
        if (command[0].equals("P")) {
            left.addLast(command[1].charAt(0));
        } else if (command[0].equals("B")) {
            if (!left.isEmpty()) {
                left.pollLast();
            }
        } else if (command[0].equals("L")) {
            if (!left.isEmpty()) {
                right.addFirst(left.pollLast());
            }
        } else if (command[0].equals("D")) {
            if (!right.isEmpty()) {
                left.addLast(right.pollFirst());
            }
        }
    }

    private static String makeResult() {
        StringBuilder builder = new StringBuilder();
        while(!left.isEmpty()) {
            builder.append(left.pollFirst());
        }
        while(!right.isEmpty()) {
            builder.append(right.pollFirst());
        }
        return builder.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String init = br.readLine();
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        for (int i=0; i<init.length(); i++) {
            left.addLast(init.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            process(br.readLine().split(" "));
        }
        System.out.println(makeResult());
    }
}
